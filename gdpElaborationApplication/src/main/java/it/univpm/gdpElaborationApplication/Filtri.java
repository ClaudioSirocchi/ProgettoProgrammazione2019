package it.univpm.gdpElaborationApplication;

import java.io.IOException;

import it.univpm.gdpElaborationApplication.dataclass.Rilevazione;

public class Filtri {
    public static void getMeta() throws NoSuchMethodException, IOException {
    	Rilevazione r1=new Rilevazione();
    	System.out.print(r1.creaMetaDati());
    }
}

