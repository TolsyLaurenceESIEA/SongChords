package com.example.songchords;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SongsApi {

    @GET("FireApi.json")
    Call<SongsResponse> getAllSongs();
}
