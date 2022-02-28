package com.example.movieapi.Tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapi.DB.DataBaseHelper;
import com.example.movieapi.Data.Movie_Data;
import com.example.movieapi.MovieAdapter;
import com.example.movieapi.R;

import java.util.ArrayList;


public class Tab2 extends Fragment {

    RecyclerView recyclerView;

    DataBaseHelper dbHelper;

    MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_tab2, container, false);

        dbHelper = new DataBaseHelper(this.getContext());

        ArrayList<Movie_Data> result = dbHelper.movieQuery();

        recyclerView = rootView.findViewById(R.id.tab2_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //어뎁터 객체 생성
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        //setItems메소드를 어뎁터랑 연결
        movieAdapter.setItems(result);


        //찜목록 삭제 버튼 이벤트
        movieAdapter.setOnItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClickListener(RecyclerView.ViewHolder holder, View view, int position) {
                Movie_Data item = movieAdapter.getItem(position);

                dbHelper.deleteRecord(item.getTitle());

                Toast.makeText(getContext(), "찜 목록 해제 : " + item.getTitle(), Toast.LENGTH_LONG).show();
            }
        });





        return rootView;
    }
}