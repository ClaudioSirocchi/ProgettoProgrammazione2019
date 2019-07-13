package it.univpm.gdpElaborationApplication;

import it.univpm.gdpElaborationApplication.dataclass.GDP;

public class Elaborazione{
	private double Avg;
	private GDP Min;
	private GDP Max;
	private double Percentuale;
	private double Variazione;
	
	public void Media(GDP[] gdp){
		int somma = 0;
		for (int i =0; i <18; i ++) {
			somma += gdp[i].getValue();
		}
		Avg = somma /18;
		System . out . println (" La media e ’: " +Avg );	
	}
	
	public void Minimo(GDP[] gdp) {
		Min=gdp[0];
		for(int i=0; i<=gdp.length-1; i=i+1) {
			if(gdp[i].getValue() < Min.getValue()) {
				Min=gdp[i];
		    }
		  }
		System.out.println("Il minimo e' "+Min);
	}
	
	public void Massimo(GDP[] gdp) {
		Max=gdp[0];
		for(int i=0; i<=gdp.length-1; i=i+1) {
			if( gdp[i].getValue()>Max.getValue()) {
				Max=gdp[i];
		      }
		  }
		System.out.println("Il massimo e' "+Max);
	}
	
	public void Variazione(GDP dateI, GDP dateF) {
		float somma = dateI.getValue()+dateF.getValue();
	    Variazione =((dateF.getValue()-dateI.getValue())/dateI.getValue());
	    Percentuale = (float) ((Variazione*100)/somma);
	    
	    System.out.println("La variazione percentuale e' "+Percentuale);
	    
	}
}
