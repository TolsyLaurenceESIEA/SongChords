package com.example.songchords.Model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongsResponse {
    @SerializedName("Songs")
    private List<Songs> songs;
    @SerializedName("Chords")
    private List<Chords> chords;

    public SongsResponse(List<Songs> songs, List<Chords> chords) {
        this.songs = songs;
        this.chords = chords;
    }

    public List<Songs> getSongs() {
        return songs;
    }

    public List<Chords> getChords() {
        return chords;
    }
}
