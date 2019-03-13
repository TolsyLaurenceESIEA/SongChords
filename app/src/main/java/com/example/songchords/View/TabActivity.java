package com.example.songchords.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.songchords.R;

import java.util.ArrayList;

public class TabActivity extends AppCompatActivity {

    ArrayList<String> StringArray = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        /*// Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        ArrayList<String> test = getIntent().getStringArrayListExtra("test");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.lyrictab);
        */

        //Intent intent = getIntent();

        String title = getIntent().getExtras().getString("title");
        TextView songTitle = findViewById(R.id.titletab);
        songTitle.setText(title);

        String image = getIntent().getExtras().getString("image");
        ImageView songImage = findViewById(R.id.imagetab);
        Glide.with(this)
                .load(image)
                .into(songImage);

        String artist = getIntent().getExtras().getString("artist");
        TextView songArtist = findViewById(R.id.artist);
        songArtist.setText(artist);


        ArrayList<String> lyrics = getIntent().getStringArrayListExtra("lyric");
        ArrayValueAddFunction(lyrics);
        TextView lyricsArray = findViewById(R.id.lyrictab);
        for (int i=0; i<StringArray.size();i++){
            lyricsArray.append(StringArray.get(i));
            lyricsArray.append("\n");
        }
    }

    private void ArrayValueAddFunction(ArrayList<String> list) {
        for(String s : list)
        {
            StringArray.add(s);
        }
    }
}
