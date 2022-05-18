package com.example.apimars.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimars.network.MarsApiService
import com.example.apimars.network.MarsPhoto
import com.example.apimars.network.MarsState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _status = MutableLiveData<String>()
    val status:LiveData<String> = _status

    private val _listPhotos = MutableLiveData<List<MarsPhoto>>()
    val listPhotos:LiveData<List<MarsPhoto>> = _listPhotos

    private val _listStates = MutableLiveData<List<MarsState>>()
    val listStates:LiveData<List<MarsState>> = _listStates

    fun getMarsPhotos(){
        viewModelScope.launch {
            try {
                val listResult = MarsApiService.Companion.MarsApi.retrofitService.getPhotos()
                _listPhotos.value = listResult
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun getMarsStates(){
        viewModelScope.launch {
            try {
                val listResult = MarsApiService.Companion.MarsApi.retrofitService.getState()
                _listStates.value = listResult
                _status.value = "Success: ${listResult.size} states retrieved"
            } catch (e : Exception){
                _status.value = "Error: ${e.message}"
            }
        }
    }
}