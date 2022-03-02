package com.example.perludilindungi.ui.bookmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.CreateDBApplication
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.Faskes
import com.example.perludilindungi.models.VaksinInfo
import com.example.perludilindungi.room.BookmarkFaskes
import com.example.perludilindungi.room.BookmarkFaskesViewModel
import com.example.perludilindungi.room.BookmarkFaskesViewModelFactory
import com.example.perludilindungi.ui.detailfaskes.DetailFaskesFragment
import com.example.perludilindungi.ui.list_faskes.ListFaskesFragmentFactory
import kotlinx.coroutines.flow.collectLatest

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: BookmarkViewModel

    private val daoViewModel: BookmarkFaskesViewModel by activityViewModels {
        BookmarkFaskesViewModelFactory(
            (activity?.application as CreateDBApplication).database.bookmarkFaskesDao()
        )
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(BookmarkViewModel::class.java)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        daoViewModel.getAllBookmark()
        daoViewModel.bookmarks.observe(viewLifecycleOwner) {
            viewModel.daftarFaskes.clear()
            viewModel.daftarFaskes.addAll(it.map { bookmarkFaskes ->
                 bookmarkFaskesToFaskes(
                 bookmarkFaskes)
            }.toList())
        }

        setupRecyclerView()

        return root
    }

    private fun setupRecyclerView() {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTsx = fragmentManager?.beginTransaction()
        fragmentTsx?.replace(
            binding.fragmentContaionerRview.id,
            ListFaskesFragmentFactory().newFragment(viewModel.daftarFaskes,
                "Bookmark")
        )
        fragmentTsx?.commit()
        if (fragmentTsx == null) {
            Log.d("Debug", "Null fragmentTSX")
        }
    }

    private fun bookmarkFaskesToFaskes(bf: BookmarkFaskes): Faskes {
        return Faskes(
            bf.id, bf.kode, bf.nama, bf.kota, bf.provinsi, bf
                .alamat, bf.latitude, bf.longitude, bf.telp, bf.jenis_faskes, bf
                .kelas_rs, bf.status, ArrayList(), bf.source_data
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}