package com.example.songchords;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongsResponse {
    @SerializedName("heroes")
    private List<Songs> heroes;

    public List<com.example.songchords.Songs> getSongs() {
        return heroes;
    }
}
