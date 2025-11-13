package services;

import repositories.SelectCoches;

import static repositories.SelectCoches.cocheDisponibleById;

public class CochesServices {

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
