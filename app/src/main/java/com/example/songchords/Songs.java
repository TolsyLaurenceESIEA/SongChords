package com.example.songchords;

import com.google.gson.annotations.SerializedName;

public class Songs {
    @SerializedName("Name")
    public String Name;
    @SerializedName("Artist")
    public String Artist;
    @SerializedName("URL")
    public String URL;

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
