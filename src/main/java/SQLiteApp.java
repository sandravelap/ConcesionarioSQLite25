import dataBase.EstructuraDB;
import menus.MenuPrincipal;

public class SQLiteApp {
    static void main() {
        /*EstructuraDB estructuraDB = new EstructuraDB();
        estructuraDB.cargarScript();*/
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.muestraMenu();
    }
}
