package com.example.songchords;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Songs {
    @SerializedName("Name")
    public String Name;
    @SerializedName("Artist")
    public String Artist;
    @SerializedName("URL")
    public String URL;
    @SerializedName("Lyrics")
    private List<String> lyrics;

    public String getName() {
        return Name;
    }

    public String getArtist() {
        return Artist;
    }

    public String getURL() {
        return URL;
    }

    public List<String> getLyrics() {
        return lyrics;
    }
}
