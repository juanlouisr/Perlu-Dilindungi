package com.example.perludilindungi.ui.berita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.perludilindungi.databinding.FragmentDetailBeritaBinding
import kotlinx.android.synthetic.main.fragment_detail_berita.*

class DetailBeritaFragment : Fragment() {
    val args: DetailBeritaFragmentArgs by navArgs()
    private var _binding: FragmentDetailBeritaBinding? = null
    private val binding get() = _binding!!
    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)
        url = args.url
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webview_detail_berita.webViewClient = WebViewClient()
        webview_detail_berita.loadUrl(url!!)
        webview_detail_berita.settings.javaScriptEnabled = true
        webview_detail_berita.settings.setSupportZoom(true)

        binding.etUrl.setText(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}