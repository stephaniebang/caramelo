package com.example.android.caramelo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Teste extends AppCompatActivity {
    UserData db;
    private ListView listView;
    FeedReaderContract.UserEntry tbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        listView = (ListView) findViewById(R.id.list_data);
        db = new UserData(this);

        showItem();
    }

    public void showItem() {
        Cursor data = db.checkDatabase();
        ArrayList<String> list = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();

        while (data.moveToNext()) {
            list.add(data.getString(data.getColumnIndexOrThrow(tbl.col_name)));
            list.add(data.getString(data.getColumnIndexOrThrow(tbl.col_passw)));

            usuarios.add(new Usuario(
                    data.getString(data.getColumnIndexOrThrow(tbl.col_name)),
                    data.getString(data.getColumnIndexOrThrow(tbl.col_passw))));
        }

        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarios);

        listView.setAdapter(adaptador);
    }
}
