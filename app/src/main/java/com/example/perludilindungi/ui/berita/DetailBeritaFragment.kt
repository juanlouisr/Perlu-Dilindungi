package com.example.perludilindungi.ui.berita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.FragmentDetailBeritaBinding
import com.example.perludilindungi.databinding.FragmentDetailFaskesBinding
import com.example.perludilindungi.models.Faskes
import com.example.perludilindungi.ui.detailfaskes.DetailFaskesFragmentArgs
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import kotlinx.android.synthetic.main.activity_detail_berita.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailBeritaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailBeritaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailBeritaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}