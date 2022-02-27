package com.example.movieapi.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {


    public static String dbName = "movie.db";
    public static int VERSION = 1;

    public DataBaseHelper(Context context) {
        //DataBaseHelper 생성자
        super(context, dbName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //테이블 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS movieTBL(id integer PRIMARY KEY AUTOINCREMENT, title TEXT, link TEXT, image TEXT, subtitle TEXT, pubDate TEXT, director TEXT, actor TEXT, userRating TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //테이블 업그레이드

        //int oldVersion : 이전 데이터베이스 버전
        //int newVersion : 새 데이터베이스 버전
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS movieTBL");
        }
    }




    public void insertRecord(String mtitle, String mlink, String mimage, String msubtitle, String mpubDate, String mdirector, String mactor, String muserRating){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("INSERT INTO movieTBL(title, link, image, subtitle, pubDate, director, actor, userRating) VALUES('" + mtitle + "','" + mlink + "','" + mimage + "','" + msubtitle + "','"+ mpubDate + "','" + mdirector + "','" + mactor + "','" + muserRating + "');");

        Log.d("추가", mtitle + "\t" + mlink + "\t" + mimage+ "\t" + msubtitle+ "\t" + mpubDate+ "\t" + mdirector + "\t" + mactor+ "\t" + muserRating);
    }
}