package com.buiducha.speedyfood.viewmodel.shared_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.viewmodel.food_surf.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FoodViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()
    private val _foodDataList = MutableStateFlow<List<FoodData>>(emptyList())
    val foodDataList: StateFlow<List<FoodData>> = _foodDataList.asStateFlow()

    init {
        fireBaseRepository.foodDataListener {
            _foodDataList.value = it
            Log.d(HomeViewModel.TAG, it.toString())
        }
    }
}