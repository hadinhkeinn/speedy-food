package com.buiducha.speedyfood.viewmodel.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()

    init {
        viewModelScope.launch {
        }
    }

    fun onLoginSuccess(
        onUserExists: () -> Unit,
        onUserNotExists: () -> Unit
    ) {
        fireBaseRepository.isUserInfoExists(
            userId = fireBaseRepository.getCurrentUser()?.uid!!,
            onUserExists = onUserExists,
            onUserNotExists = onUserNotExists
        )
    }

    fun checkAuthState(
        onLogged: () -> Unit,
        onNotLogged: () -> Unit
    ) {
        viewModelScope.launch {
            delay(2000)
            if (fireBaseRepository.getCurrentUser() != null) {
                onLogged()
            } else {
                onNotLogged()
            }
        }

    }
}