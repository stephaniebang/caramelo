package com.example.android.caramelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainUsuario extends AppCompatActivity {
    public static FeedReaderContract.ProductEntry tbl = new FeedReaderContract.ProductEntry();
    ProductData db;
    private EditText item_name;
    private EditText item_quant;
    private EditText item_descr;
    private EditText item_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuario);

        db = new ProductData(this);
        item_name = (EditText) findViewById(R.id.item_name);
        item_quant = (EditText) findViewById(R.id.item_quantity);
        item_descr = (EditText) findViewById(R.id.item_description);
        item_price = (EditText) findViewById(R.id.item_price);
    }

    public void addItemToShop(View view) {
        // TODO (1) Checar se sao numeros
        // TODO (2) Mudar entrada do preco
        db.addData(
                item_name.getText().toString(),
                Integer.parseInt(item_quant.getText().toString()),
                item_descr.getText().toString(),
                Double.parseDouble(item_price.getText().toString()));

        item_name.setText("");
        item_quant.setText("");
        item_descr.setText("");
        item_price.setText("");
    }

    public void showProducts(View view) {
        Intent intent = new Intent(this, ListProducts.class);
        startActivity(intent);
    }
}
