package com.example.perludilindungi.ui.faskes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.CityResponse
import com.example.perludilindungi.models.ProvinceResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch

class FaskesViewModel(private val repository: Repository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is faskes Fragment"
    }

    val text: LiveData<String> = _text

    val provResponse: MutableLiveData<ProvinceResponse> = MutableLiveData()
    val cityResponse: MutableLiveData<CityResponse> = MutableLiveData()

    fun getProvince(){
        viewModelScope.launch {
            val response: ProvinceResponse = repository.getProvince()
            provResponse.value = response
        }
    }

    fun getCity(start_id: String) {
        viewModelScope.launch {
            val response: CityResponse = repository.getCity(start_id)
            cityResponse.value = response
        }
    }

}