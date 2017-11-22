package com.example.android.caramelo;


import android.provider.BaseColumns;

public class FeedReaderContract {
    private FeedReaderContract() {};

    // Definicao do conteudo da table de usuarios
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String col_name   = "name";
        public static final String col_passw  = "passw";

        public UserEntry() {};
    }
}
