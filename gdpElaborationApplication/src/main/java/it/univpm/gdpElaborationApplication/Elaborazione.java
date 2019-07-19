package it.univpm.gdpElaborationApplication;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.gdpElaborationApplication.dataclass.GDP;
import it.univpm.gdpElaborationApplication.dataclass.MetaJson.metadati;

/**
 * Classe Elaborazione
 * @author Pistagnesi Sirocchi
 * @version 1.0 
 */
public class Elaborazione{
	private double avg;
	private GDP min;
	private GDP max;
	private double variazione;
	
	public String campi= "\"Avg\",\"Min\",\"Max\",\"Variazione\"";
    
	/**
	 * Fornisce la media dei valori rilevati
	 * @return avg Media
	 */
	@metadati(alias="avg", sourcefield="media dei valori rilevati", type="double")
	public double getAvg() {
		return avg;
	}
	
	/**
	 * Imposta la media dei valori rilevati
	 * @param avg Media
	 */
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	/**
	 * Fornisce il minimo dei valori rilevati
	 * @return min Minimo
	 */
	@metadati(alias="min", sourcefield="minimo rilevarione riga", type="GDP")
	public GDP getMin() {
		return min;
	}

	/**
	 * Imposta il minimo dei valori rilevati
	 * @param min Minimo
	 */
	public void setMin(GDP min) {
		this.min = min;
	}
	
	/**
	 * Fornisce il massimo dei valori rilevati
	 * @return max Massimo
	 */
	@metadati(alias="max", sourcefield="massimo rilevarione riga", type="GDP")
	public GDP getMax() {
		return max;
	}

	/**
	 * Imposta il massimo dei valori rilevati
	 * @param max Massimo
	 */
	public void setMax(GDP max) {
		this.max = max;
	}
	
	/**
	 * Fornisce la variazione percentuale dei valori rilevati
	 * @return variazione variazione che viene restituita al metodo chiamante
	 */
	@metadati(alias="variazione", sourcefield="variazione percentuale fra prima e ultima rilevazione", type="GDP")
	public double getVariazione() {
		return variazione;
	}

	/**
	 * Imposta la variazione percentuale dei valori rilevati
	 * @param variazione variazione che deve essere impostata nell'ogetto elaborazione
	 */
	public void setVariazione(double variazione) {
		this.variazione = variazione;
	}
	
	/**
	 * Crea un'elaborazione fittizia uguale a zero.
	 */
	public Elaborazione() {
		this.avg = 0;
		this.min = null;
		this.max = null;
		this.variazione = 0;
	}
	
	/**
	 * Calcola la media dei valori rilevati
	 * @param gdpVect Vettore dei GDP
	 * @return Media
	 */
	public static double Media(Vector<GDP> gdpVect){
		double somma=0;
		for (int i =0; i <gdpVect.size(); i++) {
			somma += gdpVect.get(i).getValue();
		}
		return (somma/gdpVect.size());
	}

	/**
	 * Calcola il minimo dei valori rilevati
	 * @param gdpVect vettore gdp del quale si deve calcolare il minimo
	 * @return min elemento con il valore minore fra tutti gli elementi gdp del vettore
	 */
	public GDP Minimo(Vector<GDP> gdpVect) {
		GDP min=gdpVect.get(0);
		for(int i=0; i<gdpVect.size()-1; i++) {
			if(gdpVect.get(i).getValue() < min.getValue()) {
				min=gdpVect.get(i);
		    }
		  }
		return min;
	}
	
	/**
	 * Calcola il massimo dei valori rilevati
	 * @param gdpVect vettore gdp del quale si deve calcolare il massimo
	 * @return max elemento con il valore massimo fra tutti gli elementi gdp del vettore
	 */
	public GDP Massimo(Vector<GDP> gdpVect) {
		GDP max=gdpVect.get(0);
		for(int i=0; i<gdpVect.size()-1; i++) {
			if(gdpVect.get(i).getValue() > max.getValue()) {
				max=gdpVect.get(i);
		    }
		  }
		return max;
	}
	
