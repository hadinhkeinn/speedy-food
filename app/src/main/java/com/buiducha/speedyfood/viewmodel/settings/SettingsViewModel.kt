package com.buiducha.speedyfood.viewmodel.settings

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.utils.startAuthActivity

class SettingsViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()

    fun logout(
        context: Context
    ) {
        Log.d("This is a log", "logout: ")
        fireBaseRepository.userLogout {
            startAuthActivity(context)
        }
    }

}