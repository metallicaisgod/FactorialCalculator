package com.kirillmesh.factorialcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    fun calculate(value: String){
        if(value.isNullOrBlank()) {
            _error.value = true
            return
        }
        viewModelScope.launch {
            _progress.value = true
            val number = value.toLong()
            //calculate
            delay(1000)
            _result.value = number.toString()
            _progress.value = false
        }

    }
}