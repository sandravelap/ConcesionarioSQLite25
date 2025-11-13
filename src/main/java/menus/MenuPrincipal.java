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
            System.out.println("1. Insertar nueva venta");
            System.out.println("2. Eliminar una venta");
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
            case "1"  -> {
                MenuVenta menuVenta = new MenuVenta();
                menuVenta.muestraMenu();
            }
            case "2" -> {
                VentasServices ventasServices = new VentasServices();
                Integer idVenta = pedirEntero("Introduce el id de la venta a eliminar");
                println(ventasServices.deleteVenta(idVenta));
            }
            default -> System.out.println("Opción incorrecta");
        }
    }
}