package com.buiducha.speedyfood.viewmodel.authentication

import androidx.lifecycle.ViewModel
import com.buiducha.speedyfood.data.model.UserData
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.ui.states.AddInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddInfoViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()
    private val _addInfoState = MutableStateFlow(AddInfoState())
    val addInfoState: StateFlow<AddInfoState> get() = _addInfoState

    fun setFullName(fullName: String) {
        _addInfoState.value = _addInfoState.value.copy(
            fullName = fullName
        )
    }

    fun setDateOfBirth(dob: String) {
        _addInfoState.value = _addInfoState.value.copy(
            dateOfBirth = dob
        )
    }

    fun setPhoneNumber(phone: String) {
        _addInfoState.value = _addInfoState.value.copy(
            phoneNumber = phone
        )
    }

    fun setGender(gender: Boolean) {
        _addInfoState.value = _addInfoState.value.copy(
            gender = gender
        )
    }

    fun addUserInfo(
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        val userInfo = UserData(
            id = fireBaseRepository.getCurrentUser()?.uid!!,
            name = _addInfoState.value.fullName,
            phoneNumber = _addInfoState.value.phoneNumber,
            dateOfBirth = _addInfoState.value.dateOfBirth,
            gender = _addInfoState.value.gender
        )
        fireBaseRepository.addUserInfo(
            userData = userInfo,
            onAddSuccess = onAddSuccess,
            onAddFailure = onAddFailure
        )
    }
}