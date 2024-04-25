package com.juliana.entrega_final.controladores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.juliana.entrega_final.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroBD extends SQLiteOpenHelper implements I_LibroBD {
    Context contexto;
    private List<Libro>librosList = new ArrayList<>();

    public LibroBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE libros("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "titulo TEXT, "+
                "subtitulo TEXT, "+
                "isbn TEXT, "+
                "autor TEXT, "+
                "anio INTEGER, "+
                "precio REAL) ";
        db.execSQL(sql);
        String insert = "INSERT INTO libros VALUES (null,"+
                "'La paciente silenciosa',"+
                "'El thriller perfecto',"+
                "'9788466351935',"+
                "'Alex michaelides',2022,90000)";
        db.execSQL(insert);
        insert = "INSERT INTO libros VALUES (null,"+
                "'Una influencer muerta en paris',"+
                "'Brutalmente impactante y actual ',"+
                "'9788408286219',"+
                "'Blue jeans',2024,78000)";
        db.execSQL(insert);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public Libro elemento(int id) {
        return null;
    }

    @Override
    public Libro elemento(String title) {
        return null;
    }

    @Override
    public List<Libro> lista() {
        return null;
    }

    @Override
    public void agregar(Libro book) {

    }

    @Override
    public void actualizar(int id, Libro book) {

    }

    @Override
    public void borrar(int id) {

    }
}
