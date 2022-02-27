package com.example.perludilindungi.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "faskes"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_KODE = "kode"
        const val COLUMN_NAME_NAMA = "nama"
        const val COLUMN_NAME_KOTA = "kota"
        const val COLUMN_NAME_PROVINSI = "provinsi"
        const val COLUMN_NAME_ALAMAT = "alamat"
        const val COLUMN_NAME_LATITUDE = "latitude"
        const val COLUMN_NAME_LONGITUDE = "longitude"
        const val COLUMN_NAME_TELP = "telp"
        const val COLUMN_NAME_JENIS_VASKES = "jenis_vaskes"
        const val COLUMN_NAME_KELAS_RS = "kelas_rs"
        const val COLUMN_NAME_STATUS = "status"
        const val COLUMN_NAME_SOURCE_DATA = "source_data"
    }
}

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_KODE} TEXT," +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_NAMA} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_KOTA} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_PROVINSI} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_ALAMAT} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_LATITUDE} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_LONGITUDE} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_TELP} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_JENIS_VASKES} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_KELAS_RS} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS} TEXT)" +
            "${FeedReaderContract.FeedEntry.COLUMN_NAME_SOURCE_DATA} TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Bookmark.db"
    }
}