package com.buiducha.speedyfood.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.buiducha.speedyfood.activity.MainActivity
import com.buiducha.speedyfood.viewmodel.authentication.LoginViewModel

fun startMainActivity(
    context: Context,
) {
    Log.d(LoginViewModel.TAG, "startMainActivity:")
    val intent = Intent(context, MainActivity::class.java)
    (context as Activity).finish()
    context.startActivity(intent)
}