package com.example.perludilindungi

import android.app.Application
import com.example.perludilindungi.room.BookmarkFaskesRoomDatabase

class CreateDBApplication : Application() {
    val database: BookmarkFaskesRoomDatabase by lazy { BookmarkFaskesRoomDatabase.getDatabase(this) }
}