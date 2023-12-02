package com.buiducha.speedyfood.viewmodel.shared_viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {
    private val _labelId = MutableStateFlow(0)
    val labelId: StateFlow<Int> = _labelId.asStateFlow()

    fun setLabel(label: Int) {
        _labelId.value = label
    }
}