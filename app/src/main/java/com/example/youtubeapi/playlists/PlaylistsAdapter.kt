package com.example.youtubeapi.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.databinding.ItemListsBinding
import com.example.youtubeapi.extensions.load
import com.example.youtubeapi.model.Item

class PlaylistsAdapter(private var list : List<Item>, private val onClick: (pos : String) -> Unit?)
    : RecyclerView.Adapter<PlaylistsAdapter.MainViewHolder>() {


    inner class MainViewHolder(private val binding: ItemListsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(items: Item) {
            val imageUrl = items.snippet.thumbnails.default.url
            val playlistsTitle = items.snippet.title
            val numberOfVideos = items.contentDetails.itemCount
            val displayNumber = String.format(itemView.context.getString(R.string.video_series) , numberOfVideos)

            binding.imagePlaylists.load(imageUrl)
            binding.tvDesc.text = playlistsTitle
            binding.tvSer.text = displayNumber

            itemView.setOnClickListener {
                onClick(items.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemListsBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}