package com.example.retrofitmoviedb;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<Movie> movies;
    private Context context;

    public MyAdapter(ArrayList<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public ArrayList<Movie> getListItem(){
        return movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from( viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.i("onBindViewHolder", "true");
        final Movie movie = getListItem().get(i);

        Picasso.get().load(movie.getPosterLink()).into(viewHolder.posterImageView);

        viewHolder.judulTextView.setText(movie.getTitle());
        viewHolder.deskripsiTextView.setText(movie.getOverview());
        viewHolder.tanggalRilisTextView.setText(movie.getReleaseDate());



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public ImageView posterImageView;
        public TextView judulTextView;
        public  TextView deskripsiTextView;
        public TextView tanggalRilisTextView;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            posterImageView = (ImageView) itemView.findViewById(R.id.posterImageView);
            Log.i("view holder", "true");
            judulTextView = (TextView) itemView.findViewById(R.id.judulTextView);
            deskripsiTextView = (TextView) itemView.findViewById(R.id.deskripsiTextView);
            tanggalRilisTextView = (TextView) itemView.findViewById(R.id.tanggalRilisTextView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);



        }
    }


}
