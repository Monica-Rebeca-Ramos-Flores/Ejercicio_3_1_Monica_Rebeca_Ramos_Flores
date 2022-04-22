package com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores.Configuracion.SQLiteConexion;
import com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores.Configuracion.bdTransaccion;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellidos, edad, direccion;
    EditText puesto;
    Button agregar,btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.txtNombres);
        apellidos =(EditText) findViewById(R.id.txtApellido);
        edad = (EditText) findViewById(R.id.txtEdad);
        direccion = (EditText) findViewById(R.id.txtDireccion);

        puesto = (EditText) findViewById(R.id.txtPuesto);

        agregar = (Button) findViewById(R.id.btnAgregarEmpleado);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!nombre.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edad.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty()) {
                    AgregarEmpleado();


                } else if (edad.getText().length()<2){
                    Toast.makeText(getApplicationContext(),"Ingrese una edad Ejemplo: 20 ", Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(getApplicationContext(),"Llene todos los datos ", Toast.LENGTH_LONG).show();
                }
            }

        });

        btnAtras = (Button) findViewById(R.id.btnBack);
        // volver a pagina principal
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AgregarEmpleado() {
        SQLiteConexion conexion=new SQLiteConexion(this, bdTransaccion.NameDatabase, null, 1);
        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(bdTransaccion.nombre, nombre.getText().toString());
        valores.put(bdTransaccion.apellidos, apellidos.getText().toString());
        valores.put(bdTransaccion.edad, edad.getText().toString());
        valores.put(bdTransaccion.puesto, puesto.getText().toString());
        valores.put(bdTransaccion.direccion, direccion.getText().toString());


        Long resultado= db.insert(bdTransaccion.tablaempleados, bdTransaccion.id, valores);
        Toast.makeText(getApplicationContext(),"Ingresado : Codigo : " + resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();
        LimpiarPantalla();

    }

    private void LimpiarPantalla() {
        nombre.setText("");
        apellidos.setText("");
        edad.setText("");
        direccion.setText("");

    }


}