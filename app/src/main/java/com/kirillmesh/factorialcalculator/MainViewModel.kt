package com.kirillmesh.factorialcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun calculate(value: String?) {
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }
        viewModelScope.launch {
            _state.value = Progress
            val number = value.toLong()
            //calculate
            delay(1000)
            _state.value = Result(number.toString())
        }

    }
}