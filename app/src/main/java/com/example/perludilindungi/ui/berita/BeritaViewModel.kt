package com.example.perludilindungi.ui.berita

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.NewsResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch

class BeritaViewModel(private val repository : Repository
) : ViewModel() {

    val newsResponse: MutableLiveData<NewsResponse>  = MutableLiveData()

    fun getNews(){
        viewModelScope.launch {
            val response: NewsResponse = repository.getNews()
            newsResponse.value = response
        }
    }
}