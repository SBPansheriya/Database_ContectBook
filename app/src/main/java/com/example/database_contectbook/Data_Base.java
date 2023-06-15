package com.example.database_contectbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Data_Base extends SQLiteOpenHelper {

    public Data_Base(@Nullable Context context) {
        super(context,"ContactBook",null,1);
        Log.d("TTT","Data_Base: Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qurey = "create table Contact (ID integer primary key autoincrement,NAME text,NUMBER text)";
        sqLiteDatabase.execSQL(qurey);
        Log.d("TTT","onCreate: Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addContact(String name, String number) {
        String qurey = "insert into Contact (NAME,NUMBER) values ('"+name+"','"+number+"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(qurey);
        Log.d("TTT", "addContact: data"+name);
        Log.d("TTT", "addContact: data1"+number);
    }

    public Cursor Displayed(){
        String all = "select * from Contact";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(all,null);
        return cursor;
    }

    public void updateContact(int id, String name, String number){
        String qurey = "update Contact set NAME='"+name+"',NUMBER='"+number+"'where ID="+id+"";
        SQLiteDatabase db = getWritableDatabase();
        Log.d("TTT", "addContact: update"+name);
        Log.d("TTT", "addContact: update"+number);
        db.execSQL(qurey);
    }

    public void deleteContact(int id){
        String qurey = "delete from Contact where ID="+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(qurey);
    }
}
