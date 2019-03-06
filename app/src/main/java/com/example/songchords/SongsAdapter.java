package com.example.songchords;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {

    private List<Songs> songsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, artist;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title);
            artist = (TextView) view.findViewById(R.id.artist);
            image = view.findViewById(R.id.image);
        }
    }


    public SongsAdapter(Context context, List<Songs> SongsList) {
        this.songsList = SongsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.songdescription, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Songs songs = songsList.get(position);
        holder.name.setText(songs.getName());
        holder.artist.setText(songs.getArtist());

        Glide.with(context)
                .load(songs.getURL())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
}