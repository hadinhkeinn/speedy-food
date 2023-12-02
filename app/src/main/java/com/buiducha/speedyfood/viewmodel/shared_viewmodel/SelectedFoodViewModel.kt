package com.buiducha.speedyfood.viewmodel.shared_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.data.model.OptionalItemData
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SelectedFoodViewModel : ViewModel(){
    private val fireBaseRepository = FireBaseRepository.get()
    private val _foodData = MutableStateFlow<FoodData?>(null)
    private val _toppingsData = MutableStateFlow<MutableList<OptionalItemData>>(mutableListOf())
    val foodData: StateFlow<FoodData?> get() = _foodData

    init {
        getToppings()
    }

    fun foodUpdate(data: FoodData) {
        _foodData.value = data
    }

    private fun getToppings() {
        viewModelScope.launch {
            _foodData.collect {foodData ->
                foodData?.toppings?.forEach { toppingId ->
                    fireBaseRepository.getTopping(
                        toppingId = toppingId
                    ) {item ->
                        _toppingsData.value += item
                    }
                }
            }
        }
    }
}