package repositories;

import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectClientes {
    public static Integer clienteByName(String nombre){
        Integer idCliente = 0;
        String selectCliente = "select * from clientes";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        try (Connection con =conexionBD.connect()){
            pst = con.prepareStatement(selectCliente);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("nombre").contains(nombre)){
                    idCliente = rs.getInt("id_cliente");
                }
            }
        } catch (SQLException e) {
            idCliente = 0;
        }
        return idCliente;
    }
}
