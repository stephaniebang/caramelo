package com.example.android.caramelo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText senha;
    UserData db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new UserData(this);
        login = (EditText) findViewById(R.id.editText_user);
        senha = (EditText) findViewById(R.id.editText_passw);
    }

    public void tryLogin(View view) {
        Usuario user = db.getData(login.getText().toString());

        // Se nao for encontrado o nUSP
        if (user == null) {
            Toast.makeText(this, "Usuário não existe!", Toast.LENGTH_LONG).show();

            return;
        }

        if (user.senha.equals(senha.getText().toString())) {
            Intent intent = new Intent(this, MainUsuario.class);
            startActivity(intent);
        }

        else {
            Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
        }
    }

    public void checarDatabase(View view) {
        Intent intent = new Intent(this, Teste.class);
        startActivity(intent);
    }

    public void goToSignin(View view) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
