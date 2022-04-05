package com.example.sql_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandlerName extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Name";
    private static final String TABLE_NAME = "names";
    private static final String KEY_NAME ="name";

    public DataBaseHandlerName(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = " CREATE TABLE "
                + TABLE_NAME + " ( " + KEY_NAME + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //xoa bang cu neu no ton tai
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        //tao bang moi
        onCreate(sqLiteDatabase);
    }

    void addName(Name name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,name.getName());
        sqLiteDatabase.insert(TABLE_NAME,null, values);
        sqLiteDatabase.close();
    }
    public List<Name> getNames(){
        List<Name> nameList = new ArrayList<Name>();
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Name name = new Name();
                name.setName(cursor.getString(0));
                nameList.add(name);
            }while (cursor.moveToNext());
        }
        return nameList;
    }

    public void deleteName(Name name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,KEY_NAME + " like ? ",
                new String[] {name.getName()});
        sqLiteDatabase.close();
    }


}
