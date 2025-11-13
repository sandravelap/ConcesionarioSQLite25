package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.IO.println;

public class ConexionBD {
    //definimos como constantes los datos de la conexión a la base de datos
    private static final String URL = "jdbc:sqlite:src/main/resources/concesionario.db";

    public static Connection connect(){
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            println("Conexión mal");
        }
        return conexion;
    }
}
