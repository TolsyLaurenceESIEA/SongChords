package com.example.songchords.View;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }

        final ImageButton button = findViewById(R.id.favorite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_song = new Intent(ResearchActivity.this, FavorisActivity.class);
                startActivity(intent_song);
            }
        });
    }

    private void doMySearch(String s) {

        /*Une fois la recherche trouvée, il faut afficher les elements de la list, utilisation de RecyclerView
        Attention, ne pas oublier de reprendre la meme structure que songsactivity pour la liste mais pas la même pour l'affichage de du recycler
        view étant donné qu'il y a la barre de recherche pour l'un et la place pour l'icone des favoris et recherche pour l'autre.f
        */
    }

    public void showList (final List<Songs> listSongs){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_research);
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
                Intent intent_song = new Intent(ResearchActivity.this, TabActivity.class);
                startActivity(intent_song);
            }
        }));

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
