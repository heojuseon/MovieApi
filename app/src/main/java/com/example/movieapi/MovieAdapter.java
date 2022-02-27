package com.example.movieapi;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi.DB.DataBaseHelper;
import com.example.movieapi.Data.Movie_Data;
import com.example.movieapi.Item.Movie_Item;
import com.example.movieapi.Tab.OnMovieItemClickListener;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnMovieItemClickListener {

    ArrayList<Movie_Data> movieDataItems = new ArrayList<Movie_Data>();

    OnMovieItemClickListener listener;

    DataBaseHelper dbHelper;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie, parent, false);

        return new Movie_Item(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        Movie_Data movieData = movieDataItems.get(position);
        Movie_Data movie = (Movie_Data) movieData;
        Movie_Item movieItem = (Movie_Item) holder;
        movieItem.setItem(movie);

    }

    @Override
    public int getItemCount() {
        return movieDataItems.size();
    }

    public void setItems(ArrayList<Movie_Data> movieDataItems){
        this.movieDataItems = movieDataItems;
    }

    public void addItem(Movie_Data movieData) {
        movieDataItems.add(movieData);
    }


    public void setOnItemClickListener(OnMovieItemClickListener listener){

        this.listener = listener;
    }

    @Override
    public void onItemClickListener(RecyclerView.ViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClickListener(holder, view, position);
        }
    }

    public Movie_Data getItem(int position) {

        return movieDataItems.get(position);
    }
}
