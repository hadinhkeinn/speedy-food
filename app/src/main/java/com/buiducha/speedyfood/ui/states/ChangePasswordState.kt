package com.buiducha.speedyfood.ui.states

data class ChangePasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = ""
)
