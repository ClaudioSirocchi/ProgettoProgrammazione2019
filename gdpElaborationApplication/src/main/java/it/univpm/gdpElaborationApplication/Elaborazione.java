package it.univpm.gdpElaborationApplication;

import it.univpm.gdpElaborationApplication.dataclass.GDP;

/**
 * Classe Elaborazione per il calcolo della media, minimo GDP, massimo GDP, Variazione percentuale
 * @author Pistagnesi Sirocchi
 * @version 1.0
 */ 

public class Elaborazione{
	private double Avg;
	private GDP Min;
	private GDP Max;
	private double Percentuale;
	private double Variazione;
	
	 /**
     * Calcola la media dei valori gdp
     * @param vettore gdp
    */
	public void Media(GDP[] gdp){
		int somma = 0;
		for (int i =0; i <18; i ++) {
			somma += gdp[i].getValue();
		}
		Avg = somma /18;
		System . out . println (" La media e ’: " +Avg );	
	}
	
	 /**
     * Calcola il minimo dei valori gdp
     * @param vettore gdp
    */
	public void Minimo(GDP[] gdp) {
		Min=gdp[0];
		for(int i=0; i<=gdp.length-1; i=i+1) {
			if(gdp[i].getValue() < Min.getValue()) {
				Min=gdp[i];
		    }
		  }
		System.out.println("Il minimo e' "+Min);
	}
	
	 /**
     * Calcola il massimo dei valori gdp
     * @param vettore gdp
    */
	public void Massimo(GDP[] gdp) {
		Max=gdp[0];
		for(int i=0; i<=gdp.length-1; i=i+1) {
			if( gdp[i].getValue()>Max.getValue()) {
				Max=gdp[i];
		      }
		  }
		System.out.println("Il massimo e' "+Max);
	}
	
	 /**
     * Calcola la variazione percentuale dei gdp da un anno all'altro
     * @param anno iniziale, anno finale
    */
	public void Variazione(GDP dateI, GDP dateF) {
		double somma = dateI.getValue()+dateF.getValue();
	    Variazione =((dateF.getValue()-dateI.getValue())/dateI.getValue());
	    Percentuale = (float) ((Variazione*100)/somma);
	    
	    System.out.println("La variazione percentuale e' "+Percentuale);
	    
	}
}
