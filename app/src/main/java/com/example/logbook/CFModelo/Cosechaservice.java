package com.example.logbook.CFModelo;

import java.util.ArrayList;
import java.util.List;

public class Cosechaservice {

    public static List<Cosecha> cosechas = new ArrayList<>();

    //se valida la insersion, la eliminacion y la actualizacion
    public static void addcosecha (Cosecha cosecha){
        cosechas.add(cosecha);
    }

    public static void removecosecha (Cosecha cosecha){
        cosechas.remove(cosecha);
    }

    public static void updatecosecha (Cosecha cosecha){
        cosechas.set(cosechas.indexOf(cosecha),cosecha);
    }

}
