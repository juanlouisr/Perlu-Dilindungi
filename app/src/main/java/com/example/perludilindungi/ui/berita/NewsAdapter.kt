package com.example.perludilindungi.ui.berita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perludilindungi.R
import com.example.perludilindungi.models.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val list: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun oncClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener

    }

    inner class NewsViewHolder(ItemNews: View, listener: onItemClickListener): RecyclerView.ViewHolder(ItemNews){
        fun bind(newsResponse: News){
            with(itemView){
                tvTitle.text = newsResponse.title
                tvDate.text = newsResponse.pubDate
                Glide.with(itemView)
                    .load(newsResponse.enclosure._url)
                    .into(ivImage)
            }

        }

        init {
            itemView.setOnClickListener {
                listener.oncClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}