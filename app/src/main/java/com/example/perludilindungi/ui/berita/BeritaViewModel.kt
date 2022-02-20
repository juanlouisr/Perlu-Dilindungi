package com.example.perludilindungi.ui.berita

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BeritaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is berita Fragment"
    }
    val text: LiveData<String> = _text
}