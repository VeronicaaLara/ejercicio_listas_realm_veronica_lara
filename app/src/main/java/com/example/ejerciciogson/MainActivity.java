package com.example.ejerciciogson;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

   private EditText nameET;
   private EditText emailET;
   private Button button;
   private ListView lista;

    ArrayAdapter<Dato> datoAdapter; // declarado
    List<Dato> datoList = new ArrayList<>();

    private Button basedatos;

    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.name);
        emailET = findViewById(R.id.email);



        UsuariosSQLiteHelper usuariodb =
                new UsuariosSQLiteHelper(this, "Usuarios", null, 1);

        db = usuariodb.getWritableDatabase();



       lista = findViewById(R.id.lista);

        datoAdapter = new DatoAdapter(this, datoList); // inicializamos el adaptador
       lista.setAdapter(datoAdapter); // añadimos el adaptador a la lista



        button = findViewById(R.id.boton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();

                Dato dato = new Dato(name, email);

                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("nombre", name);
                nuevoRegistro.put("email", email);
                db.insert("Usuarios", null, nuevoRegistro);

                datoList.add(dato);

                // adaptador, actualiza la lista con los cambios que se produzcan

                datoAdapter.notifyDataSetChanged();


            }
        });

        basedatos = findViewById(R.id.basedatos);

        basedatos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                //el boton lleva de la primera vista a la segunda
                Intent intent = new Intent (MainActivity.this, ListActivity.class);
                startActivity(intent);



            }
        });

    }


}

