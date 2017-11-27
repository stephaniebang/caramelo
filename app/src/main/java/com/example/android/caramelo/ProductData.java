package com.example.android.caramelo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductData extends SQLiteOpenHelper{
    public static FeedReaderContract.ProductEntry tbl = new FeedReaderContract.ProductEntry();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "products.db";

    private static final String SQL_CREATES_ENTRIES = "CREATE TABLE " + tbl.TABLE_NAME + " (" +
            tbl._ID + " INTEGER PRIMARY KEY, " +
            tbl.col_name + " TEXT, " +
            tbl.col_quant + " INTEGER, " +
            tbl.col_descr + " TEXT, " +
            tbl.col_price + " DOUBLE)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + tbl.TABLE_NAME;

    public ProductData(Context context) {
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

    public void addData(String name, int quant, String descr, double price) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(tbl.col_name, name);
        values.put(tbl.col_quant, quant);
        values.put(tbl.col_descr, descr);
        values.put(tbl.col_price, price);

        db.insert(tbl.TABLE_NAME, null, values);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tbl.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        return data;
    }
}
