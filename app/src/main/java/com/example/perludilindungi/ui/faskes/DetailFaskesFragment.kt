package com.example.perludilindungi.ui.faskes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.FragmentDetailFaskesBinding

class DetailFaskesFragment : Fragment() {

    private var _binding: FragmentDetailFaskesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFaskesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Masukkin data dari daftar faskes
        var nama_faskes = "KLINIK DPR RI";
        var kode = "N0001702";
        var jenis_faskes = "KLINIK";
        var alamat =
            "Gedung DPR-RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia";
        var no_telp = "(021) 5715801";
        var status = "Siap Vaksinasi";
        var latitude = "-6.2101653";
        var longitude = "106.8004706";

        binding.namaFaskes.text = nama_faskes;
        binding.noKode.text = kode;
        binding.tipeFaskes.text = jenis_faskes;
        binding.alamat.text = alamat;
        binding.noTelp.text = no_telp;
        binding.status.text = status;
        if (!status.equals("Siap Vaksinasi")) {
            binding.statusImage.setImageResource(R.drawable.ic_resource_false);
        }

        //Google Map
        val gmmIntentUri = Uri.parse("google.streetview:cbll=${latitude}, ${longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps");
        binding.googleMap.setOnClickListener { startActivity(mapIntent) };

        //Bookmark
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}