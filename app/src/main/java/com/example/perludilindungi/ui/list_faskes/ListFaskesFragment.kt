package com.example.perludilindungi.ui.list_faskes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.perludilindungi.databinding.FragmentListFaskesBinding
import com.example.perludilindungi.models.Faskes
import com.example.perludilindungi.ui.faskes.FaskesFragmentDirections
import com.example.perludilindungi.ui.faskes.FaskesRecyclerViewAdapter

class ListFaskesFragment: Fragment() {

    private var _binding: FragmentListFaskesBinding? = null
    private lateinit var viewModel: ListFaskesViewModel
    private val binding get() = _binding!!


    override fun onResume() {
        super.onResume()
        setupRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val data = arguments?.getSerializable("FASKES") as ArrayList<Faskes>

        val viewModelFactory = ListFaskesViewModelFactory(data)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[ListFaskesViewModel::class.java]

        _binding = FragmentListFaskesBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.listFaskesRecyclerView
        val adapter = FaskesRecyclerViewAdapter(viewModel.daftarFaskes)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object :
            FaskesRecyclerViewAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                val action = FaskesFragmentDirections
                    .actionNavigationFaskesToDetailFaskesFragment(
                        viewModel.daftarFaskes[position]
                    )
                Navigation.findNavController(binding.root)
                    .navigate(action)
            }
        })
        recyclerView.setHasFixedSize(true)
    }

}