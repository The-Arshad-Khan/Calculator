package com.rianomusicskb.skbchat.modelClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class db_model extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="password_set";
    private static final int version=1;

    public db_model(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY="CREATE TABLE register(id INTEGER PRIMARY KEY AUTOINCREMENT ,password TEXT)";
        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS register");
        onCreate(db);
    }

    public boolean register_user(String pass1){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",pass1);

       long l= sqLiteDatabase.insert("register",null,contentValues);
        sqLiteDatabase.close();
       if(l>0){
           return true;
       }
       else {
           return false;
       }


    }
    boolean loggedin;
    public boolean login_user(String pass1){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM register WHERE  id='1'" ,null);
        if(cursor.moveToFirst()){
            loggedin= true;
        }
        else{
            loggedin =false;
        }
    return loggedin;
    }
    boolean loggedin1;
    public boolean login_user1(String data){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM register WHERE  password='"+data+"'" ,null);
        if(cursor.moveToFirst()){
            loggedin1= true;
        }
        else{
            loggedin1 =false;
        }
        return loggedin1;
    }
}
