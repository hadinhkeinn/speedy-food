package com.buiducha.speedyfood.viewmodel.food_surf

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.speedyfood.data.model.FoodTypes
import com.buiducha.speedyfood.ui.states.FoodByCategoryState
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.CategoryViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.FoodViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class FoodByCategoryViewModel(
    foodViewModel: FoodViewModel,
    private val categoryViewModel: CategoryViewModel
) : ViewModel() {
    private val _foodByCategoryState = MutableStateFlow(FoodByCategoryState())
    val foodByCategoryState: StateFlow<FoodByCategoryState> = _foodByCategoryState.asStateFlow()

    init {
        viewModelScope.launch {
            categoryViewModel.labelId.collect { labelValue ->
                val label = when(labelValue) {
                    FoodTypes.All.label -> "all"
                    FoodTypes.Rice.label -> "rice"
                    FoodTypes.Soup.label -> "soup"
                    FoodTypes.Drink.label -> "drink"
                    FoodTypes.FastFood.label -> "fast_food"
                    FoodTypes.Snack.label -> "snack"
                    FoodTypes.Meat.label -> "meat"
                    else -> ""
                }
                Log.d(TAG, labelValue.toString())
                val foodList = if (label != "all") {
                    foodViewModel.foodDataList.value.filter { foodData ->
                        Log.d(TAG, foodData.type?.lowercase(Locale.ENGLISH)!!)
                        foodData.type == label
                    }
                } else {
                    foodViewModel.foodDataList.value
                }
                _foodByCategoryState.value = _foodByCategoryState.value.copy(
                    categoryLabel = categoryViewModel.labelId.value,
                    foodList = foodList
                )
//                Log.d(TAG, foodViewModel.foodDataList.value.toString())
                Log.d(TAG, foodList.toString())
            }
        }


    }

    companion object {
        const val TAG = "FoodByCategoryViewModel"
    }
}