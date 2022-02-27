package com.example.movieapi.Tab;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public interface OnMovieItemClickListener {

    public void onItemClickListener(RecyclerView.ViewHolder holder, View view, int position);

}
