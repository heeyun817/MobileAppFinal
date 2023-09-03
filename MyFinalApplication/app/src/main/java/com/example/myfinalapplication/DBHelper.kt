package com.example.myfinalapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context:Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table todo_tbl ( _id integer primary key autoincrement, todo not null)")// 테이블명 (필드, 필드) //autoincrement 데이터가 생길 때마다 번호가 자동으로 부여됨
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}