import dataBase.EstructuraDB;
import menus.MenuPrincipal;

public class SQLiteApp {
    static void main() {
        EstructuraDB estructuraDB = new EstructuraDB();
        estructuraDB.cargarScript();
        //estructuraDB.createDB("pruebaBD");
        /*MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.muestraMenu();*/
    }
}
