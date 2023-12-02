package com.buiducha.speedyfood.viewmodel.food_surf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.ui.states.HomeState
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.FoodViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val foodViewModel: FoodViewModel,
) : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
//        fireBaseRepository.foodDataListener {
//            _homeState.value = _homeState.value.copy(
//                foodList = it
//            )
//            Log.d(TAG, it.toString())
//        }
        fireBaseRepository.saveLoginToken()
        viewModelScope.launch {
            foodViewModel.foodDataList.collect {
                _homeState.value = _homeState.value.copy(
                    foodList = it
                )
            }
        }
    }


    companion object {
        const val TAG = "HomeViewModel"
    }
}