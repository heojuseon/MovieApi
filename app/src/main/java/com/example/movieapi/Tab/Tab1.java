package com.example.movieapi.Tab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapi.Movie;
import com.example.movieapi.MovieList;
import com.example.movieapi.R;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Tab1 extends Fragment {

    static RequestQueue requestQueue;

    Button btn3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_tab1, container, false);

        btn3 = rootView.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieRequest();

            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getContext());
        }


        return rootView;
    }

    private void movieRequest() {
        String url = "https://openapi.naver.com/v1/search/movie.json?query=love&display=30&start=1&genre=1";

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

            Movie movie = movieList.items.get(i);

            println(movie.title);
            println(movie.link);
            println(movie.image);
            println(movie.subtitle);
            println(movie.pubDate);
            println(movie.director);
            println(movie.actor);
            println(movie.userRating);

        }
    }

}