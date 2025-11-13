package repositories;

import DAOs.Venta;
import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectVentas {
    public static Venta selectVentaById(Integer idVenta){
        Venta venta = new Venta();
        String selectCliente = "select * from ventas where id_venta = ?";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        try (Connection con =conexionBD.connect()){
            pst = con.prepareStatement(selectCliente);
            pst.setInt(1,idVenta);
            ResultSet rs = pst.executeQuery();
            while (rs.next ()){
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setIdCoche(rs.getInt("id_coche"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setFechaVenta(rs.getDate("fecha_venta"));
                venta.setPrecioFinal(rs.getDouble("precio_final"));
            }

        } catch (SQLException e) {
            venta = null;
        }
        return venta;
    }
}
