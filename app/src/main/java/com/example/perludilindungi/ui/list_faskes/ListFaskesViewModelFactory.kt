package com.example.perludilindungi.ui.list_faskes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.models.Faskes

class ListFaskesViewModelFactory(private val daftarFaskes: ArrayList<Faskes>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListFaskesViewModel(daftarFaskes) as T
    }
}