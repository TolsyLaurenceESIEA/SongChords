package com.example.songchords.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.songchords.Model.Chords;
import com.example.songchords.R;

import java.util.List;

public class ChordsAdapter extends RecyclerView.Adapter<ChordsAdapter.MyViewHolder> {

        private List<Chords> chordsList;
        private Context context;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public Button name;

            public MyViewHolder(View view) {
                super(view);
                name = (Button) view.findViewById(R.id.chordsName);
            }
        }

        public ChordsAdapter(Context context, List<Chords> chordsList) {
            this.chordsList = chordsList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_tab, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Chords chords = chordsList.get(position);
            holder.name.setText(chords.getName());
        }

        @Override
        public int getItemCount() {
            return chordsList.size();
        }

}
