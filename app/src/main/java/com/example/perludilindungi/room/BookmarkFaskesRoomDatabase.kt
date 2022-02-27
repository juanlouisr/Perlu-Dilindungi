package com.example.perludilindungi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookmarkFaskes::class], version = 1, exportSchema = false)
abstract class BookmarkFaskesRoomDatabase : RoomDatabase(){
    abstract fun bookmarkFaskesDao(): BookmarkFaskesDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkFaskesRoomDatabase? = null

        fun getDatabase(context: Context): BookmarkFaskesRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, BookmarkFaskesRoomDatabase::class.java, "bookmarkfaskes_database")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}