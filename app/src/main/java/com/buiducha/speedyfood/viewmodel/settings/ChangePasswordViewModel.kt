package com.buiducha.speedyfood.viewmodel.settings

import androidx.lifecycle.ViewModel
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.ui.states.ChangePasswordState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChangePasswordViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()
    private val _changePasswordState = MutableStateFlow(ChangePasswordState())
    val changePasswordState: StateFlow<ChangePasswordState> = _changePasswordState.asStateFlow()

    fun changePassword(
        onChangePasswordSuccess: () -> Unit,
        onChangePasswordFailure: (String) -> Unit,
    ) {
        if (isPasswordValid()) {
            fireBaseRepository.changePassword(
                oldPassword = _changePasswordState.value.oldPassword,
                newPassword = _changePasswordState.value.newPassword,
                onChangePasswordSuccess = onChangePasswordSuccess,
                onChangePasswordFailure = onChangePasswordFailure,
            )
        }
    }

    fun setConfirmPassword(confirmPassword: String) {
        _changePasswordState.value = _changePasswordState.value.copy(
            confirmPassword = confirmPassword
        )
    }

    fun setNewPassword(newPassword: String) {
        _changePasswordState.value = _changePasswordState.value.copy(
            newPassword = newPassword
        )
    }

    fun setOldPassword(oldPassword: String) {
        _changePasswordState.value = _changePasswordState.value.copy(
            oldPassword = oldPassword
        )
    }

    private fun isPasswordValid(): Boolean {
        val oldPassword = _changePasswordState.value.oldPassword
        val newPassword = _changePasswordState.value.newPassword
        val confirmPassword = _changePasswordState.value.confirmPassword
        if (newPassword == confirmPassword && newPassword.isNotEmpty() && oldPassword.isNotEmpty()) {
            return true
        }
        return false
    }
}