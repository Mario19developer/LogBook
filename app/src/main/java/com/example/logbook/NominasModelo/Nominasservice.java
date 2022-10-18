package com.example.logbook.NominasModelo;

import java.util.ArrayList;
import java.util.List;

public class Nominasservice {

    public static List<Nominas> nominass = new ArrayList<>();

    //se valida la insersion, la eliminacion y la actualizacion
    public static void addnominas (Nominas nominas){
        nominass.add(nominas);
    }

    public static void removenominas (Nominas nominas){
        nominass.remove(nominas);
    }

    public static void updatenominas (Nominas nominas){
        nominass.set(nominass.indexOf(nominas),nominas);
    }
}
