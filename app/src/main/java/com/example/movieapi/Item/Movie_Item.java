package com.example.movieapi.Item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi.Data.Movie_Data;
import com.example.movieapi.R;

public class Movie_Item extends RecyclerView.ViewHolder {

//    ImageView imageView;

    TextView img;

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;


    public Movie_Item(@NonNull View itemView) {
        super(itemView);

//        imageView = itemView.findViewById(R.id.movie_img);

        img = itemView.findViewById(R.id.movie_img);

        textView = itemView.findViewById(R.id.movie_title);
        textView2 = itemView.findViewById(R.id.movie_subtitle);
        textView3 = itemView.findViewById(R.id.movie_actor);
        textView4 = itemView.findViewById(R.id.movie_director);
        textView5 = itemView.findViewById(R.id.movie_rating);
        textView6 = itemView.findViewById(R.id.movie_date);
        textView7 = itemView.findViewById(R.id.movie_link);

    }

    public void setItem(Movie_Data item) {

//        imageView.setImageResource(item.getImage());

        img.setText(item.getImage());

        textView.setText(item.getTitle());
        textView2.setText(item.getSubtitle());
        textView3.setText(item.getActor());
        textView4.setText(item.getDirector());
        textView5.setText(item.getUserRating());
        textView6.setText(item.getPubDate());
        textView7.setText(item.getLink());


    }
}
