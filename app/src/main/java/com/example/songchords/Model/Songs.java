package com.example.songchords.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Songs {
    @SerializedName("Name")
    private String Name;
    @SerializedName("Artist")
    private String Artist;
    @SerializedName("URL")
    private String URL;
    @SerializedName("Lyric")
    private ArrayList<String> lyrics;
    @SerializedName("Capo")
    private String Capo;

    public Songs(String name, String artist, String URL, ArrayList<String> lyrics, String capo) {
        Name = name;
        Artist = artist;
        this.URL = URL;
        this.lyrics = lyrics;
        Capo = capo;
    }

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

}
