package com.example.perludilindungi.ui.faskes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FaskesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is faskes Fragment"
    }
    val text: LiveData<String> = _text
}