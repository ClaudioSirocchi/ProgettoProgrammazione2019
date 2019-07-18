package it.univpm.gdpElaborationApplication;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.gdpElaborationApplication.dataclass.GDP;
import it.univpm.gdpElaborationApplication.dataclass.MetaJson.metadati;

public class Elaborazione{
	private double avg;
	private GDP min;
	private GDP max;
	private double variazione;
	
	public String campi= "\"Avg\",\"Min\",\"Max\",\"Variazione\"";
    
	@metadati(alias="avg", sourcefield="media dei valori rilevati", type="double")
	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	@metadati(alias="min", sourcefield="minimo rilevarione riga", type="GDP")

	public GDP getMin() {
		return min;
	}

	public void setMin(GDP min) {
		this.min = min;
	}
	@metadati(alias="max", sourcefield="massimo rilevarione riga", type="GDP")
	public GDP getMax() {
		return max;
	}

	public void setMax(GDP max) {
		this.max = max;
	}
	@metadati(alias="variazione", sourcefield="variazione percentuale fra prima e ultima rilevazione", type="GDP")
	public double getVariazione() {
		return variazione;
	}

	public void setVariazione(double variazione) {
		this.variazione = variazione;
	}
	
	
	public Elaborazione() {
		this.avg = 0;
		this.min = null;
		this.max = null;
		this.variazione = 0;
	}
	
	
	public static double Media(Vector<GDP> gdpVect){
		double somma=0;
		for (int i =0; i <gdpVect.size(); i++) {
			somma += gdpVect.get(i).getValue();
		}
		return (somma/gdpVect.size());
	}

	public GDP Minimo(Vector<GDP> gdpVect) {
		GDP min=gdpVect.get(0);
		for(int i=0; i<gdpVect.size()-1; i++) {
			if(gdpVect.get(i).getValue() < min.getValue()) {
				min=gdpVect.get(i);
		    }
		  }
		return min;
	}
	
	public GDP Massimo(Vector<GDP> gdpVect) {
		GDP max=gdpVect.get(0);
		for(int i=0; i<gdpVect.size()-1; i++) {
			if(gdpVect.get(i).getValue() > max.getValue()) {
				max=gdpVect.get(i);
		    }
		  }
		return max;
	}
	
	public double Variazione(GDP dateI, GDP dateF) {
		double somma = dateI.getValue()+dateF.getValue();
		double percentuale;  													//controllare la divisione per zero
	    variazione = dateF.getValue()-dateI.getValue();
	    percentuale = (variazione*100)/somma;
	    return percentuale;
	}
	
	public Elaborazione elabora(Vector<GDP> valoririga) {
		Elaborazione datiElab= new Elaborazione();
			datiElab.avg=Media(valoririga);
			datiElab.min=Minimo(valoririga);
			datiElab.max=Massimo(valoririga);
			datiElab.variazione=Variazione(valoririga.get(0),valoririga.get(valoririga.size()-1));
    	return datiElab;
    }
	
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
