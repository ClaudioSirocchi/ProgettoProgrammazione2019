package it.univpm.gdpElaborationApplication;

import java.io.IOException; 
import java.util.Vector;

import org.json.simple.JSONArray;


import it.univpm.gdpElaborationApplication.dataclass.GDP;
import it.univpm.gdpElaborationApplication.dataclass.Rilevazione;

/**
 * Classe Filtri
 * @author Pistagnesi Sirocchi
 */
public class Filtri {
	private static Vector<Rilevazione> tabellaIn;

	public static void getMetaDati() throws NoSuchMethodException, IOException {
    	Rilevazione r1=new Rilevazione();
    	r1.creaMetaDati();
    }
	
	public Filtri(Vector<Rilevazione> tabella) throws NoSuchMethodException, IOException {
		tabellaIn=tabella;
		Elaborazione el1=new Elaborazione();
		for(int i=0;i<tabella.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			riga.setDatiElab(el1.elabora(riga.getGdpdata()));//passo alla classe elabora tutti i valori gdp su cui eseguire i calcoli
		}

	}
	
	/**
	 * Restituisce una tabella con i dati GDP
	 * @return Json con tutti i dati
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray dati() { 
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		int startyear=tabellaIn.get(0).getGdpdata().get(0).getDate();
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4+riga.getGdpdata().size());
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,startyear));
		}
		return ja;
	}
	
	/**
	 * Restituisce una tabella con la media dei valori per ogni riga
	 * @return Json con campo media dei valori della riga
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray media() { 
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		String mediaApp;
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			mediaApp=Double.toString(riga.getDatiElab().getAvg());
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Media",mediaApp));
		}
		return ja;
	}
	
	/**
	 * Restituisce una tabella con il massimo dei valori per ogni riga
	 * @return Json con campo dei massimi
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray max() { 
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMax(),"Massimo"));
		}
		return ja;
	}
	
	/**
	 * Restituisce una tabella con il minimo dei valori per ogni riga
	 * @return Json con campo dei minimi
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray min() { 
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMin(),"Minimo"));
		}
		return ja;
	}
	
	/**
	 * Restituisce una tabella con la variazione dei valori per ogni riga fra data iniziale e data finale
	 * @return Json con variazione fra data iniziale e data finale
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray variazione() { 
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		String mediaVar;
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			mediaVar=Double.toString(riga.getDatiElab().getVariazione());
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",mediaVar));
		}
		return ja;
	}
	
	/**
	 * Restituisce un json con la variazione percentuale dei valori fra due date selezionate 
	 * @param data1 prima data
	 * @param data2 seconda data
	 * @return Json con variazione fra prima e seconda data 
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray variazione(int data1, int data2) { 
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		Elaborazione el=new Elaborazione();
		GDP elem1= new GDP();
		GDP elem2= new GDP();
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
		for(int k=0;k<riga.getGdpdata().size();k++) {// serve a selezionare i GDP con le due date selezionate
			if(riga.getGdpdata().get(k).getDate()==data1)
				elem1=riga.getGdpdata().get(k);
			if(riga.getGdpdata().get(k).getDate()==data2)
				elem2=riga.getGdpdata().get(k);
		}
			double elaborazione= el.Variazione(elem1, elem2);
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",Double.toString(elaborazione)));
		}
		return ja;
	}
	
	
	/** 
	 * Restituisce il valore medio gdp dei campi contenuti in una tabella maggiori di un determinato valore passato come
	 * parametro oppure restituisce il valore medio gdp dei campi contenuti in una tabella minori di un determinato valore passato come
	 * parametro
	 * @param operator operatore di controllo
	 * @param value  valore di controllo
	 * @return Json con campo media della riga che rispetta le condizioni
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray filterAvg(String operator, double value) {
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		String mediaApp="";
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			switch (operator) {
			case "<":
				mediaApp=Double.toString(riga.getDatiElab().getAvg());
				if(riga.getDatiElab().getAvg()<value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Media",mediaApp));	
				break;
			case ">":
				mediaApp=Double.toString(riga.getDatiElab().getAvg());
				if(riga.getDatiElab().getAvg()>value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Media",mediaApp));	
				break;
			}
		}
		return ja;
	}
	
	/**
	 * Restituisce la variazione gdp tra data iniziale e data finale dei campi 
	 * contenuti in una tabella maggiori di un determinato valore passato 
	 * come parametro oppure restituisce la variazione gdp tra primo e ultimo anno
	 * dei campi contenuti in una tabella minori 
	 * di un determinato valore passato come parametro
	 * @param operator operatore di controllo
	 * @param value  valore di controllo
	 * @return ja Json con campo variazione, fra due date, della riga che 
	 * rispetta le condizioni
	 * @return Json con variazione fra data iniziale e data finale che rispetta le condizioni 
	 */
	
