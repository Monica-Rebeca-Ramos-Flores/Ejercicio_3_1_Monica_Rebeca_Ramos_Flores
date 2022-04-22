package com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores.Configuracion.SQLiteConexion;
import com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores.Configuracion.bdTransaccion;
import com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores.Configuracion.empleados;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    Button btnNuevoEmp;
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<empleados> lista;
    ArrayList<String> ArregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);



        //llenar la lista
        conexion=new SQLiteConexion(this, bdTransaccion.NameDatabase, null, 1);
        listapersonas=(ListView) findViewById(R.id.listaempleado);

        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter( this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);

        //doble click
        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                empleados mostrarr = lista.get(i);

                Intent intent = new Intent(ListaActivity.this, UpdateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("empleado", mostrarr);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        // Para ir a agregar un nuevo empleado
        btnNuevoEmp = (Button) findViewById(R.id.btnNuevoEmpleado);
        btnNuevoEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ObtenerListaEmpleados() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        empleados list_personas = null;
        lista = new ArrayList<empleados>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + bdTransaccion.tablaempleados, null);

        //Iterando la informacion del cursor

        while (cursor.moveToNext()) {
            list_personas = new empleados();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombre(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getString(3));
            list_personas.setDireccion(cursor.getString(4));
            list_personas.setPuesto(cursor.getString(5));
            lista.add(list_personas);
        }
        cursor.close();

        filllist();


    }

    private void filllist() {
        ArregloPersonas = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++) {
            ArregloPersonas.add(lista.get(i).getId() + " | "
                    + lista.get(i).getNombre() + " "
                    + lista.get(i).getApellidos() + " || "
                    + lista.get(i).getPuesto());
        }
    }
}