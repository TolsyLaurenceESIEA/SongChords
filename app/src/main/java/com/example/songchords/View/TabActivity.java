package com.example.songchords.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.songchords.Controller.ChordsAdapter;
import com.example.songchords.Controller.TouchListener;
import com.example.songchords.Model.Chords;
import com.example.songchords.Model.Songs;
import com.example.songchords.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    ArrayList<String> StringArray = new ArrayList<>();
    private List<Chords> chordsList = new ArrayList<Chords>();
    private ChordsAdapter chordsAdapter;
    private RecyclerView recyclerView;
    public HashMap<Songs,Integer> favList=new HashMap();
    private boolean fav_on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_chord);

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

        //attention ne fonctionne qu'au démarrage, doit le faire en continue...
        final ImageButton button_fav = findViewById(R.id.fav_tab);
        button_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fav_on ==false) {
                    button_fav.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favoris_or));
                    fav_on = true;
                }
                else
                {
                    button_fav.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favoris));
                    fav_on = false;
                }
            }
        });


        ArrayList<String> lyrics = getIntent().getStringArrayListExtra("lyric");
        ArrayValueAddFunction(lyrics);
        TextView lyricsArray = findViewById(R.id.lyrictab);
        for (int i=0; i<StringArray.size();i++){
            lyricsArray.append(StringArray.get(i));
            lyricsArray.append("\n");
        }
        Intent intent = getIntent();
        chordsList = (List<Chords>) intent.getSerializableExtra("chords");

        this.showList(chordsList);

        //while ()

    }

    private void ArrayValueAddFunction(ArrayList<String> list) {
        for(String s : list)
        {
            StringArray.add(s);
        }
    }

    public void showList (final List<Chords> chordsList){

        chordsAdapter = new ChordsAdapter(this, chordsList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(chordsAdapter);

        recyclerView.addOnItemTouchListener(new TouchListener(getApplicationContext(), recyclerView, new TouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Chords chords = chordsList.get(position);
                Intent intent = new Intent(TabActivity.this, ChordsActivity.class);
                putExtraItems(intent,chords);
                startActivity(intent);
            }
        }));

    }
    public void putExtraItems(Intent intent, Chords chords){
        intent.putExtra("chord_image", chords.getURL());
    }
}
