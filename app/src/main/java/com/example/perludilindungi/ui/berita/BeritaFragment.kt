package com.example.perludilindungi.ui.berita

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.databinding.FragmentBeritaBinding
import com.example.perludilindungi.models.News
import com.example.perludilindungi.repository.Repository

class BeritaFragment : Fragment() {

    private var _binding: FragmentBeritaBinding? = null
    private lateinit var viewModel: BeritaViewModel
    private lateinit var factory: BeritaViewModelFactory

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        viewModel.getNews()
        viewModel.newsResponse.observe(viewLifecycleOwner) { response ->
            val listResult = response.results
            val adapter = NewsAdapter(listResult)
            binding.rvNews.adapter = adapter
            adapter.setOnItemClickListener(object : NewsAdapter.onItemClickListener {
                override fun oncClick(position: Int) {
                    //Toast.makeText(requireContext(), "item no : $position", Toast.LENGTH_SHORT)
                    //    .show()
                    val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
                    intent.putExtra("newsUrl", listResult[position].guid)
                    startActivity(intent)
                }
            })
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = Repository()
        factory = BeritaViewModelFactory(repository)
        viewModel =
            ViewModelProvider(
                this,
                factory
            )[BeritaViewModel::class.java]

        _binding = FragmentBeritaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}