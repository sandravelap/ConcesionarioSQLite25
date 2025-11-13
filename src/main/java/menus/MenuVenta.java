package menus;

import DTOs.VentaDTO;
import services.ClientesServices;
import services.CochesServices;
import services.VentasServices;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.IO.println;
import static libs.Leer.pedirCadena;
import static libs.Leer.pedirDouble;

public class MenuVenta {
    private boolean salir = false;
    private Scanner sc = new Scanner(System.in);
    private CochesServices cochesServices = new CochesServices();
    private ClientesServices clientesServices = new ClientesServices();
    private VentasServices ventasServices = new VentasServices();
    private VentaDTO ventaDTO = new VentaDTO();

    public void muestraMenu() {
        String opcion;
        do {
            println("Introduce los datos del cliente o 0 para salir:");
            println("1. Introduce el nombre del cliente.");
            println("2. Introduce el modelo del coche vendido.");
            println("3. Introduce el precio final del coche.");
            println("4. Verificar datos y guardar la venta en BD.");
            println("0. Salir");
            opcion = this.pideOpcion();
            this.procesaOpcion(opcion);
        } while (!salir);
    }

    private String pideOpcion() {
        return this.sc.nextLine();
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> {
                String nombreCliente = pedirCadena ("Introduce el nombre: ");
                Integer idCliente = clientesServices.clienteByName(nombreCliente);
                if ( idCliente== 0) {
                    println("El nombre del cliente no existe");
                }else{
                    println("Cliente ok con id: " + idCliente);
                    ventaDTO.setIdCliente(idCliente);
                }
            }
            case "2" -> {
                String modeloCoche = pedirCadena("Introduce el modelo del coche:");
                Integer idCoche = cochesServices.cocheByModel(modeloCoche);
                if (idCoche == 0) {
                    println("El modelo coche no existe");
                }else{
                    println("Coche ok con id: " + idCoche);
                    if(cochesServices.isDisponible(idCoche)) {
                        ventaDTO.setIdCoche(idCoche);
                    }else{
                        println("El coche no está disponible.");
                    }
                }
            }
            case "3" -> {
                Double precio = pedirDouble("Introduce el precio final del coche:");
                ventaDTO.setPrecio(precio);
            }
            case "4" ->{
                println("Se va a insertar una venta con los datos:");
                println("\t Identificador del coche: "+ventaDTO.getIdCoche());
                println("\t Identificador del cliente: "+ventaDTO.getIdCliente());
                println("\t Precio final del coche: "+ventaDTO.getPrecio());
                if (Objects.equals(pedirCadena("Escribe OK para confirmar la venta. "), "OK")){
                    println(ventasServices.insertarNuevaVenta(ventaDTO));
                }else{
                    println("Venta no insertada.");
                }
            }
            default -> println("Opción incorrecta");
        }
    }
}
