package com.aplicacion.ejercicio_3_1_monica_rebeca_ramos_flores.Configuracion;

public class bdTransaccion {

    // Nombre de la base de datos
    public static final String NameDatabase = "DBEmpleados";

    //Nombre de la tabla en la base de datos
    public static final String tablaempleados = "empleados";

    //Creacion de los atributos
    public static final String id= "id";
    public static final String nombre="nombre";
    public static final String apellidos="apellidos";
    public static final String edad="edad";
    public static final String puesto="puesto";
    public static final String direccion="direccion";


    //Creacion de la tabla
    public static final String CREATE_TABLE_EMPLEADOS = "CREATE TABLE " + tablaempleados +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombre TEXT, apellidos TEXT, edad INTEGER, direccion TEXT, puesto TEXT)";

    public static final String DROPTABLEempleados ="DROP TABLE IF EXISTS empleados";
    public static final String SELECT_ALL_TABLE_EMPLEADOS = "SELECT * FROM " + tablaempleados;


}
