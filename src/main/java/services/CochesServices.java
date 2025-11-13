package services;

import DAOs.Coche;
import repositories.InsertarCochesDB;
import repositories.SelectCoches;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static repositories.SelectCoches.cocheDisponibleById;

public class CochesServices {
    public static StringBuilder insertarCocheService(Path pathCsv){
        StringBuilder respuestaCliente = new StringBuilder();
        Coche cocheAux;
        InsertarCochesDB insertarCochesDB = new InsertarCochesDB();
        if (Files.isReadable(pathCsv)) {
            try {
                List<String> cochesCsv = Files.readAllLines(pathCsv);
                cochesCsv.removeFirst();
                for (String cocheCsv : cochesCsv) {
                    cocheAux = new Coche();
                    cocheAux.setIdCoche(Integer.valueOf(cocheCsv.split(",")[0]));
                    cocheAux.setMarca(cocheCsv.split(",")[1]);
                    cocheAux.setModelo(cocheCsv.split(",")[2]);
                    cocheAux.setAnio(Short.valueOf(cocheCsv.split(",")[3]));
                    cocheAux.setBastidor(cocheCsv.split(",")[4]);
                    cocheAux.setPrecio(Double.valueOf(cocheCsv.split(",")[5]));
                    cocheAux.setDisponible(Boolean.parseBoolean(cocheCsv.split(",")[6]));
                    if (insertarCochesDB.insertCoche(cocheAux)){
                        respuestaCliente.append("Coche ").append(cocheAux.getIdCoche()).append(" insertado correctamente");
                    }else{
                        respuestaCliente.append("Coche ").append(cocheAux.getIdCoche()).append(" NO insertado correctamente");
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
    public static Integer cocheByModel(String modeloCoche){
        Integer idCoche=0;
        SelectCoches selectCoches = new SelectCoches();
        idCoche = selectCoches.cocheByModel(modeloCoche);
        return idCoche;
    }

    public boolean isDisponible(Integer idCoche) {
        boolean isDisponible = false;
            if(cocheDisponibleById(idCoche)){isDisponible=true;}
        return isDisponible;
    }
}
