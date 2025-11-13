package repositories;

import DAOs.Venta;
import dataBase.ConexionBD;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SelectVentas {
    public static Venta selectVentaById(Integer idVenta){
        Venta venta = new Venta();
        String selectCliente = "select * from ventas where id_venta = ?";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        //SQLite almacena la fecha como string con el formato "YYYY-MM-DD HH:MM:SS.SSS"
        //creamos el formato que necesitamos que tenga, prescindimos de la hora porque no la tenemos
        SimpleDateFormat formateo= new SimpleDateFormat("yyyy-MM-dd");
        try (Connection con =conexionBD.connect()){
            pst = con.prepareStatement(selectCliente);
            pst.setInt(1,idVenta);
            ResultSet rs = pst.executeQuery();
            while (rs.next ()){
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setIdCoche(rs.getInt("id_coche"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                String utilFechaVenta = rs.getString("fecha_venta");
                java.util.Date utilFecha = formateo.parse(utilFechaVenta);
                Date sqlFecha = new java.sql.Date(utilFecha.getTime());
                venta.setFechaVenta(sqlFecha);
                venta.setPrecioFinal(rs.getDouble("precio_final"));
            }

        } catch (SQLException e) {
            venta = null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return venta;
    }
}
