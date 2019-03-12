package com.example.songchords.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Songs {
    @SerializedName("Name")
    public String Name;
    @SerializedName("Artist")
    public String Artist;
    @SerializedName("URL")
    public String URL;
    @SerializedName("Lyric")
    public ArrayList<String> lyrics;
    @SerializedName("Capo")
    public String Capo;
    @SerializedName("Chords")
    public Chords chords;

    public String getName() {
        return Name;
    }

    public String getArtist() {
        return Artist;
    }

    public String getURL() {
        return URL;
    }

    public ArrayList<String> getLyrics() {
        return lyrics;
    }

    public String getCapo() {
        return Capo;
    }

    public Chords getChords() {
        return chords;
    }
}
