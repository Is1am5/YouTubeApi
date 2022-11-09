package com.example.youtubeapi.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.Constant
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.model.PlayLists
import com.example.youtubeapi.remote.ApiService
import com.example.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel(val API_KEY: String) : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playLists() : LiveData<PlayLists> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<PlayLists>  {
        val data = MutableLiveData<PlayLists>()

        apiService.getPlaylists(Constant.part , Constant.channelId , API_KEY , 50).enqueue(object:
            Callback<PlayLists> {
            override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<PlayLists>, t: Throwable) {

            }

        })
        return data
    }
}