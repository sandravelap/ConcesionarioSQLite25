package repositories;

import dataBase.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCoches {
    public static Integer cocheByModel(String modelo){
        Integer idCoche=0;
        String selectCliente = "select * from coches where modelo = ?";
        PreparedStatement pst = null;
        ConexionBD conexionBD = new ConexionBD();
        try (Connection con =conexionBD.connect()){
            pst = con.prepareStatement(selectCliente);
            pst.setString(1, modelo);
            ResultSet rs = pst.executeQuery();
            if (rs.next ()){
                idCoche = rs.getInt("id_coche");
            }

        } catch (SQLException e) {
            idCoche = 0;
        }
        return idCoche;
    }

    public static boolean cocheDisponibleById (Integer idCoche){
        boolean cocheDisponible=false;
        PreparedStatement pst = null;
        String cocheDisponibleString = "select disponible from coches where id_coche = ?";
        ConexionBD conexionBD = new ConexionBD();
        try(Connection con = conexionBD.connect()){
            pst = con.prepareStatement(cocheDisponibleString);
            pst.setInt(1, idCoche);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                if (rs.getInt("disponible")==1){
                    cocheDisponible=true;
                }
            }
        }catch(SQLException e){
            cocheDisponible=false;
        }
        return cocheDisponible;
    }
}
