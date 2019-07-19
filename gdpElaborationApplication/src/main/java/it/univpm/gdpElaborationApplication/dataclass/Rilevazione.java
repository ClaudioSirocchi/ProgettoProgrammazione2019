 package it.univpm.gdpElaborationApplication.dataclass;

import java.io.IOException;
import java.util.Vector;

import it.univpm.gdpElaborationApplication.Elaborazione;
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
	private Elaborazione datiElab;
	
	
	@metadati(alias="DatiElab", sourcefield="Risultati ottenuti dall'elaborazione dei dati", type="Elaborazione")
	public Elaborazione getDatiElab() {
		return datiElab;
	}

	public void setDatiElab(Elaborazione datiElab) {
		this.datiElab = datiElab;
	}

	@metadati(alias="Frequenza", sourcefield="Frequenza di Rilevazione", type="Char")

	/**
	 * Fornisce la frequenza del GDP
	 * @return frequenza frequenza dell'oggetto Rilevazione
	 */
	public char getFrequenza() {
		return frequenza;
	}
	
	/**
     * Imposta la frequenza del GDP
     * @param frequenza frequenza da impostare
     */
	public void setFrequenza(char frequenza) {
		this.frequenza=frequenza;
	}
	

	@metadati(alias="Geo", sourcefield="Località geografica Rilevazione", type="String")

	/**
	 * Fornisce la città del GDP
	 * @return geo geo dell'oggetto Rilevazione
	 */

	public String getGeo() {
		return geo;
	}
	
	/**
     * Imposta la città del GDP
     * @param geo geo da impostare
     */
	public void setGeo(String geo) {
		this.geo = geo;
	}
	
	@metadati(alias="Unit", sourcefield="Unità di rilevamento", type="String")

	/**
	 * Fornisce il tipo di dato
	 * @return unit unità dell'oggetto Rilevazione
	 */


	public String getUnit() {
		return unit;
	}
	
	/**
     * Imposta il tipo di dato
     * @param unit unità da impostare
     */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@metadati(alias="Obj", sourcefield="Nome oggetto di rilevazione", type="String")

	/**
	 * Fornisce l'obiettivo
	 * @return obj oggetto dell'oggetto Rilevazione
	 */

	public String getObj() {
		return obj;
	}
	
	/**
     * Imposta l'obiettivo
     * @param obj obbiettivo da impostare
     */
	public void setObj(String obj) {
		this.obj = obj;
	}
	
	@metadati(alias="Gdpdata", sourcefield="Vettore di rilevazioni", type="Char")

	/**
	 * Fornisce il vettore GDP
	 * @return gdpdata dati del gdp dell'oggetto Rilevazione
	 */

	public Vector<GDP> getGdpdata() {
		return gdpdata;
	}
	

	/**
     * Imposta il vettore GDP
     * @param  gdpdata dati del gdp da impostare
     */
	public void setGdpdata(Vector<GDP> gdpdata) {
		this.gdpdata = gdpdata;
	}
	
	  /**
	    * Costruttore oggetto Rilevazione con parametri elaborati
	    * @param frequenza frequenza di rilevazione
	    * @param geo città della rilevazione
	    * @param unit tipo di dato
	    * @param obj obiettivo della rilevazione
	    * @param gdpdata vettore gdp
	    * @param datiElab dati ottenuti dall'elaborazione
	    */
	public Rilevazione(char frequenza, String geo, String unit, String obj, Vector<GDP> gdpdata, Elaborazione datiElab) {
		this.frequenza = frequenza;
		this.geo = geo;
		this.unit = unit;
		this.obj = obj;
		this.gdpdata=gdpdata;
		this.datiElab=datiElab;
	}
	
	  /**
	    * Costruttore oggetto Rilevazione
	    * @param frequenza frequenza di rilevazione
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
	/**
	 * Costruttore di default
	 */
	public Rilevazione() {
		this.frequenza='\0';
		this.geo ="";
		this.unit = "";
		this.obj = "";
		this.gdpdata=null;
	}

	/**
	    * Fornisce i dati della rilevazione sotto forma di una stringa di testo
	    * @return stringa con i dati della rilevazione
	    */

	@Override
	public String toString() {
		return "frequenza=" + frequenza + ", geo=" + geo + ", unit=" + unit + ", obj=" + obj + ", gdpdata="
				+ gdpdata + "\n";
	}
	
	
	/**
	 * Fornisce sotto forma di vettore di stringhe i valori dei campi di una determinata riga
	 * @param riga riga da cui ottenere i valori
	 * @param sizeString dimensione della riga da cui ottenere i valori
	 * @return stringa con i valori estrapolati da riga
	 */
	public String[] setRigaAsString(Rilevazione riga,int sizeString) { 
		String [] lineString= new String[sizeString];
		lineString[0]= Character.toString(riga.getFrequenza());
		lineString[1]= riga.getGeo();
		lineString[2]=riga.getUnit();
		lineString[3]=riga.getObj();
		Vector<GDP> gdpVect = riga.getGdpdata();
		int gdpStart=0;
		for(int k=4;k<sizeString;k++) {//++controllare l'ho modificata in modo tale che posso riutilizzare il metodo scrivere le stringhe anche per i filtri
			lineString[k]=Double.toString(gdpVect.get(gdpStart++).getValue());
		}
		return lineString;
	}
	/**
	 * Si occupa di fornire i metadati del data set(la funzione jsonMetaRilevazione dell'interfaccia
	 * metajson si occuperà di salvare i dati in un file
	 * @throws NoSuchMethodException segnala l'inesistenza di un metodo 
	 * al quale la funzione jsonMetaRilevazione 
	 * sta cercando di accedere
	 * @throws IOException gestisce gli errori di scrittura da parte della 
	 * funzione jsonMetaRilevazione  file metadati.json
	 */
	public void creaMetaDati() throws NoSuchMethodException, IOException{
		String campi[]= {"Frequenza","Geo","Unit","Obj","DatiElab","Gdpdata"};
		MetaJson.jsonMetaRilevazione(this.getClass(), campi);
		return;
	}
	
	
}
