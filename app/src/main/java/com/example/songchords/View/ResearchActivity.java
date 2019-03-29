package com.example.songchords.View;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.songchords.Controller.Controller;
import com.example.songchords.Controller.SongsAdapter;
import com.example.songchords.Controller.TouchListener;
import com.example.songchords.Model.Chords;
import com.example.songchords.Model.Songs;
import com.example.songchords.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongsAdapter songsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String found;
    private boolean the_research;
    private List<Songs> result;
    private List<Songs> listSongs = new ArrayList<Songs>();
    private EditText search;
    private Button last_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);

        last_button =(Button) findViewById(R.id.last);
        search = (EditText) findViewById(R.id.research);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.getText().clear();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_research);

        Intent intent = getIntent();
        listSongs = (List<Songs>) intent.getSerializableExtra("songs");

        last_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_cache(v);
            }
        });


        doMySearch(listSongs);

        this.showList(listSongs);
    }

    private void doMySearch(List<Songs> s) {

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                found=s.toString().toLowerCase();

                if(the_research)
                {
                    result.clear();
                }
                if (found!=null)
                {
                    the_research=true;
                    loading();
                }
            }
        });

    }

    public void research()
    {
        result=new ArrayList<>();
        for(Songs song :listSongs)
        {
            if((song.getName().toLowerCase().contains(found))||(song.getArtist().toLowerCase().contains(found))){
                result.add(song);
            }
        }
    }

    public void loading()
    {
        if (the_research)
        {
            research();
            songsAdapter = new SongsAdapter(this, result);
            songsAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(songsAdapter);
            recyclerView.invalidate();
            recyclerView.removeAllViews();
        }
    }

    public void get_cache(View view)
    {
        SharedPreferences getcache = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        found = getcache.getString("last", "").toLowerCase();
        loading();
    }

    public void put_cache (String s)
    {
        SharedPreferences.Editor save_data= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        save_data.putString("last", s);
        save_data.apply();
    }

    public void showList (final List<Songs> listSongs){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_research);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        // define an adapter

        recyclerView.setAdapter(songsAdapter);

        recyclerView.addOnItemTouchListener(new TouchListener(getApplicationContext(), recyclerView, new TouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Songs songs = listSongs.get(position);
                Toast.makeText(getApplicationContext(), songs.getName(), Toast.LENGTH_SHORT).show();
                Intent intent_song = new Intent(ResearchActivity.this, TabActivity.class);
                putExtraItems(intent_song,songs);
                put_cache(songs.getName());
                startActivity(intent_song);
            }
        }));

    }

    public void putExtraItems(Intent intent, Songs songs){
        List<Chords> nameChordslist=new ArrayList<>();
        for(Chords c : songs.getChords())
        {
            nameChordslist.add(c);
        }
        intent.putStringArrayListExtra("lyric", songs.getLyrics());
        intent.putExtra("title", songs.getName());
        intent.putExtra("image", songs.getURL());
        intent.putExtra("artist", songs.getArtist());
        intent.putExtra("capo", songs.getCapo());
        intent.putExtra("chords", (Serializable) nameChordslist);
        intent.putExtra("songs",songs);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
