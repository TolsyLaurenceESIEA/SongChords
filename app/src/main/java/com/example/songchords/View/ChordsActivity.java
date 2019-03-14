package com.example.songchords.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.songchords.Model.Chords;
import com.example.songchords.R;

import java.util.ArrayList;
import java.util.List;

public class ChordsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords);

        String image = getIntent().getExtras().getString("chord_image");
        ImageView songImage = findViewById(R.id.chordurl);
        Glide.with(this)
                .load(image)
                .into(songImage);

    }
}
