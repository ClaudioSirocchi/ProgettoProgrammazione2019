package it.univpm.gdpElaborationApplication.dataclass;

import java.io.IOException;
import java.util.Vector;
import it.univpm.gdpElaborationApplication.dataclass.MetaJson.metadati;

public class Rilevazione {
	
	private char frequenza;
	private String geo;
	private String unit;
	private String obj;
	private Vector<GDP> gdpdata;
	
	@metadati(alias="Frequenza", sourcefield="Frequenza di Rilevazione", type="Char")
	public char getFrequenza() {
		return frequenza;
	}
	public void setFrequenza(char frequenza) {
		this.frequenza = frequenza;
	}
	
	@metadati(alias="Geo", sourcefield="Località geografica Rilevazione", type="String")
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	
	@metadati(alias="Unit", sourcefield="Unità di rilevamento", type="String")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@metadati(alias="Obj", sourcefield="Nome oggetto di rilevazione", type="String")
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	
	@metadati(alias="Gdpdata", sourcefield="Vettore di rilevazioni", type="Char")
	public Vector<GDP> getGdpdata() {
		return gdpdata;
	}
	
	public void setGdpdata(Vector<GDP> gdpdata) {
		this.gdpdata = gdpdata;
	}

	public Rilevazione(char frequenza, String geo, String unit, String obj, Vector<GDP> gdpdata) {
		this.frequenza = frequenza;
		this.geo = geo;
		this.unit = unit;
		this.obj = obj;
		this.gdpdata=gdpdata;
	}
	
	public Rilevazione() {
		this.frequenza='\0';
		this.geo ="";
		this.unit = "";
		this.obj = "";
		this.gdpdata=null;
	}
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
