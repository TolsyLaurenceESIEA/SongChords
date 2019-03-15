package com.example.songchords.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Songs implements Serializable {
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
    @SerializedName("Chords")
    public List<Chords> chords;


    public Songs(String name, String artist, String URL, ArrayList<String> lyrics, String capo, List<Chords> chords) {
        Name = name;
        Artist = artist;
        this.URL = URL;
        this.lyrics = lyrics;
        Capo = capo;
        this.chords = chords;
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

    public List<Chords> getChords() {
        return chords;
    }
}
