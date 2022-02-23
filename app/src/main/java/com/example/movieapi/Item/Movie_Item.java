package com.example.movieapi.Item;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapi.Data.Movie_Data;
import com.example.movieapi.R;

import java.io.InputStream;
import java.net.URL;

public class Movie_Item extends RecyclerView.ViewHolder {

    Bitmap bitmap;

    ImageView imageView;


    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;



    //이미지 url
    Handler handler = new Handler();



    public Movie_Item(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.movie_img);

//        img = itemView.findViewById(R.id.movie_img);

        textView = itemView.findViewById(R.id.movie_title);
        textView2 = itemView.findViewById(R.id.movie_subtitle);
        textView3 = itemView.findViewById(R.id.movie_actor);
        textView4 = itemView.findViewById(R.id.movie_director);
        textView5 = itemView.findViewById(R.id.movie_rating);
        textView6 = itemView.findViewById(R.id.movie_date);
        textView7 = itemView.findViewById(R.id.movie_link);

    }

    public void setItem(Movie_Data item) {

        //이미지 url을 가져오기 위해 Thread사용
         Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(item.image != null){
                        URL url = new URL(item.image);
                        InputStream instream = url.openStream();
                        bitmap = BitmapFactory.decodeStream(instream);
                        handler.post(new Runnable() {//외부쓰레드에서 메인 UI에 접근하기 위해 Handler를 사용
                            @Override
                            public void run() { // 화면에 그려줄 작업
                                imageView.setImageBitmap(bitmap);


                                //이미지 사이즈 조정
                                Glide.with(imageView)

                                        .load(item.image)

                                        .override(500, 500)

                                        .into(imageView);

                            }
                        });
                    }else{
                        imageView.setImageResource(R.drawable.movie);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


        textView.setText(item.getTitle());
        textView2.setText(item.getSubtitle());
        textView3.setText(item.getActor());
        textView4.setText(item.getDirector());
        textView5.setText(item.getUserRating());
        textView6.setText(item.getPubDate());
        textView7.setText(item.getLink());

    }
}
