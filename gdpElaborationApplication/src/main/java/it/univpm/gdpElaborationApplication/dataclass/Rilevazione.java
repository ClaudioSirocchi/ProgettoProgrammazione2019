package it.univpm.gdpElaborationApplication.dataclass;

public class Rilevazione {
	private char frequenza;
	private String geo;
	private String unit;
	private String obj;
	private GDP[] gdp;
	
	public char getFrequenza() {
		return frequenza;
	}
	public void setFrequenza(char frequenza) {
		this.frequenza = frequenza;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public GDP[] getGdp() {
		return gdp;
	}
	public void setGdp(GDP[] gdp) {
		this.gdp = gdp;
	}
	
}
