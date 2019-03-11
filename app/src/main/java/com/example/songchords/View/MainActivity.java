package com.example.songchords.View;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.songchords.Controller.Controller;
import com.example.songchords.R;
import com.example.songchords.Model.Songs;
import com.example.songchords.Controller.SongsAdapter;
import com.example.songchords.Controller.TouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongsAdapter songsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songslist);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        controller = new Controller(this);
        controller.onCreate();
    }

    public void showList (final List<Songs> listSongs){

        songsAdapter = new SongsAdapter(this, listSongs);
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
                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                putExtraItems(intent,songs);
                startActivity(intent);
            }
        }));

    }
    public void putExtraItems(Intent intent, Songs songs){
        intent.putStringArrayListExtra("lyric", songs.getLyrics());//lyric
        intent.putExtra("title", songs.getName());
        intent.putExtra("image", songs.getURL());
        intent.putExtra("artist", songs.getArtist());
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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}