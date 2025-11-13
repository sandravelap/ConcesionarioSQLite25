package repositories;

import DAOs.Venta;
import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarVentasDB {
    public static boolean insertVenta(Venta venta) {
        Boolean respuesta;
        String insertVentaString = "INSERT INTO ventas VALUES (?,?,?,?,?)";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        try (Connection con =conexionBD.connect()){
            pst = con.prepareStatement(insertVentaString);
            pst.setInt(1,venta.getIdVenta());
            pst.setInt(2,venta.getIdCoche());
            pst.setInt(3,venta.getIdCliente());
            pst.setDate(4,venta.getFechaVenta());
            pst.setDouble(5,venta.getPrecioFinal());
            pst.executeUpdate();
            respuesta = true;
        } catch (SQLException e) {
            respuesta = false;
        }
        return respuesta;
    }
    public static boolean insertVentaNueva(Venta venta) throws SQLException {
        Boolean respuesta = false;
        String insertVentaString = "INSERT INTO ventas (id_coche, id_cliente, fecha_venta, precio_final) VALUES (?,?,?,?)";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        Connection con =conexionBD.connect();
        pst = con.prepareStatement(insertVentaString);
        pst.setInt(1,venta.getIdCoche());
        pst.setInt(2,venta.getIdCliente());
        pst.setDate(3,venta.getFechaVenta());
        pst.setDouble(4,venta.getPrecioFinal());
        pst.executeUpdate();
        respuesta = true;
        return respuesta;
    }
}
