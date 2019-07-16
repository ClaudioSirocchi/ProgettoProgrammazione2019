package it.univpm.gdpElaborationApplication.dataclass;

import java.io.IOException;

import it.univpm.gdpElaborationApplication.dataclass.MetaJson.metadati;

public class GDP {
	
	private int date;
	private double value;
	
	@metadati(alias="Date", sourcefield="Data Rilevazione", type="int")
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	@metadati(alias="Value", sourcefield="Valore di rilevazione", type="double")
	public double getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}	
	public GDP(int date, double value) {
		this.date = date;
		this.value = value;
	}
	public GDP() {
		this.date = 0;
		this.value = 0;
	}
	@Override
	public String toString() {
		return "date=" + date + ", value=" + value;
	}
	
	public String creaMetaDati() throws NoSuchMethodException, IOException{
		String campi[]= {"Date","Value"};
		return MetaJson.jsonMetaGDP(this.getClass(), campi);
	}

}
