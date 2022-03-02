package com.example.perludilindungi.ui.detailfaskes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.perludilindungi.CreateDBApplication
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.FragmentDetailFaskesBinding
import com.example.perludilindungi.models.Faskes
import com.example.perludilindungi.models.VaksinInfo
import com.example.perludilindungi.room.BookmarkFaskesViewModel
import com.example.perludilindungi.room.BookmarkFaskesViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail_faskes.*


class DetailFaskesFragment : Fragment() {

    val args: DetailFaskesFragmentArgs by navArgs()

    private var _binding: FragmentDetailFaskesBinding? = null
    private val binding get() = _binding!!
    private lateinit var faskes: Faskes
    private val viewModel: BookmarkFaskesViewModel by activityViewModels {
        BookmarkFaskesViewModelFactory(
            (activity?.application as CreateDBApplication).database.bookmarkFaskesDao()
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkBookmark(faskes.id)
        viewModel.selectedFaskesBookmaraked.observe(viewLifecycleOwner) { response ->
            binding.bookmark.text = if (response) "- Bookmark" else "+ Bookmark"
            Log.d("Debug", "selected id $id = $response")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFaskesBinding.inflate(inflater, container, false)
        faskes = args.faskes
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Masukkin data dari daftar faskes
        binding.namaFaskes.text = faskes.nama
        binding.noKode.text = faskes.kode
        binding.tipeFaskes.text = faskes.jenis_faskes
        binding.alamat.text = faskes.alamat
        binding.noTelp.text = faskes.telp
        binding.status.text = faskes.status
        if (faskes.status != "Siap Vaksinasi") {
            binding.statusImage.setImageResource(R.drawable.ic_resource_false)
        }

        binding.bookmark.setOnClickListener {
            if (binding.bookmark.text.toString() == "+ Bookmark") {
                addNewBookmark()
            } else {
                deleteBookmark()
            }
        }

        //Google Map
        binding.googleMap.setOnClickListener {
            intentToGoogleMaps()
        }

        //Bookmark

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun intentToGoogleMaps() {
        val gmmIntentUri = Uri.parse("geo:${faskes.latitude}," +
                "${faskes.longitude}?q=${faskes.nama}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

//    private fun checkIsBookmarked(): Boolean {
//        return viewModel.checkBookmark(id)
//    }

    private fun addNewBookmark() {
        viewModel.addNewBookmark(faskes)
    }

    private fun deleteBookmark() {
        viewModel.deleteBookmark(faskes)
    }

}

