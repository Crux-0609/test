package com.example.myapplication1.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication1.User;

public class UserService {
    private DatabaseHelper dbHelper;
    public UserService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean login(String id,String password,int i){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql;
        if(i==0){
            sql="select id,password from user_student where id=? and password=?";
        }
        else {
            sql = "select id,password from user_teacher where id=? and password=?";
        }
        Cursor cursor=sdb.rawQuery(sql, new String[]{id,password});

        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    public boolean register(User user,int j){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql;
        if(j==0){
            sql="insert into user_student(id,username,password) values(?,?,?)";
        }
        else {
            sql="insert into user_teacher(id,username,password) values(?,?,?)";
        }
        Object obj[]={user.getId(),user.getUsername(),user.getPassword()};
        sdb.execSQL(sql, obj);
        return true;
    }
}
