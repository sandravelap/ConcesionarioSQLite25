package repositories;

import DAOs.Coche;
import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarCochesDB {
    public static boolean insertCoche(Coche coche) {
        Boolean respuesta;
        String insertCocheString = "INSERT INTO coches VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        try (Connection con =conexionBD.connect()){
            pst = con.prepareStatement(insertCocheString);
            pst.setInt(1,coche.getIdCoche());
            pst.setString(2,coche.getMarca());
            pst.setString(3,coche.getModelo());
            pst.setShort(4,coche.getAnio());
            pst.setString(5,coche.getBastidor());
            pst.setDouble(6,coche.getPrecio());
            pst.setBoolean(7,coche.isDisponible());
            pst.executeUpdate();
            respuesta = true;
        } catch (SQLException e) {
            respuesta = false;
        }
        return respuesta;
    }
}
