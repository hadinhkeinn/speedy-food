package com.buiducha.speedyfood.data.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.data.model.OptionalItemData
import com.buiducha.speedyfood.data.model.OrderData
import com.buiducha.speedyfood.data.model.OrderFeedback
import com.buiducha.speedyfood.data.model.UserData
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FireBaseRepository private constructor(context: Context) {
    private val database = Firebase.database
    private val foodsRef = database.getReference("foods")
    private val toppingsRef = database.getReference("toppings")
    private val usersRef = database.getReference("users")
    private val orderRef = database.getReference("orders")
    private val feedbackRef = database.getReference("feedbacks")
    private var auth: FirebaseAuth = Firebase.auth

    fun getCurrentUser() = auth.currentUser

    fun getFeedbacks(
        onGetFeedbackSuccess: (OrderFeedback) -> Unit
    ) {
        feedbackRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { shot ->
                    val data = shot.getValue(OrderFeedback::class.java)
                    data?.let(onGetFeedbackSuccess)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun addFeedback(
        feedback: OrderFeedback,
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        feedbackRef.push().setValue(feedback)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onAddSuccess()
                    Log.d(TAG, "addFeedback: successfully")
                }
            }
            .addOnFailureListener {
                onAddFailure()
            }
    }

    fun getOrder(
        userId: String,
        onGetOrderSuccess: (List<OrderData>) -> Unit,
        onGetOrderFailure: () -> Unit
    ) {
        orderRef.orderByChild("userId").equalTo(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<OrderData>()
                snapshot.children.forEach { shot ->
                    val data = shot.getValue(OrderData::class.java)
                    data?.let {
                        dataList += it
                    }
                    Log.d(TAG, data.toString())
                }
                onGetOrderSuccess(dataList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun placeOrder(
        orderData: OrderData,
        onOrderSuccess: () -> Unit,
        onOrderFailure: () -> Unit
    ) {
        orderRef.push().setValue(orderData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "add order success")
                    onOrderSuccess()
                }
            }
            .addOnFailureListener {e ->
                Log.e(TAG, "add order failure", e)
                onOrderFailure()
            }
    }

    fun getUserInfo(
        userId: String,
        onGetInfoSuccess: (UserData) -> Unit,
        onGetInfoFailure: () -> Unit
    ) {
        usersRef.orderByChild("id").equalTo(userId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { user ->
                    val data = user.getValue(UserData::class.java)
                    data?.let(onGetInfoSuccess)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onGetInfoFailure()
            }

        })
    }

    fun isUserInfoExists(
        userId: String,
        onUserExists: () -> Unit,
        onUserNotExists: () -> Unit
    ) {
        usersRef.orderByChild("id").equalTo(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, snapshot.value.toString())
                if (snapshot.exists()) {
                    onUserExists()
                    Log.d(TAG, "user exists")
                } else {
                    onUserNotExists()
                    Log.d(TAG, "user not exists")
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun updateUserInfo(
        userData: UserData,
    ) {
        usersRef.orderByChild("id").equalTo(auth.currentUser?.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { shot ->
                    val key = shot.key
                    key?.let {
                        usersRef.child(key).setValue(userData).addOnCompleteListener {
                            Log.d(TAG, "updateUserInfo: ")
                        }
                        Log.d(TAG, key)
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun addUserInfo(
        userData: UserData,
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        usersRef.push().setValue(userData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "add user success")
                    onAddSuccess()
                }
            }
            .addOnFailureListener {e ->
                Log.e(TAG, "add user failure", e)
                onAddFailure()
            }
    }

    fun forgotPassword(
        email: String,
        onSendEmailSuccess: () -> Unit,
        onSendEmailFailure: () -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    onSendEmailSuccess()
                }
            }
            .addOnFailureListener {e ->
                onSendEmailFailure()
                Log.e(TAG, "send email failure", e)
            }
    }

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        onChangePasswordSuccess: () -> Unit,
        onChangePasswordFailure: (String) -> Unit,
    ) {
        val credential = EmailAuthProvider.getCredential(
            auth.currentUser?.email ?: "",
            oldPassword
        )

        auth.currentUser?.reauthenticate(credential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Call updatePassword() method to update the password
                    updatePassword(
                        newPassword = newPassword,
                        onChangePasswordSuccess = onChangePasswordSuccess,
                        onChangePasswordFailure = onChangePasswordFailure
                    )
                    Log.d(TAG, "changePassword: true")
                } else {
                    // Handle the error
                    onChangePasswordFailure(OLD_PASSWORD_INVALID)
                    Log.d(TAG, "changePassword: false")
                }
            }
    }

    private fun updatePassword(
        newPassword: String,
        onChangePasswordSuccess: () -> Unit,
        onChangePasswordFailure: (String) -> Unit
    ) {
        auth.currentUser?.updatePassword(newPassword)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Password updated successfully
                    onChangePasswordSuccess()
                    Log.d(TAG, "update password successfully")
                } else {
                    // Handle the error
                    onChangePasswordFailure(PASSWORD_CHANGE_FAILURE)
                    Log.d(TAG, "update password failure")
                }
            }
    }

    fun createUser(
        activity: Activity,
        email: String,
        password: String,
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    onCreateSuccess()
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                    Log.d(TAG, "create user successfully")
                } else if (task.isCanceled) {
                    Log.d(TAG, "create user failure")
                    onCreateFailure()
                }
            }
            .addOnFailureListener {
                onCreateFailure()
            }
    }

    fun userLogout(
        onLogout: () -> Unit
    ) {
        auth.signOut()
        auth.addAuthStateListener {
            if (auth.currentUser == null) {
                onLogout()
            }
        }
    }

    fun saveLoginToken() {
        auth.currentUser?.getIdToken(true)?.addOnCompleteListener {tokenTask ->
            if (tokenTask.isSuccessful) {
                val tokenResult = tokenTask.result
                val loginToken = tokenResult.token
                loginToken?.let {
                    Log.d(TAG, "autoLogin: $it")
                }
            }
        }
    }

    fun userLogin(
        activity: Activity,
        email: String,
        password: String,
        onLoginSuccess: () -> Unit,
        onLoginFailure: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        if (it.isEmailVerified) {
                            Log.d(TAG, "login successfully")
                            onLoginSuccess()
                        } else {
                            Log.d(TAG, "email not verified")
                            onLoginFailure("Email is not verified")
                        }
                    }
                } else if (task.isCanceled) {
                    Log.d(TAG, "login failure")
                }
            }
            .addOnFailureListener(activity) { _ ->
                Log.d(TAG, "login failure")
                onLoginFailure("Login failure")
            }
    }

    fun getFood(
        foodId: String,
        onGetFoodSuccess: (FoodData) -> Unit
    ) {
        foodsRef.orderByChild("id").equalTo(foodId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { shot ->
                    Log.d(TAG, shot.value.toString())
                    val data = shot.getValue(FoodData::class.java)
                    data?.let(onGetFoodSuccess)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun foodDataListener(
        foodDataUpdate: (MutableList<FoodData>) -> Unit
    ) {
        foodsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "onDataChange: ")
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "foodDataListener: ")
                val value = dataSnapshot.getValue<Map<String, FoodData>>()
                value?.let {
                    foodDataUpdate(it.values.toMutableList())
                }
                if (value != null) {
                    Log.d(TAG, "Value is: $value")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun getTopping(
        toppingId: String,
        onToppingListener: (OptionalItemData) -> Unit
    ) {
        toppingsRef.orderByChild("id").equalTo(toppingId).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { shot ->
                    Log.d(TAG, shot.value.toString())
                    val data = shot.getValue(OptionalItemData::class.java)
                    onToppingListener(data!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "onCancelled: failure to get data")
            }

        })
    }
    companion object {
        const val TAG = "FireBaseRepository"
        private var INSTANCE: FireBaseRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FireBaseRepository(context)
            }
        }

        fun get(): FireBaseRepository {
            return INSTANCE ?: throw  IllegalStateException("repo must be init")
        }

        const val OLD_PASSWORD_INVALID = "old_password_invalid"
        const val PASSWORD_CHANGE_FAILURE = "password_change_failure"
    }
}