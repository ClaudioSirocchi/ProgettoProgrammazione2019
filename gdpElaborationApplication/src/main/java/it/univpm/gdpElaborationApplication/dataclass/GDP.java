package it.univpm.gdpElaborationApplication.dataclass;

<<<<<<< HEAD
import java.io.IOException;

import it.univpm.gdpElaborationApplication.dataclass.MetaJson.metadati;
=======
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392

public class GDP {
	
	private int date;
	private double value;
	
<<<<<<< HEAD
	@metadati(alias="Date", sourcefield="Data Rilevazione", type="int")
=======
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
<<<<<<< HEAD
	@metadati(alias="Value", sourcefield="Valore di rilevazione", type="double")
=======
>>>>>>> 31342636a555e0e57bb9878c8689cff42004b392
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
	
	public GDP(int date, double value) {
		this.date = date;
		this.value = value;
	}
<<<<<<< HEAD
	public GDP() {
		this.date = 0;
		this.value = 0;
	}
=======
	@Override
	public String toString() {
		return "date=" + date + ", value=" + value;
	}
	
	public String creaMetaDati() throws NoSuchMethodException, IOException{
		String campi[]= {"Date","Value"};
		return MetaJson.jsonMetaGDP(this.getClass(), campi);
	}

}
