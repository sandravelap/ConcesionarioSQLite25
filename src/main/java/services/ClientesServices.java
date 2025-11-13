package services;

import DAOs.Cliente;
import repositories.InsertarClientesDB;
import repositories.SelectClientes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ClientesServices {
    public static StringBuilder insertarClienteService(Path pathCsv){
        StringBuilder respuestaCliente = new StringBuilder();
        Cliente clienteAux;
        InsertarClientesDB insertarClientesDB = new InsertarClientesDB();

        if (Files.isReadable(pathCsv)) {
            try {
                List<String> clientesCsv = Files.readAllLines(pathCsv);
                clientesCsv.removeFirst();
                for (String clienteCsv : clientesCsv) {
                    clienteAux = new Cliente();
                    clienteAux.setIdCliente(Integer.parseInt(clienteCsv.split(",")[0]));
                    clienteAux.setNombre(clienteCsv.split(",")[1]);
                    clienteAux.setTelefono(clienteCsv.split(",")[2]);
                    clienteAux.setEmail(clienteCsv.split(",")[3]);
                    if (insertarClientesDB.insertCliente(clienteAux)){
                        respuestaCliente.append("Cliente ").append(clienteAux.getIdCliente()).append(" insertado correctamente \n");
                    }else{
                        respuestaCliente.append("Cliente ").append(clienteAux.getIdCliente()).append(" NO insertado correctamente \n");
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

    public static Integer clienteByName(String nombre){
        SelectClientes selectClientes = new SelectClientes();
        Integer respuestaCliente = selectClientes.clienteByName(nombre);

        return respuestaCliente;
    }
}
