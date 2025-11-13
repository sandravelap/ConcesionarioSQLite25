package repositories;

import DAOs.Venta;
import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarVentasDB {
    public static boolean insertVentaNueva(Venta venta) throws SQLException {
        Boolean respuesta = false;
        String insertVentaString = "INSERT INTO ventas (id_coche, id_cliente, fecha_venta, precio_final) VALUES (?,?,?,?)";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        Connection con =conexionBD.connect();
        pst = con.prepareStatement(insertVentaString);
        pst.setInt(1,venta.getIdCoche());
        pst.setInt(2,venta.getIdCliente());
        pst.setString(3, String.valueOf(venta.getFechaVenta()));
        pst.setDouble(4,venta.getPrecioFinal());
        pst.executeUpdate();
        respuesta = true;
        return respuesta;
    }
}