	@SuppressWarnings("unchecked")
	public static JSONArray filterVar(String operator, double value) {
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		String var="";
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			switch (operator) {
			case "<":
				var=Double.toString(riga.getDatiElab().getVariazione());
				if(riga.getDatiElab().getVariazione()<value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",var));	
				break;
			case ">":
				var=Double.toString(riga.getDatiElab().getVariazione());
				if(riga.getDatiElab().getVariazione()>value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",var));	
				break;
			}
		}
		return ja;
	}

	/**
	 * Restituisce un json selezionando solo i campi che contengono
	 * un determinato valore per l'attributo geo
	 * @param valore valore di selezione geo
	 * @return Json che presenta solo elementi che contengono come geo il parametro valore
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray searchGeo(String valore) {
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		int startyear=tabellaIn.get(0).getGdpdata().get(0).getDate();
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4+riga.getGdpdata().size());
			if(jsonToPrint[1].equals(valore))
				ja.add(Elaborazione.jsonSaveObj(jsonToPrint,startyear));
		}
		return ja;
	}

	/**
	 * Restituisce un json selezionando solo i campi che contengono
	 * un determinato valore per l'attributo Oggetto
	 * @param valore valore di selezione geo
	 * @return Json che presenta solo elementi che contengono come geo il parametro valore
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray searchOggetto(String valore) {
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		int startyear=tabellaIn.get(0).getGdpdata().get(0).getDate();
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4+riga.getGdpdata().size());
			if(jsonToPrint[3].equals(valore))
				ja.add(Elaborazione.jsonSaveObj(jsonToPrint,startyear));
		}
		return ja;
	}
	/**
	 * Restituisce la variazione gdp tra due anni selezionati sui campi 
	 * contenuti in una tabella maggiori di un determinato valore passato 
	 * come parametro oppure restituisce la variazione gdp tra due anni 
	 * selezionati sui campi contenuti in una tabella minori 
	 * di un determinato valore passato come parametro
	 * @param operator operatore di controllo
	 * @param value  valore di controllo
	 * @return ja Json con campo variazione, fra due date, della riga che 
	 * rispetta le condizioni
	 * @return Json con variazione fra prima e seconda data che rispetta le condizioni 
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray variazione(int data1, int data2,String operator, double value) { 
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		Elaborazione el=new Elaborazione();
		GDP elem1= new GDP();
		GDP elem2= new GDP();
		String mediaVar="";
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
		for(int k=0;k<riga.getGdpdata().size();k++) {// serve a selezionare i GDP con le due date selezionate
			if(riga.getGdpdata().get(k).getDate()==data1)
				elem1=riga.getGdpdata().get(k);
			if(riga.getGdpdata().get(k).getDate()==data2)
				elem2=riga.getGdpdata().get(k);
		}
			double elaborazione= el.Variazione(elem1, elem2);
			switch (operator) {
			case "<":
				mediaVar=Double.toString(elaborazione);
				if(elaborazione<value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",mediaVar));	
				break;
			case ">":
				mediaVar=Double.toString(elaborazione);
				if(elaborazione>value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",mediaVar));	
				break;
			}		}
		return ja;
	}
	
}

