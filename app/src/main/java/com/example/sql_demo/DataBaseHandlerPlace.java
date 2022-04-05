package com.example.sql_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandlerPlace extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Place";
    private static final String TABLE_PLACE = "names";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DataBaseHandlerPlace(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = " CREATE TABLE " + TABLE_PLACE +
                " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT " + " ) ";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_PLACE);
        onCreate(sqLiteDatabase);
    }

    void addPlace(Place place) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, place.getDiadiem());
        sqLiteDatabase.insert(TABLE_PLACE, null, values);
        sqLiteDatabase.close();
    }

    Place getPlace(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_PLACE, new String[]{KEY_ID, KEY_NAME}, KEY_ID + " =? "
                , new String[]{String.valueOf(id)}, null
                , null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Place place = new Place(cursor.getString(0), cursor.getString(1));
        return place;
    }
    public List<Place> getPlaces(){
        List<Place> placeList = new ArrayList<Place>();
        String query = "SELECT * FROM " + TABLE_PLACE;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                Place place = new Place();
                place.setSTT(cursor.getString(0));
                place.setDiadiem(cursor.getString(1));
                placeList.add(place);
            }while (cursor.moveToNext());
        }
        return placeList;
    }
    public int updatePlace(Place place){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,place.getDiadiem());
        return sqLiteDatabase.update(TABLE_PLACE, values, KEY_ID + " =? ",
                new String[]{String.valueOf(place.getSTT())});
    }
    public void deletePlace(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_PLACE, KEY_ID + " =? ",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }
}
