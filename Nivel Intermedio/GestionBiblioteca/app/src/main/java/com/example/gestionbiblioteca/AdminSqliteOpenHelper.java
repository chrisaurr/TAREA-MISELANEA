package com.example.gestionbiblioteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSqliteOpenHelper extends SQLiteOpenHelper {

    public AdminSqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tablasPorDefecto(db);
        llenarTablas(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS libro");
        onCreate(db);
    }

    public void tablasPorDefecto(SQLiteDatabase db){
        final String usuario = "CREATE TABLE usuario(id varchar(20) primary key, nombres varchar(50), apellidos varchar(50))";
        final String libro = "CREATE TABLE libro(id varchar(20) primary key, nom_libro varchar(30), autor varchar(50), " +
                "id_usuario varchar(20), disponible boolean, " +
                "FOREIGN KEY (id_usuario) REFERENCES usuario(id))";
        db.execSQL(usuario);
        db.execSQL(libro);
    }

    public void llenarTablas(SQLiteDatabase db){
        final String usuario1 = "insert into usuario values('1', 'Mario Jose', 'Rojas Garcia')";
        final String usuario2 = "insert into usuario values('2', 'Carlos Roberto', 'Miranda Lopez')";
        final String usuario3 = "insert into usuario values('3', 'Marta Julia', 'Ramirez Gutierrez')";

        final String libro1 = "insert into libro values('1', 'Clean Code', 'Robert C. Martin', '1', true)";
        final String libro2 = "insert into libro values('2', 'Code Complete', 'Steve McConnell', '1', true)";
        final String libro3 = "insert into libro values('3', 'Soft Skills', 'John Sonmez', '1', true)";

        db.execSQL(usuario1);
        db.execSQL(usuario2);
        db.execSQL(usuario3);
        db.execSQL(libro1);
        db.execSQL(libro2);
        db.execSQL(libro3);
    }


}
