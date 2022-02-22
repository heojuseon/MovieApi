package com.example.movieapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.movieapi.Tab.Tab1;
import com.example.movieapi.Tab.Tab2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Tab1 tab1;
    Tab2 tab2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tab1 = new Tab1();
        tab2 = new Tab2();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, tab1).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.frameLayout, tab1).commit();


                        return true;

                    case R.id.tab2:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, tab2).commit();
                        return true;
                }
                return false;
            }
        });

    }
}