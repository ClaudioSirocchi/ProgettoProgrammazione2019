package it.univpm.gdpElaborationApplication.dataclass;

import java.io.IOException; 
import it.univpm.gdpElaborationApplication.dataclass.MetaJson.metadati;

/**
 * Classe GDP composta dalla data e dal valore GDP
 * @author Pistagnesi Sirocchi
 * @version 1.0
 */ 

public class GDP {
	
	private int date;
	private double value;

	@metadati(alias="Date", sourcefield="Data Rilevazione", type="int")

	/**
	 * Fornisce la data del GDP
	 * @return anno
	 */

	public int getDate() {
		return date;
	}
	
	 /**
     * Imposta la data del GDP
     * @param date
     */
	public void setDate(int date) {
		this.date = date;
	}
	@metadati(alias="Value", sourcefield="Valore di rilevazione", type="double")

	/**
	 * Fornisce il valore del GDP
	 * @return valore
	 */

	public double getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}	
	
	 /**
	    * Crea un GDP
	    * @param date anno del GDP
	    * @param value valore del GDP
	    */

	public GDP(int date, double value) {
		this.date = date;
		this.value = value;
	}
	public GDP() {
		this.date = 0;
		this.value = 0;
	}

	 /**
	    * Fornisce i dati del GDP sotto forma di una stringa di testo
	    * @return stringa con i dati del GDP
	    */

	@Override
	public String toString() {
		return "date=" + date + ", value=" + value;
	}
	
	public String toStringJson() {
		return "date\":\""+ date +"\",\"value\":\"" + value+"\"";
	}
	
	public String creaMetaDati() throws NoSuchMethodException, IOException{
		String campi[]= {"Date","Value"};
		return MetaJson.jsonMetaGDP(this.getClass(), campi);
	}

}
