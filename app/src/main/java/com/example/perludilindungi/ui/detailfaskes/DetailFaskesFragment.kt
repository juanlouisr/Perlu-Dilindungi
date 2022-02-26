package com.example.perludilindungi.ui.detailfaskes

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.FragmentDetailFaskesBinding
import com.example.perludilindungi.models.FeedReaderContract
import com.example.perludilindungi.models.SQLiteHelper


class DetailFaskesFragment : Fragment() {

    private var _binding: FragmentDetailFaskesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFaskesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Masukkin data dari daftar faskes

        binding.namaFaskes.text = nama_faskes
        binding.noKode.text = kode
        binding.tipeFaskes.text = jenis_faskes
        binding.alamat.text = alamat
        binding.noTelp.text = no_telp
        binding.status.text = status
        if (status != "Siap Vaksinasi") {
            binding.statusImage.setImageResource(R.drawable.ic_resource_false)
        }
//        if (!dataNotInDB()) {
//            binding.bookmark.text = "- UnBookmark"
//        }

        //Google Map
        binding.googleMap.setOnClickListener {
            intentToGoogleMaps()
        }

//        //Bookmark
//        binding.bookmark.setOnClickListener {
//            if (binding.bookmark.text == "+ Bookmark") {
//                insertToDB()
//                binding.bookmark.text = "- Bookmark"
//            } else {
//                deleteFromDB()
//                binding.bookmark.text = "+ Bookmark"
//            }
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun intentToGoogleMaps() {
        val gmmIntentUri = Uri.parse("geo:${latitude},${longitude}?q=${nama_faskes}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun dataNotInDB(): Boolean {
        val dbHelper = this.activity?.let { SQLiteHelper(it.applicationContext) }
        val db = dbHelper?.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(FeedReaderContract.FeedEntry.COLUMN_NAME_ID)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db?.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,                 // The array of columns to return (pass null to get all)
            selection,                  // The columns for the WHERE clause
            selectionArgs,              // The values for the WHERE clause
            null,               // don't group the rows
            null,                // don't filter by row groups
            null                // The sort order
        )

        return cursor == null;
    }

    private fun deleteFromDB() {
        val dbHelper = this.activity?.let { SQLiteHelper(it.applicationContext) }
        val db = dbHelper?.readableDatabase
        // Define 'where' part of query.
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_ID} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(id.toString())
        // Issue SQL statement.
        db?.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs)
    }

    private fun insertToDB() {
        val dbHelper = this.activity?.let { SQLiteHelper(it.applicationContext) }
        val db = dbHelper?.writableDatabase

        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ID, Companion.id)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_KODE, kode)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAMA, nama_faskes)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_KOTA, kota)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_PROVINSI, provinsi)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ALAMAT, alamat)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_LATITUDE, latitude)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_LONGITUDE, longitude)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TELP, no_telp)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_JENIS_VASKES, jenis_faskes)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_KELAS_RS, kelas_rs)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS, status)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_SOURCE_DATA, source_data)
        }

        db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
    }

    override fun onDestroy() {
        val dbHelper = this.activity?.let { SQLiteHelper(it.applicationContext) }
        dbHelper?.close()
        super.onDestroy()
    }

    companion object {
        private const val id = 2725
        private const val kota = "KOTA ADM. JAKARTA PUSAT"
        private const val provinsi = "DKI JAKARTA"
        private const val kelas_rs = "1"
        private const val source_data = "Control Tower KPCPEN"
        private const val nama_faskes = "KLINIK DPR RI"
        private const val kode = "N0001702"
        private const val jenis_faskes = "KLINIK"
        private const val alamat =
            "Gedung DPR-RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia"
        private const val no_telp = "(021) 5715801"
        private const val status = "Siap Vaksinasi"
        private const val latitude = "-6.2101653"
        private const val longitude = "106.8004706"
    }
}

