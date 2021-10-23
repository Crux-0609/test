package com.example.myapplication1.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name1="test.db";
    static int dbVersion=1;
    public DatabaseHelper(Context context) {
        super(context, name1, null, dbVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        //String sql="create table user_student(id int primary key autoincrement,username varchar(20) not null,password varchar(20) not null,age int check(age<100 and age>0),gender varchar(2))";



        //改table user_student为xxx
        //学生：id 学号 username 姓名 password 密码
        String sql_s="create table user_student("+
                " id varchar(20) primary key ,"+
                " username varchar(20)  not null,"+
                " password varchar(20) not null)";

        db.execSQL(sql_s);
        //老师：id 编号 username 姓名 password 密码
        String sql_t="create table user_teacher("+
                " id varchar(20) primary key ,"+
                " username varchar(20) not null,"+
                " password varchar(20) not null)";

        db.execSQL(sql_t);

        //学生账号输入
        String sql_s1 = "insert into user_student(id ,username ,password)" +
                " values('B19010101','马冬梅','123456')";
        String sql_s2 = "insert into user_student(id ,username ,password)" +
                " values('B19010102','张三','111111')";
        String sql_s3 = "insert into user_student(id ,username ,password)" +
                " values('B19010103','李四','123456')";
        db.execSQL(sql_s1);
        db.execSQL(sql_s2);
        db.execSQL(sql_s3);


        //老师账号输入
        String sql_t1 = "insert into user_teacher(id ,username ,password)" +
                " values('150101','罗老师','123456')";
        String sql_t2 = "insert into user_teacher(id ,username ,password)" +
                " values('150102','王老师','111111')";
        String sql_t3 = "insert into user_teacher(id ,username ,password)" +
                " values('150103','刘老师','123456')";
        db.execSQL(sql_t1);
        db.execSQL(sql_t2);
        db.execSQL(sql_t3);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}