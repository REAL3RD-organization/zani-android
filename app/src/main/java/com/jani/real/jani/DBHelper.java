package com.jani.real.jani;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JeongMinCha on 2016. 2. 3..
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper (Context context, String name, CursorFactory factory, int version) {
        super(context, "friends.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table processing_sever (access_token TEXT);");
        db.execSQL("create table friends (name TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists friends");
    }

    public void deleteFriend(String name) {
        this.getWritableDatabase().execSQL("delete from friends where name = '" + name + "';");
    }

    public void addFriend(String name) {
        this.getWritableDatabase().execSQL("insert into friends (name) values ('" + name + "');");
    }

    public List<String> getFriendList() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select name from friends", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            list.add(name);
        }
        return list;
    }

    public void saveAccessToken(String token) {
        this.getWritableDatabase().execSQL("update processing_server set access_token = '" + token + "' where true;");
    }

    public String getAccessToken() {
        String token = "";
        Cursor cursor = this.getReadableDatabase().rawQuery("select access_token from processing_server", null);
        while(cursor.moveToNext()) {
            token = cursor.getString(0);
        }
        return token;
    }
}
