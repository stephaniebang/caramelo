package com.example.android.caramelo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserData extends SQLiteOpenHelper{
    public static FeedReaderContract.UserEntry tbl = new FeedReaderContract.UserEntry();
    public static final int    DATABASE_VERSION = 1;
    public static final String DATABASE_NAME    = "users.db";

    private static final String SQL_CREATES_ENTRIES =
            "CREATE TABLE " + tbl.TABLE_NAME + " (" +
                    tbl._ID + " INTEGER PRIMARY KEY, " +
                    tbl.col_name + " TEXT, " +
                    tbl.col_passw + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + tbl.TABLE_NAME;

    public UserData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATES_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addData(String name, String passw) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FeedReaderContract.UserEntry.col_name, name);
        values.put(FeedReaderContract.UserEntry.col_passw, passw);

        db.insert(tbl.TABLE_NAME, null, values);
    }

    public Usuario getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tbl.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        while (data.moveToNext()) {
            if (data.getString(data.getColumnIndexOrThrow(tbl.col_name)).equals(name))
                return new Usuario(
                        data.getString(data.getColumnIndexOrThrow(tbl.col_name)),
                        data.getString(data.getColumnIndexOrThrow(tbl.col_passw)));
        }

        return null;
    }

    public Cursor checkDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tbl.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}