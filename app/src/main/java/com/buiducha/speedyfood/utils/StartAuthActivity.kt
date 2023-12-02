package com.buiducha.speedyfood.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.buiducha.speedyfood.activity.AuthActivity
import com.buiducha.speedyfood.viewmodel.authentication.LoginViewModel

fun startAuthActivity(context: Context) {
    Log.d(LoginViewModel.TAG, "startAuthActivity: ")
    val intent = Intent(context, AuthActivity::class.java)
    (context as Activity).finish()
    context.startActivity(intent)
}