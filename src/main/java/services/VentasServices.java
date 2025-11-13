package services;

import DAOs.Venta;
import DTOs.VentaDTO;
import repositories.DeleteVentas;
import repositories.InsertarVentasDB;
import repositories.SelectVentas;
import repositories.UpdateCoches;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VentasServices {
    public static StringBuilder insertarVentaService(Path pathCsv){
        StringBuilder respuestaCliente = new StringBuilder();
        Venta ventaAux;
        InsertarVentasDB insertarVentasDB = new InsertarVentasDB();
        if (Files.isReadable(pathCsv)) {
            try {
                List<String> ventasCsv = Files.readAllLines(pathCsv);
                ventasCsv.removeFirst();
                for (String ventaCsv : ventasCsv) {
                    ventaAux = new Venta();
                    ventaAux.setIdVenta(Integer.valueOf(ventaCsv.split(",")[0]));
                    ventaAux.setIdCoche(Integer.valueOf(ventaCsv.split(",")[1]));
                    ventaAux.setIdCliente(Integer.valueOf(ventaCsv.split(",")[2]));
                    ventaAux.setFechaVenta(Date.valueOf(ventaCsv.split(",")[3]));
                    ventaAux.setPrecioFinal(Double.valueOf(ventaCsv.split(",")[4]));
                    if (insertarVentasDB.insertVenta(ventaAux)){
                        respuestaCliente.append("Venta ").append(ventaAux.getIdCoche()).append(" insertada correctamente\n");
                    }else{
                        respuestaCliente.append("Venta ").append(ventaAux.getIdCoche()).append(" NO insertada correctamente\n");
                    }
                }
            } catch (IOException e) {
                respuestaCliente.append("Error al leer el archivo");
            }
        }else{
            respuestaCliente.append("Error de lectura del archivo csv. ");
        }
        return respuestaCliente;
    }
    public static StringBuilder insertarNuevaVenta(VentaDTO nuevaVenta){
        StringBuilder respuestaCliente = new StringBuilder();
        InsertarVentasDB insertarVentasDB = new InsertarVentasDB();
        Venta ventaDAO = new Venta();
        ventaDAO.setIdCoche(nuevaVenta.getIdCoche());
        ventaDAO.setIdCliente(nuevaVenta.getIdCliente());
        ventaDAO.setPrecioFinal(nuevaVenta.getPrecio());
        ventaDAO.setFechaVenta(Date.valueOf(LocalDate.now()));
        try {
            insertarVentasDB.insertVentaNueva(ventaDAO);
            respuestaCliente.append("Venta insertada. \n");
        } catch (SQLException e) {
            respuestaCliente.append("Error al insertar nueva venta");
        }
        UpdateCoches updateCochesRepository = new UpdateCoches();
        try {
            updateCochesRepository.anularDispoCocheById(nuevaVenta.getIdCoche());
            respuestaCliente.append("Disponibilidad del coche actualizada.");
        } catch (SQLException e) {
           respuestaCliente.append("Error al cambiar la disponibilidad del coche vendido.");
        }
        return respuestaCliente;
    }

    public static StringBuilder deleteVenta(Integer idVenta){
        StringBuilder respuestaCliente = new StringBuilder();
        DeleteVentas deleteVentasRepo = new DeleteVentas();
        SelectVentas selectVentasRepo = new SelectVentas();
        UpdateCoches updateCochesRepo = new UpdateCoches();
        Venta ventaDAO = new Venta();
        ventaDAO = selectVentasRepo.selectVentaById(idVenta);
        if (ventaDAO != null){
            try {
                deleteVentasRepo.deleteVentaById(idVenta);
                respuestaCliente.append("Venta eliminada. \n");
                try {
                    updateCochesRepo.activarDispoCocheById(ventaDAO.getIdCoche());
                    respuestaCliente.append("Disponibilidad del coche actualizada.");
                } catch (SQLException e) {
                    respuestaCliente.append("Error al activar la disponibilidad del coche.");
                }
            } catch (SQLException e) {
                respuestaCliente.append("Error al eliminar la venta.");
            }

        }else{
            respuestaCliente.append("No existe ninguna venta con ese id.");
        }
        return respuestaCliente;
    }
}
