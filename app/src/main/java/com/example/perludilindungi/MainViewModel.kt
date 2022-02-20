package com.example.perludilindungi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.ProvinceResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) :ViewModel() {
    val myResponse: MutableLiveData<ProvinceResponse> = MutableLiveData()

    fun getProvince(){
        viewModelScope.launch {
            val response: ProvinceResponse = repository.getProvince()
            myResponse.value = response
        }
    }



}