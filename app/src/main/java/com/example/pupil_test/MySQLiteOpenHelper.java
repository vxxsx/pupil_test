package com.example.pupil_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //数据库版本号
    public static final String DB_NAME = "user";
    private Context context;
    //在SQLiteOpenHelper的子类当中，必须有该构造函数
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        //必须通过super调用父类当中的构造函数
        super(context, name, factory, version);
        this.context = context;
    }
    public MySQLiteOpenHelper(Context context, String name, int version)
    {
        this(context,name,null,version);
    }

    public static final String CREATE_TABLES = "create table subjects ("+
            "id integer primary key autoincrement,"+
            "subject text,"+
            "result text,"+
            "answer text)";

    //第一次创建数据库的时候回调该方法
    //当使用getReadableDatabase()方法获取数据库实例的时候, 如果数据库不存在, 就会调用这个方法;
    //作用：创建数据库表：将创建数据库表的 execSQL()方法 和 初始化表数据的一些 insert()方法写在里面;
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLES);
        Toast.makeText(context,"success databases",Toast.LENGTH_SHORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:db.execSQL(CREATE_TABLES);
        }
    }
}
