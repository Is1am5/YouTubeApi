package com.example.youtubeapi.remote

import com.example.youtubeapi.model.PlayLists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists (
        @Query("part") part: String,
        @Query("channelId") channelId : String,
        @Query("key") apiKey : String,
        @Query("maxResults") muxResults: Int
    ): Call<PlayLists>
}