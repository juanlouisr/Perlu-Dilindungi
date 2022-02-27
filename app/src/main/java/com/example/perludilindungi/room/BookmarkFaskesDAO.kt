package com.example.perludilindungi.room

import android.content.ClipData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkFaskesDao {

    @Query("SELECT * from bookmarkfaskes ORDER BY id ASC")
    fun getItems(): Flow<List<BookmarkFaskes>>

    @Query("SELECT * from bookmarkfaskes WHERE id = :id")
    fun getItem(id: Int): Flow<BookmarkFaskes>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookmark: BookmarkFaskes)

    @Update
    suspend fun update(bookmark: BookmarkFaskes)

    @Delete
    suspend fun delete(bookmark: BookmarkFaskes)
}