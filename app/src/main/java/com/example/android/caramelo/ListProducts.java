package com.example.android.caramelo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListProducts extends AppCompatActivity {
    ProductData db;
    ListView listView;
    FeedReaderContract.ProductEntry tbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        db = new ProductData(this);
        listView = (ListView) findViewById(R.id.list_products);

        showProducts();
    }

    private void showProducts() {
        Cursor data = db.getAllData();
        ArrayList<String> list = new ArrayList<>();
        List<Produtos> produtos = new ArrayList<>();

        while (data.moveToNext()) {
            list.add(data.getString(data.getColumnIndexOrThrow(tbl.col_name)));
            list.add(data.getString(data.getColumnIndexOrThrow(tbl.col_quant)));
            list.add(data.getString(data.getColumnIndexOrThrow(tbl.col_descr)));
            list.add(data.getString(data.getColumnIndexOrThrow(tbl.col_price)));

            produtos.add(new Produtos(
                    data.getString(data.getColumnIndexOrThrow(tbl.col_name)),
                    data.getInt(data.getColumnIndexOrThrow(tbl.col_quant)),
                    data.getString(data.getColumnIndexOrThrow(tbl.col_descr)),
                    data.getDouble(data.getColumnIndexOrThrow(tbl.col_price))));
        }

        ArrayAdapter<Produtos> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);

        listView.setAdapter(adaptador);
    }
}
