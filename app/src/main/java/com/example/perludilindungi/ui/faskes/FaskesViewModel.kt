package com.example.perludilindungi.ui.faskes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.CityResponse
import com.example.perludilindungi.models.Faskes
import com.example.perludilindungi.models.FaskesResponse
import com.example.perludilindungi.models.ProvinceResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch

class FaskesViewModel(private val repository: Repository) : ViewModel() {

    val provResponse: MutableLiveData<ProvinceResponse> = MutableLiveData()
    val cityResponse: MutableLiveData<CityResponse> = MutableLiveData()
    val faskesResponse: MutableLiveData<FaskesResponse> = MutableLiveData()

    val provinces = ArrayList<String>()
    val cities = ArrayList<String>()
    val daftarFaskes = ArrayList<Faskes>()

    var latitude: Double = 10.0
    var longitude: Double = 10.0

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

    fun getDaftarFaskes(province: String, city: String) {
        viewModelScope.launch {
            val response: FaskesResponse = repository.getFaskes(province,city)
            faskesResponse.value = response
        }
    }

}