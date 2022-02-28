package com.example.movieapi.Tab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapi.DB.DataBaseHelper;
import com.example.movieapi.Data.Movie_Data;
import com.example.movieapi.MovieAdapter;
import com.example.movieapi.MovieList;
import com.example.movieapi.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Tab1 extends Fragment {


    static RequestQueue requestQueue;

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    DataBaseHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_tab1, container, false);

        recyclerView = rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);


        dbHelper = new DataBaseHelper(getContext());

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getContext());
        }

        movieRequest();




        movieAdapter.setOnItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClickListener(RecyclerView.ViewHolder holder, View view, int position) {
                Movie_Data item = movieAdapter.getItem(position);

                //잘못된 접근 방식(movieDataItems가 사용되면 안됨)
//                if (movieDataItems.size() != 0){
//
//                    String title = movieDataItems.get(position).getTitle();
//                    String link = movieDataItems.get(position).getLink();
//                    String image = movieDataItems.get(position).getImage();
//                    String subtitle = movieDataItems.get(position).getSubtitle();
//                    String pubDate = movieDataItems.get(position).getPubDate();
//                    String director = movieDataItems.get(position).getDirector();
//                    String actor = movieDataItems.get(position).getActor();
//                    String userRating = movieDataItems.get(position).getUserRating();
//
//                    dbHelper.insertRecord(title, link, image, subtitle, pubDate, director, actor, userRating);
//                }

                //Movie_Data 의 item객체에 접근하여 insertRecord메소드 실행
                dbHelper.insertRecord(item.getTitle(), item.getLink(), item.getImage(), item.getSubtitle(), item.getPubDate(), item.getDirector(), item.getActor(), item.getUserRating());

                Toast.makeText(getContext(), "선택 : " + item.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    private void movieRequest() {
        String url = "https://openapi.naver.com/v1/search/movie.json?query=Crayon Shin-chan&display=30&start=1&genre=15";

        StringRequest request = new StringRequest(  //요청을 보내기 위한 StringRequest 객체 생성
                Request.Method.GET,
                url,
                new Response.Listener<String>() {   //응답을 잘 받았을 때 이 메소드가 자동으로 호출

                    @Override
                    public void onResponse(String response) {

                        println("response -> " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {  //에러 발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error -> " + error.getMessage());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();

                if (headers == null
                        || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }
                headers.put("X-Naver-Client-Id", "QuP2TiRYPHxfmdohw4R0");
                headers.put("X-Naver-Client-Secret", "In48PcSF46");

                return headers;
            }

        };


        request.setShouldCache(false);  //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        requestQueue.add(request);  //요청 큐의 add메소드
        println("요청 보냄.");
    }

    private void println(String s) {
        Log.d("Movie", s);
    }

    private void processResponse(String response) {
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class);
        for (int i = 0; i < movieList.items.size(); i++){
            Movie_Data movieData = movieList.items.get(i);

            movieAdapter.addItem(movieData);

//            println(movieData.title);
//            println(movieData.link);
////            println(movieData.image);
//            println(movieData.subtitle);
//            println(movieData.pubDate);
//            println(movieData.director);
//            println(movieData.actor);
//            println(movieData.userRating);

        }
        movieAdapter.notifyDataSetChanged();
    }

}