package com.example.songchords;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongsResponse {
    @SerializedName("Songs")
    private List<Songs> songs;

    public List<Songs> getSongs() {
        return songs;
    }
}
