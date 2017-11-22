package com.example.android.caramelo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    private EditText nUSP;
    private EditText senha;
    UserData db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        nUSP = (EditText) findViewById(R.id.editText_new_nUSP);
        senha = (EditText) findViewById(R.id.editText_new_password);
        db = new UserData(this);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveInfo(View view) {
        Cursor data = db.getData(nUSP.toString());

        if (data.moveToNext()) {
            Toast.makeText(this, "Esse número usp já possui conta!", Toast.LENGTH_LONG).show();
        }

        else {
            db.addData(nUSP.toString(), senha.toString());

            nUSP.setText("");
            senha.setText("");

            Intent intent = new Intent(this, MainUsuario.class);
            startActivity(intent);
        }
    }
}
