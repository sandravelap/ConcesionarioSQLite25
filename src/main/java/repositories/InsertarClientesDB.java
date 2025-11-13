package repositories;

import DAOs.Cliente;
import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarClientesDB {
    public static boolean insertCliente(Cliente cliente) {
        Boolean respuesta;
        String insertClienteString = "INSERT INTO clientes VALUES (?,?,?,?)";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        try (Connection con =conexionBD.connect()){
            pst = con.prepareStatement(insertClienteString);
            pst.setInt(1,cliente.getIdCliente());
            pst.setString(2,cliente.getNombre());
            pst.setString(3,cliente.getTelefono());
            pst.setString(4,cliente.getEmail());
            pst.executeUpdate();
            respuesta = true;
        } catch (SQLException e) {
            respuesta = false;
        }
        return respuesta;
    }
}
