package com.example.ejerciciogson;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView lista;
    private SQLiteDatabase db;

    ArrayAdapter<Dato> datoAdapter;
    List<Dato> datoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //helper instanciar
        // sacar el Database, readable

        UsuariosSQLiteHelper usuariodb =
                new UsuariosSQLiteHelper(this, "Usuarios", null, 1);

        db = usuariodb.getReadableDatabase();



        Cursor c = db.rawQuery("SELECT nombre, email FROM Usuarios", null);
        if (c.moveToFirst()){
            do{
                String name = c.getString( 0);
                String email = c.getString(1);

            }while(c.moveToNext());
        }


        lista = findViewById(R.id.lista);
        datoAdapter = new DatoAdapter("name", "email");

        lista.setAdapter(datoAdapter);

        datoAdapter.add();


    }
}
