package dataBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstructuraDB {
    public static void cargarScript(){
        ConexionBD connectionBD = new ConexionBD();
        try(Connection conBD = connectionBD.connect()) {
            Statement statement = conBD.createStatement();
            Path sqlScriptPath = Path.of("src/main/resources/ConcesionarioSQLite.sql");
            //almaceno cada statement a ejecutar
            StringBuilder query = new StringBuilder();
            String line;
            //cargo todas las lineas del fichero sql
            List<String> linesScript = Files.readAllLines(sqlScriptPath);
            for (String lineScript:linesScript){
                //elimino los comentarios
                if(!lineScript.startsWith("--")){
                    query.append(lineScript);
                }
                //cuando llega un fin de statement lo ejecuto y reinicio el string que almacena el stm
                if(lineScript.endsWith(";")){
                    statement.execute(query.toString().trim());
                    query = new StringBuilder();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
