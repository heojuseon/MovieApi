package com.example.movieapi.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.movieapi.Data.Movie_Data;

import java.util.ArrayList;


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

        Log.d("추가 : ", mtitle + "\t" + mlink + "\t" + mimage+ "\t" + msubtitle+ "\t" + mpubDate+ "\t" + mdirector + "\t" + mactor+ "\t" + muserRating);
    }

    public ArrayList<Movie_Data> movieQuery(){

        ArrayList<Movie_Data> result = new ArrayList<Movie_Data>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM movieTBL", null);

        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++){
            //다음 결과 레코드로 넘기기
            cursor.moveToNext();

            String title = cursor.getString(1);
            String link = cursor.getString(2);
            String image = cursor.getString(3);
            String subtitle = cursor.getString(4);
            String pubDate = cursor.getString(5);
            String director = cursor.getString(6);
            String actor = cursor.getString(7);
            String userRating = cursor.getString(8);

            Log.d("조회 : ", title + "\t" + link + "\t" + image+ "\t" + subtitle+ "\t" + pubDate+ "\t" + director + "\t" + actor+ "\t" + userRating);

            Movie_Data data = new Movie_Data(title, link, image, subtitle, pubDate, director, actor, userRating);
            result.add(data);
        }
        cursor.close();

        return result;

    }

    public void deleteRecord(String mtitle){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM movieTBL WHERE title = '" + mtitle + "'");

        Log.d("삭제 : ", mtitle);
    }

}