package it.univpm.gdpElaborationApplication.dataclass;

import java.util.Vector;

public class Rilevazione {


	private char frequenza;
	private String geo;
	private String unit;
	private String obj;
	private Vector<GDP> gdpdata;
	private double appop;
	
	public double getAppop() {
		return appop;
	}
	public void setAppop(double appop) {
		this.appop = appop;
	}
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
		if(appop!=0) {
			lineString[riga.gdpdata.size()]=Double.toHexString(appop);
		}
		return lineString;
	}
	
}
