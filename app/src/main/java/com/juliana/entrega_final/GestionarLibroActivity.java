package com.juliana.entrega_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.juliana.entrega_final.controladores.LibroBD;
import com.juliana.entrega_final.modelos.Libro;

import java.util.Locale;

public class GestionarLibroActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    EditText txttitulo, txtsubtitulo, txtisbn, txtautor, txtaniopublicacion, txtprecio;
    int id;
    LibroBD libroBD;

    Button btnGuardar,btnActualizar,btnBorrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_libro);
        init();

    }


    private void init(){
        context = getApplicationContext();
        txttitulo =findViewById(R.id.ges_txttitulo);
        txtsubtitulo =findViewById(R.id.ges_txtsubtitulo);
        txtautor =findViewById(R.id.ges_txtautor);
        txtisbn =findViewById(R.id.ges_txtisbn);
        txtaniopublicacion =findViewById(R.id.ges_txtanio_publicacion);
        txtprecio =findViewById(R.id.ges_txtprecio);


        btnGuardar=findViewById(R.id.ges_btnguardar);
        btnActualizar= findViewById(R.id.ges_btnactualizar);
        btnBorrar= findViewById(R.id.ges_btnborrar);

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        if (bolsa != null) {
            id = bolsa.getInt("id");
        }
        if(id !=0){
            if (bolsa != null) {
                txttitulo.setText(bolsa.getString("titulo"));
            }
            if (bolsa != null) {
                txtsubtitulo.setText(bolsa.getString("subtitulo"));
            }
            if (bolsa != null) {
                txtautor.setText(bolsa.getString("autor"));
            }
            if (bolsa != null) {
                txtisbn.setText(bolsa.getString("isbn"));
            }
            if (bolsa != null) {
                int anioPublicacion = bolsa.getInt("anio_publicacion");
                txtaniopublicacion.setText(String.valueOf(anioPublicacion));
            }
            if (bolsa != null && txtprecio != null) {
                double precio = bolsa.getDouble("precio");
                txtprecio.setText(String.format(Locale.getDefault(), "%.2f", precio));
                btnGuardar.setEnabled(false);
            }
        }else{
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
        }
    }

    private void limpiarCampos(){
        id=0;
        txttitulo.setText( "" );
        txtsubtitulo.setText( "" );
        txtautor.setText( "" );
        txtisbn.setText( "" );
        txtaniopublicacion.setText( "" );
        txtprecio.setText( "" );
    }


    private Libro llenarDatosLibro(){
        Libro libro = new Libro();

        String t = txttitulo.getText().toString();
        String s = txtsubtitulo.getText().toString();
        String a = txtautor.getText().toString();
        String i = txtisbn.getText().toString();
        String anio = txtaniopublicacion.getText().toString();
        String precio = txtprecio.getText().toString();


        libro.setId( id );
        libro.setTitulo( t );
        libro.setSubtitulo( s );
        libro.setAutor( a );
        libro.setIsbn( i );
        libro.setAnioPublicacion( Integer.parseInt(anio) );
        libro.setPrecio( Double.parseDouble(precio) );

        return libro;
    }

    private void guardar(){
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        Libro libro = llenarDatosLibro();
        if (id == 0){
            libroBD.agregar(libro);
            Toast.makeText(context, "Guardado Nuevo OK", Toast.LENGTH_LONG).show();
        }else{
            libroBD.actualizar(id, libro);
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            Toast.makeText(context, "Actualizado Existente OK", Toast.LENGTH_LONG).show();
        }
    }


    private void borrar(){
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        Libro libro = llenarDatosLibro();
        if (id == 0){
            Toast.makeText(context, "No es posible borrar", Toast.LENGTH_LONG).show();
        }else{
            libroBD.borrar(id);
            limpiarCampos();
            btnGuardar.setEnabled(true);
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            Toast.makeText(context, "Se borro el registro", Toast.LENGTH_LONG).show();
        }
    }

    //FUNCION PARA QUE CUANDO DEN CLIC EN LOS BOTONES SE HAGAN LAS FUNCIONES
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.ges_btnguardar) {
            guardar();
        } else if (id == R.id.ges_btnactualizar) {
            guardar();
        } else if (id == R.id.ges_btnborrar) {
            borrar();
        }
    }


}