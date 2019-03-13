package com.example.songchords.View;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.songchords.Controller.ChordsAdapter;
import com.example.songchords.Controller.Controller;
import com.example.songchords.Controller.TouchListener;
import com.example.songchords.Model.Chords;
import com.example.songchords.R;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    ArrayList<String> StringArray = new ArrayList<>();
    private List<Chords> chordsList;
    private ChordsAdapter chordsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_chord);

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
        Intent intent = getIntent();
        chordsList = (List<Chords>) intent.getSerializableExtra("chords");

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

        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        // define an adapter

        recyclerView.setAdapter(chordsAdapter);

        recyclerView.addOnItemTouchListener(new TouchListener(getApplicationContext(), recyclerView, new TouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Chords chords = chordsList.get(position);
                Intent intent = new Intent(TabActivity.this, ChordsActivity.class);
                //putExtraItems(intent,chords);
                startActivity(intent);
            }
        }));

    }
    /*public void putExtraItems(Intent intent, Chords chords){
        intent.putExtra("chord_name", chords.getName());
        intent.putExtra("chord_image", chords.getURL());
    }*/

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
