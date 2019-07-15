package it.univpm.gdpElaborationApplication.dataclass;

/**
 * Classe GDP composta dalla data e dal valore GDP
 * @author Pistagnesi Sirocchi
 * @version 1.0
 */ 

public class GDP {

	private int date;
	private double value;
	
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
	
	/**
	 * Fornisce il valore del GDP
	 * @return valore
	 */
	public double getValue() {
		return value;
	}
	
	/**
     * Imposta il valore del GDP
     * @param valore
     */
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
	
	 /**
	    * Fornisce i dati del GDP sotto forma di una stringa di testo
	    * @return stringa con i dati del GDP
	    */
	@Override
	public String toString() {
		return "date=" + date + ", value=" + value;
	}
}
