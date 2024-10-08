package com.example.bmi_vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BmiViewModel : ViewModel() {
    private val _height = MutableStateFlow("")
    val height = _height.asStateFlow()

    private val _weight = MutableStateFlow("")
    val weight = _weight.asStateFlow()

    private val _bmi = MutableStateFlow(0.0)
    val bmi = _bmi.asStateFlow()

    fun updateHeight(height: String) {
        _height.value = height.replace(",", ".")
        calculateBmi()
    }

    fun updateWeight(weight: String) {
        _weight.value = weight.replace(",", ".")
        calculateBmi()
    }

    private fun calculateBmi() {
        val height = _height.value.toDoubleOrNull() ?: 0.0
        val weight = _weight.value.toDoubleOrNull() ?: 0.0
        when {
            height == 0.0 || weight == 0.0 -> _bmi.value = 0.0
            else -> _bmi.value = weight / (height * height)
        }
    }
}
