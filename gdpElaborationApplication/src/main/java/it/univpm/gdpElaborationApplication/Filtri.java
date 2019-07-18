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
	
	
	@SuppressWarnings("unchecked")
	public static JSONArray media() { // restituisce una tabella con la media dei valori per ogni riga
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
	
	@SuppressWarnings("unchecked")
	public static JSONArray max() { // restituisce una tabella con la media dei valori per ogni riga
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
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
		String[] jsonToPrint=new String [4];
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
	
	@SuppressWarnings("unchecked")
	public static JSONArray variazione(int data1, int data2) { // restituisce un json con la variazione percentuale dei valori fra due date
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
	
	@SuppressWarnings("unchecked")
	public static JSONArray filterMax(String operator, double value) {
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			switch (operator) {
			case "<":
				if(riga.getDatiElab().getMax().getValue()<value)
				ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMin(),"Massimo"));	
				break;
			case ">":
				if(riga.getDatiElab().getMax().getValue()>value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMin(),"Massimo"));	
				break;
			}
		}
		return ja;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray filterMin(String operator, double value) {
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			switch (operator) {
			case "<":
				if(riga.getDatiElab().getMin().getValue()<value)
				ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMin(),"Minimo"));	
				break;
			case ">":
				if(riga.getDatiElab().getMin().getValue()>value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,riga.getDatiElab().getMin(),"Minimo"));	
				break;
			}
		}
		return ja;
	}
	
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
	
	@SuppressWarnings("unchecked")
	public static JSONArray filterVar(String operator, double value) {
		JSONArray ja = new JSONArray();
		String[] jsonToPrint=new String [4];
		String mediaVar="";
		for(int i=0;i<tabellaIn.size();i++) {
			Rilevazione riga=tabellaIn.get(i);
			jsonToPrint=riga.setRigaAsString(riga, 4);
			switch (operator) {
			case "<":
				mediaVar=Double.toString(riga.getDatiElab().getVariazione());
				if(riga.getDatiElab().getVariazione()<value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",mediaVar));	
				break;
			case ">":
				mediaVar=Double.toString(riga.getDatiElab().getVariazione());
				if(riga.getDatiElab().getVariazione()>value)
					ja.add(Elaborazione.jsonSaveObj(jsonToPrint,"Variazione",mediaVar));	
				break;
			}
		}
		return ja;
	}

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
	
	@SuppressWarnings("unchecked")
	public static JSONArray variazione(int data1, int data2,String operator, double value) { // restituisce un json con la variazione percentuale dei valori fra due date
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

