package repositories;

import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCoches {
    public static boolean anularDispoCocheById(Integer idCoche) throws SQLException {
        boolean respuesta = false;
        String actualizarEstado = "UPDATE coches SET disponible=0 WHERE id_coche=? "; // probamos que haga el update a false aunque podemos tener problemas porque es un tinyint
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        Connection con =conexionBD.connect();
        pst = con.prepareStatement(actualizarEstado);
        pst.setInt(1, idCoche); // hace referencia al int que en este caso es idCoche y lo que hará será buscar en la base de datos
        pst.executeUpdate(); // para que pueda actualizar
        respuesta = true;
        return respuesta;
    }
    public static boolean activarDispoCocheById(Integer idCoche) throws SQLException {
        boolean respuesta = false;
        String actualizarEstado = "UPDATE coches SET disponible=1 WHERE id_coche=? "; // probamos que haga el update a false aunque podemos tener problemas porque es un tinyint
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        Connection con =conexionBD.connect();
        pst = con.prepareStatement(actualizarEstado);
        pst.setInt(1, idCoche);
        pst.executeUpdate(); // para que pueda actualizar
        respuesta = true;
        return respuesta;
    }
}
