package com.buiducha.speedyfood.viewmodel.authentication

import androidx.lifecycle.ViewModel
import com.buiducha.speedyfood.data.repository.FireBaseRepository

class ForgotPasswordViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()
    fun forgotPassword(
        email: String,
        onSendEmailSuccess: () -> Unit,
        onSendEmailFailure: () -> Unit
    ) {
        fireBaseRepository.forgotPassword(
            email = email,
            onSendEmailSuccess = onSendEmailSuccess,
            onSendEmailFailure = onSendEmailFailure
        )
    }
}