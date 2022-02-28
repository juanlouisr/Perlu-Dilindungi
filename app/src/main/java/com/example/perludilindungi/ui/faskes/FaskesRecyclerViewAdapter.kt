package com.example.perludilindungi.ui.faskes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.models.Faskes
import com.example.perludilindungi.ui.berita.NewsAdapter
import com.google.android.material.card.MaterialCardView

class FaskesRecyclerViewAdapter(private val daftarFaskes: List<Faskes>) :
    RecyclerView.Adapter<FaskesRecyclerViewAdapter.FaskesAdapterViewHolder>() {

    private lateinit var mListener: OnItemClickListener


    class FaskesAdapterViewHolder(private val view: View, listener: OnItemClickListener) : RecyclerView
    .ViewHolder(view) {
        val faskesCard: MaterialCardView = view.findViewById(R.id.faskes_card)
        val namaFaskes: TextView = view.findViewById(R.id.tview_nama_faskes)
        val jenisFaskes: TextView = view.findViewById(R.id.tview_jenis_faskes)
        val alamatFaskes: TextView = view.findViewById(R.id.tview_alamat_faskes)
        val notelpFaskes: TextView = view.findViewById(R.id.tview_telp_faskes)
        val kodeFaskes: TextView = view.findViewById(R.id.tview_kode_faskes)

        init {
            view.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener{
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FaskesAdapterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R
            .layout.faskes_card,parent,false)
        return FaskesAdapterViewHolder(adapterLayout,mListener)
    }

    override fun onBindViewHolder(
        holder: FaskesAdapterViewHolder,
        position: Int
    ) {
        val item = daftarFaskes[position]
        holder.namaFaskes.text = item.nama
        if (item.jenis_faskes == "") {
            val layoutParams = holder.jenisFaskes.layoutParams
            layoutParams.width = 0
            holder.jenisFaskes.width = layoutParams.width
        }
        holder.jenisFaskes.text = item.jenis_faskes
        holder.alamatFaskes.text = item.alamat
        item.telp?.let {
            holder.notelpFaskes.text = "Telp : " + item.telp
        }
        holder.kodeFaskes.text = "Kode : " + item.kode
    }

    override fun getItemCount(): Int {
        return daftarFaskes.size
    }


}