package com.example.perludilindungi.room

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.Faskes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookmarkFaskesViewModel(private val bookmarkFaskesDao: BookmarkFaskesDao) : ViewModel() {

    var bookmarks: MutableLiveData<List<BookmarkFaskes>> = MutableLiveData()
    var selectedFaskesBookmaraked: MutableLiveData<Boolean> = MutableLiveData()

    fun checkBookmark(id: Int) {
        viewModelScope.launch {
            bookmarkFaskesDao.getCountItem(id).collect {
                Log.d("DebugID", "selected id $id = $it")
                selectedFaskesBookmaraked.value = (it != 0)
            }
        }
    }

    fun addNewBookmark(faskes: Faskes) {
        val newBookmark = getNewBookmark(faskes)
        viewModelScope.launch {
            bookmarkFaskesDao.insert(newBookmark)
        }
    }


    fun getAllBookmark(){
        viewModelScope.launch {
            bookmarkFaskesDao.getItems().collect {
                bookmarks.value = it
            }
        }
    }

    fun deleteBookmark(faskes: Faskes){
        val newBookmark = getNewBookmark(faskes)
        viewModelScope.launch {
            bookmarkFaskesDao.delete(newBookmark)
        }
    }

    private fun getNewBookmark(faskes: Faskes): BookmarkFaskes {
        return BookmarkFaskes(
            id = faskes.id,
            kode = faskes.kode,
            nama = faskes.nama,
            kota = faskes.kota,
            provinsi = faskes.provinsi,
            alamat = faskes.alamat,
            latitude = faskes.latitude,
            longitude = faskes.longitude,
            telp = faskes.telp,
            jenis_faskes = faskes.jenis_faskes,
            kelas_rs = faskes.kelas_rs,
            status = faskes.status,
            source_data = faskes.source_data,
        )
    }

}

class BookmarkFaskesViewModelFactory(private val bookmarkFaskesDao: BookmarkFaskesDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkFaskesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookmarkFaskesViewModel(bookmarkFaskesDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}