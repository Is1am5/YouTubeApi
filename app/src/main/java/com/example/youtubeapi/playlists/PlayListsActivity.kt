package com.example.youtubeapi.playlists

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.example.youtubeapi.network.NetworkStatus
import com.example.youtubeapi.network.NetworkStatusHelper
import com.example.youtubeapi.playlists_detail.PlaylistDetailActivity

class PlayListsActivity : BaseActivity<ActivityMainBinding, PlaylistsViewModel>() {

    companion object {
        const val plaPDAId = "ID"
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initViewModel() {
        viewModel = ViewModelProvider(this)[PlaylistsViewModel::class.java]
        viewModel.playLists().observe(this) {
            binding.recyclerView.adapter = PlaylistsAdapter(it.items, this::onClick)
        }
    }

    override fun isConnection() {
        checkInternet()
    }

    private fun checkInternet() {
        NetworkStatusHelper(this).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.networkLayout.root.visibility = View.GONE
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.networkLayout.root.visibility = View.VISIBLE
            }
        }

    }

    private fun onClick(channelId: String) {
        Intent(this, PlaylistDetailActivity::class.java).apply {
            putExtra(plaPDAId, channelId)
            startActivity(this)
        }
    }
}