	/**
	 * Calcola la variazione percentuale dei gdp da una data iniziale a una finale
	 * @param dateI Data iniziale
	 * @param dateF Data finale
	 * @return percentuale
	 */
	public double Variazione(GDP dateI, GDP dateF) {
		double somma = dateI.getValue()+dateF.getValue();
		double percentuale;  													//controllare la divisione per zero
	    variazione = dateF.getValue()-dateI.getValue();
	    percentuale = (variazione*100)/somma;
	    return percentuale;
	}
	
	/**
	 * Assegna per ogni riga valore massimo, minimo, media, variazione(calcolata fra il primo e l'ultimo anno)
	 * @param valoririga valori gdp sui quali viene effettuata l'elaborazione
	 * @return datiElab ogetto gdp che contiene le elaborazioni dei valori della riga 
	 */
	public Elaborazione elabora(Vector<GDP> valoririga) {
		Elaborazione datiElab= new Elaborazione();
			datiElab.avg=Media(valoririga);
			datiElab.min=Minimo(valoririga);
			datiElab.max=Massimo(valoririga);
			datiElab.variazione=Variazione(valoririga.get(0),valoririga.get(valoririga.size()-1));
    	return datiElab;
    }
	
	/**
	 * Restituisce un json object che contiene tutti i valori della prima riga
	 * @param jsonData vettore che contiene i dati da trasformare in oggetto json
	 * @param startyear elemento che contiene il valore Anno del primo elemento gdp
	 * @return obj elemento di tipo JSONObject restituito al filtro da aggiungere al json
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject jsonSaveObj(String[] jsonData,int startyear) {
		JSONObject obj = new JSONObject();
		int year=startyear;
		for(int j=0; j<jsonData.length; j++)
	    {
	       obj.put("Frequenza", jsonData[0]);
	       obj.put("Geo", jsonData[1]);
	       obj.put("Unità", jsonData[2]);
	       obj.put("Oggetto", jsonData[3]);
	       JSONObject objGdp = new JSONObject();
	       for(int k=4;k<jsonData.length;k++) {
	    	   objGdp.put(year++, jsonData[k]);
	       }
	       year=startyear;
	       obj.put("Gdp", objGdp);
	    }  
	    return obj;
	}
	
	/**
	 * Restituisce un json object che contiene i valori della prima riga fino all'oggetto più un campo usato come filtro
	 * @param jsonData vettore che contiene i dati da trasformare in oggetto json
	 * @param field campo da inserire all'interno dell'oggetto json
	 * @param fieldValue valore del campo da inserire
	 * @return obj elemento di tipo JSONObject restituito al filtro da aggiungere al json
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject jsonSaveObj(String[] jsonData,String field,String fieldValue) {
		JSONObject obj = new JSONObject();
		for(int j=0; j<jsonData.length; j++)
	    {
	       obj.put("Frequenza", jsonData[0]);
	       obj.put("Geo", jsonData[1]);
	       obj.put("Unità", jsonData[2]);
	       obj.put("Oggetto", jsonData[3]);
	       obj.put(field, fieldValue);
	    }  
	    return obj;
	}
	
	/**
	 * Restituisce un json object che contiene i valori della prima riga fino all'oggetto più un campo di tipo gdp usato come filtro
	 * @param jsonData jsonData vettore che contiene i dati da trasformare in oggetto json
	 * @param gdpValue valori gdp su cui selezionare il campo da filtrare da inserire nell'oggetto json
	 * @param field campo che deve essere inserito nell'oggetto
	 * @return obj elemento di tipo JSONObject restituito al filtro da aggiungere al json
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject jsonSaveObj(String[] jsonData,GDP gdpValue, String field) {
		JSONObject obj = new JSONObject();
		JSONObject gdpObj = new JSONObject();
		gdpObj.put("Anno", Integer.toString(gdpValue.getDate()));
		gdpObj.put("Valore", Double.toString(gdpValue.getValue()));
		for(int j=0; j<jsonData.length; j++)
		{
		   obj.put("Frequenza", jsonData[0]);
		   obj.put("Geo", jsonData[1]);
		   obj.put("Unità", jsonData[2]);
		   obj.put("Oggetto", jsonData[3]);
		   obj.put(field,gdpObj);
		} 
	    return obj;
	}
	
	
	
}
