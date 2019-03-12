package com.example.songchords.Model;

import com.google.gson.annotations.SerializedName;

class Chords {
    @SerializedName("Name")
    public String Name;
    @SerializedName("Accord")
    public String URL;

    public String getName() {
        return Name;
    }

    public String getURL() {
        return URL;
    }
}
