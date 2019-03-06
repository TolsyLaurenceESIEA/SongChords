package com.example.songchords;

import com.google.gson.annotations.SerializedName;

public class Songs {
    @SerializedName("name")
    public String Name;
    @SerializedName("title")
    public String Artist;
    @SerializedName("URL")
    public String URL;
/*
    public Songs(String name, String artist, String URL) {
        Name = name;
        Artist = artist;
        this.URL = URL;
    }
*/
    public String getName() {
        return Name;
    }

    public String getArtist() {
        return Artist;
    }

    public String getURL() {
        return URL;
    }
}
