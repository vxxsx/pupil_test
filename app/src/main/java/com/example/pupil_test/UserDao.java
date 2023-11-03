package com.example.pupil_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDao {
    private SQLiteOpenHelper helper;

    public UserDao(Context context) {
        helper = new MySQLiteOpenHelper(context, MySQLiteOpenHelper.DB_NAME,null,1);
    }

    //增加
    public long addSubject(Subject subject) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("subject", subject.getSubject());
        values.put("result", subject.getResult());
        values.put("answer", subject.getAnswer());
        long i = database.insert("subjects", null, values);
        database.close();
        return i;
    }
    //删除
    public int deleteSubject(int id) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(id)};
        int i = database.delete("subjects", "id=?", whereArgs);
        database.close();
        return i;
    }

    //修改
    public int updateSubject(Subject subject){
        //1.获取数据库对象
        SQLiteDatabase database=helper.getWritableDatabase();
        //那些列为空，可以设置为空
        ContentValues values=new ContentValues();
        //key是数据表的列名，value是要放进去的值
        values.put("result",subject.getResult());
        String[] whereArgs = {String.valueOf(subject.getId())};
        //第一个参数表明，第二个参数新数据，第三个参数是条件
        int i=database.update("subjects",values,"id=?",whereArgs);
        //关闭数据库
        database.close();
        return i;
    }

    //查询
    public ArrayList<Subject> queryAll() {
        ArrayList<Subject> list = new ArrayList<>();
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.query("subjects", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int subjectIndex = cursor.getColumnIndex("subject");
                int resultIndex = cursor.getColumnIndex("result");
                int answerIndex = cursor.getColumnIndex("answer");

                if (idIndex != -1 && subjectIndex != -1 && resultIndex != -1 && answerIndex != -1) {
                    int id = cursor.getInt(idIndex);
                    String subject = cursor.getString(subjectIndex);
                    double result = cursor.getDouble(resultIndex);
                    double answer = cursor.getDouble(answerIndex);
                    Subject subjectObj = new Subject(id, subject, result, answer);
                    list.add(subjectObj);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
