package com.se391.davidlandi.landifinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Landi on 5/23/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "members.db";
    private static final String TABLE_NAME = "members";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_LOGIN_NAME = "loginName";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table members (id integer primary key not null, " +
            "name text not null, email text not null, loginName text not null, password text not null);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }
    public void insertMember(Member data){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, queryRowCount()+1);
        values.put(COLUMN_NAME, data.getUsrName());
        values.put(COLUMN_EMAIL, data.getUsrEmail());
        values.put(COLUMN_LOGIN_NAME, data.getUsrLoginName());
        values.put(COLUMN_PASSWORD, data.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int queryRowCount(){
        String query = "select * from members";
        int count;
        Cursor cursor = db.rawQuery(query, null);
        count = cursor.getCount();
        return count;
    }

    public String queryTotalMembers(){
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        String result = String.valueOf(cursor.getCount());
        return result;
    }

    public List<Member> getAllMembers(){
        List<Member> memberList = new ArrayList<Member>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Member member = new Member();
                member.setUsrID(Integer.parseInt(cursor.getString(0)));
                member.setUsrName(cursor.getString(1));
                member.setUsrEmail(cursor.getString(2));
                member.setUsrLoginName(cursor.getString(3));
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        return memberList;
    }

    public String queryPassword(String userName){
        db = this.getReadableDatabase();
        String query = "select loginName, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "No match in database";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                b = cursor.getString(1);
                if(a.equals(userName)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
