package com.example.songchords.Model;
import com.example.songchords.Model.SongsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SongsApi {

    @GET("songsapi.json")
    Call<SongsResponse> getAllSongs();
}
