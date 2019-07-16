package it.univpm.gdpElaborationApplication.dataclass;

import java.io.IOException;
import java.util.Vector;
import it.univpm.gdpElaborationApplication.dataclass.MetaJson.metadati;

/**
 * Classe Rilevazione composta da:
 * -frequenza
 * -geo
 * -unit
 * -obj
 * -vettore gdp
 * @author Pistagnesi Sirocchi
 * @version 1.0
 */ 

public class Rilevazione {
	
	private char frequenza;
	private String geo;
	private String unit;
	private String obj;
	private Vector<GDP> gdpdata;
	
<<<<<<< HEAD
	@metadati(alias="Frequenza", sourcefield="Frequenza di Rilevazione", type="Char")
=======
	/**
	 * Fornisce la frequenza del GDP
	 * @return frequenza
	 */
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	public char getFrequenza() {
		return frequenza;
	}
	
	/**
     * Imposta la frequenza del GDP
     * @param frequenza
     */
	public void setFrequenza(char frequenza) {
		this.frequenza = frequenza;
	}
	
<<<<<<< HEAD
	@metadati(alias="Geo", sourcefield="Località geografica Rilevazione", type="String")
=======
	/**
	 * Fornisce la città del GDP
	 * @return geo
	 */
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	public String getGeo() {
		return geo;
	}
	
	/**
     * Imposta la città del GDP
     * @param geo
     */
	public void setGeo(String geo) {
		this.geo = geo;
	}
	
<<<<<<< HEAD
	@metadati(alias="Unit", sourcefield="Unità di rilevamento", type="String")
=======
	/**
	 * Fornisce il tipo di dato
	 * @return unit
	 */
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	public String getUnit() {
		return unit;
	}
	
	/**
     * Imposta il tipo di dato
     * @param unit
     */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
<<<<<<< HEAD
	@metadati(alias="Obj", sourcefield="Nome oggetto di rilevazione", type="String")
=======
	/**
	 * Fornisce l'obiettivo
	 * @return obj
	 */
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	public String getObj() {
		return obj;
	}
	
	/**
     * Imposta l'obiettivo
     * @param obj
     */
	public void setObj(String obj) {
		this.obj = obj;
	}
	
<<<<<<< HEAD
	@metadati(alias="Gdpdata", sourcefield="Vettore di rilevazioni", type="Char")
=======
	/**
	 * Fornisce il vettore GDP
	 * @return dati del gdp
	 */
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	public Vector<GDP> getGdpdata() {
		return gdpdata;
	}
	
<<<<<<< HEAD
=======
	/**
     * Imposta il vettore GDP
     * @param dati del gdp
     */
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	public void setGdpdata(Vector<GDP> gdpdata) {
		this.gdpdata = gdpdata;
	}
	
	  /**
	    * Crea una rilevazione
	    * @param frequenza
	    * @param geo città della rilevazione
	    * @param unit tipo di dato
	    * @param obj obiettivo della rilevazione
	    * @param gdpdata vettore gdp
	    */
	public Rilevazione(char frequenza, String geo, String unit, String obj, Vector<GDP> gdpdata) {
		this.frequenza = frequenza;
		this.geo = geo;
		this.unit = unit;
		this.obj = obj;
		this.gdpdata=gdpdata;
	}
	
<<<<<<< HEAD
	public Rilevazione() {
		this.frequenza='\0';
		this.geo ="";
		this.unit = "";
		this.obj = "";
		this.gdpdata=null;
	}
=======
	/**
	    * Fornisce i dati della rilevazione sotto forma di una stringa di testo
	    * @return stringa con i dati della rilevazione
	    */
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	@Override
	public String toString() {
		return "frequenza=" + frequenza + ", geo=" + geo + ", unit=" + unit + ", obj=" + obj + ", gdpdata="
				+ gdpdata + "\n";
	}
	
	public String[] setRigaAsString(Rilevazione riga,int sizeString) {
		String [] lineString= new String[sizeString];
		lineString[0]= Character.toString(riga.getFrequenza());
		lineString[1]= riga.getGeo();
		lineString[2]=riga.getUnit();
		lineString[3]=riga.getObj();
		Vector<GDP> gdpVect = riga.getGdpdata();
		int gdpStart=4;
		for(int k=0;k<riga.gdpdata.size();k++) {
			lineString[gdpStart++]=Double.toString(gdpVect.get(k).getValue());
		}
		return lineString;
	}
	
	public String creaMetaDati() throws NoSuchMethodException, IOException{
		String campi[]= {"Frequenza","Geo","Unit","Obj","Gdpdata"};
		MetaJson.jsonMetaRilevazione(this.getClass(), campi);
		return ("Ho salvato il file Json");
	}
	
}
