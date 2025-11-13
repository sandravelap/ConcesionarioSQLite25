package repositories;

import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteVentas {
    public static boolean deleteVentaById(Integer idVenta) throws SQLException {
        SelectVentas selectVenta = new SelectVentas();
        boolean deleteOk = false;
        //recupero los datos de la venta para poder actualizar la disponibilidad del coche
        String insertVentaString = "DELETE from ventas WHERE id_venta=?";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        Connection con =conexionBD.connect();
        pst = con.prepareStatement(insertVentaString);
        pst.setInt(1,idVenta);
        pst.executeUpdate();
        deleteOk = true;
        return deleteOk;
    }
}
