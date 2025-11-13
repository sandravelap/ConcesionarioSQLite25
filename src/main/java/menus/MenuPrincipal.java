package menus;

import services.ClientesServices;
import services.CochesServices;
import services.VentasServices;

import java.nio.file.Path;
import java.util.Scanner;

import static java.lang.IO.println;
import static libs.Leer.pedirEntero;
import static libs.UserData.pedirRuta;

public class MenuPrincipal {
    private boolean salir = false;
    private Scanner sc = new Scanner(System.in);

    public void muestraMenu() {
        String opcion;
        do {
            System.out.println("Elige una opcion:");
            System.out.println("1. Leer coches desde csv.");
            System.out.println("2. Leer clientes desde csv");
            System.out.println("3. Leer ventas desde csv");
            System.out.println("4. Insertar nueva venta");
            System.out.println("5. Eliminar una venta");
            System.out.println("0. Salir");
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
                CochesServices cochesServices = new CochesServices();
                Path p = pedirRuta("Introduce la ruta del archivo csv");
                println(cochesServices.insertarCocheService(p));
            }
            case "2" -> {
                ClientesServices clientesServices = new ClientesServices();
                Path p = pedirRuta("Introduce la ruta del archivo csv:");
                println(clientesServices.insertarClienteService(p));
            }
            case "3" -> {
                VentasServices ventasServices = new VentasServices();
                Path p = pedirRuta("Introduce la ruta del archivo csv:");
                println(ventasServices.insertarVentaService(p));
            }
            case "4" -> {
                MenuVenta menuVenta = new MenuVenta();
                menuVenta.muestraMenu();
            }
            case "5" -> {
                VentasServices ventasServices = new VentasServices();
                Integer idVenta = pedirEntero("Introduce el id de la venta a eliminar");
                println(ventasServices.deleteVenta(idVenta));
            }
            default -> System.out.println("Opción incorrecta");
        }
    }
}