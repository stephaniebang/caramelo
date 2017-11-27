package com.example.android.caramelo;


public class Usuario {
    String usuario;
    String senha;

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuário: " + usuario + "   Senha: " + senha;
    }
}
