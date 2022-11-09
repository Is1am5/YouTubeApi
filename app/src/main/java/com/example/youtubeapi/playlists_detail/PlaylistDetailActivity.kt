package com.example.youtubeapi.playlists_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapi.network.NetworkStatus
import com.example.youtubeapi.network.NetworkStatusHelper
import com.example.youtubeapi.playlists.PlayListsActivity

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding, PlaylistsDetailViewModel>() {

    override fun inflateViewBinding(): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

    override fun isConnection() {
        NetworkStatusHelper(this).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.networkLayout.root.visibility = View.GONE
            } else {
                binding.networkLayout.root.visibility = View.VISIBLE
            }
        }
    }
    override fun initViewModel() {
        viewModel = ViewModelProvider(this)[PlaylistsDetailViewModel::class.java]
    }

    override fun initViews() {
        val channelId = intent.getStringExtra(PlayListsActivity.plaPDAId)
        Toast.makeText(this, channelId , Toast.LENGTH_SHORT).show()
    }
}