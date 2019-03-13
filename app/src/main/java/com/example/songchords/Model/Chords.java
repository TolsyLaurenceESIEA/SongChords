package com.example.songchords.Model;

import com.google.gson.annotations.SerializedName;

public class Chords {
    @SerializedName("Name")
    public String Name;
    @SerializedName("URL")
    public String URL;

    public Chords(String name, String URL) {
        Name = name;
        this.URL = URL;
    }

    public String getName() {
        return Name;
    }

    public String getURL() {
        return URL;
    }
}
