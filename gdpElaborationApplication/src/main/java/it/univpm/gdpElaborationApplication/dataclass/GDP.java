package it.univpm.gdpElaborationApplication.dataclass;

public class GDP {

	private int date;
	private double value;
	
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public double getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}	
	public GDP(int date, double value) {
		super();
		this.date = date;
		this.value = value;
	}
}
