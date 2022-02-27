package com.example.perludilindungi.ui.berita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.repository.Repository

@Suppress("UNCHECKED_CAST")
class BeritaViewModelFactory (
    private val repository: Repository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeritaViewModel(repository) as T
    }

    }