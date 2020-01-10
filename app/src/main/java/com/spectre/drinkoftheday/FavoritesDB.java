package com.spectre.drinkoftheday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;

public class FavoritesDB extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Favoris.db";
    public static final String TABLE_NAME = "Favoris";
    public static final String COLUMN_ID = "Drink Name";
    public static final String TMP = "Select*FROM";
        //initialize the database

    public FavoritesDB(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_TABLE = new String("CREATE TABLE" + TABLE_NAME + "(" + COLUMN_ID + "INTEGER PRIMARYKEY," + "TEXT )");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler(){
    String result = "";
    String query = TMP + TABLE_NAME;
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Drink drink) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, drink.getDrinkName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow(TABLE_NAME, null, values);
        db.close();
    }


    public boolean updateHandler(String drink) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, drink);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + drink, null) > 0;
    }





}
