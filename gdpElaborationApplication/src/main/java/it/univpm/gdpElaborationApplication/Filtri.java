package it.univpm.gdpElaborationApplication;

import java.io.IOException; 
import java.util.Vector;

import org.json.simple.JSONArray;

import it.univpm.gdpElaborationApplication.dataclass.GDP;
import it.univpm.gdpElaborationApplication.dataclass.Rilevazione;

public class Filtri {
	private static Vector<Rilevazione> tabellaIn;
	
	public static void getMetaDati() throws NoSuchMethodException, IOException {
    	Rilevazione r1=new Rilevazione();
    	System.out.print(r1.creaMetaDati());
    }
	
	public Filtri(Vector<Rilevazione> tabella) throws NoSuchMethodException, IOException {
		tabellaIn=tabella;
		Elaborazione el1=new Elaborazione();
		for(int i=0;i<tabella.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			riga.setDatiElab(el1.elabora(riga.getGdpdata()));//passo alla classe elabora tutti i valori gdp su cui eseguire i calcoli
		}

	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray media() { // restituisce una tabella con la media dei valori per ogni riga
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [5];
		String mediaApp;
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			mediaApp=Double.toString(riga.getDatiElab().getAvg());
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Media",mediaApp));
		}
		return ja;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray max() { // restituisce una tabella con la media dei valori per ogni riga
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [5];
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMax(),"Massimo"));
		}
		return ja;
	}
	@SuppressWarnings("unchecked")
	public static JSONArray min() { // restituisce una tabella con la media dei valori per ogni riga
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [5];
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMin(),"Minimo"));
		}
		return ja;
	}
	@SuppressWarnings("unchecked")
	public static JSONArray variazione() { // restituisce una tabella con la media dei valori per ogni riga
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [5];
		String mediaVar;
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			mediaVar=Double.toString(riga.getDatiElab().getVariazione());
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",mediaVar));
		}
		return ja;
	}
	
	public static JSONArray variazione(GDP data1, GDP data2) { // restituisce un json con la variazione percentuale dei valori fra due date
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [5];
		Elaborazione el = null;
		double elaborazione= el.Variazione(data1, data2);
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",Double.toString(elaborazione)));
		}
		return ja;
	}
	
	
}

