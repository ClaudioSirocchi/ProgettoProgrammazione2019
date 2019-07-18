package it.univpm.gdpElaborationApplication.dataclass;

import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public interface MetaJson{

	static void jsonMetaRilevazione(Class<?> rilevazione, String...campiRilev) throws NoSuchMethodException, IOException{
		BufferedWriter JsonFile = new BufferedWriter(new FileWriter("savedfile/metadati.json"));
		JsonFile.write("[\n");
		GDP g1= new GDP();
		for(int i=0; i<campiRilev.length; i++){
			Method attribRilev = rilevazione.getMethod("get"+campiRilev[i]);
			metadati JsonData = attribRilev.getAnnotation(metadati.class);
			if(i>campiRilev.length-2) {//serve a non mettere la virgola in ultima posizione per mantenere la formattazione del json e per inserire la struttura composta GDP;
				JsonFile.write("{\n\"alias\":"+g1.creaMetaDati()+",\n\"source field\":\""+ JsonData.sourcefield() +"\",\n\"type\":\"" + JsonData.type() + "\"\n}");//manca l'ultima virgola	
			}
			else {
				JsonFile.write("{\n\"alias\":\"" + JsonData.alias() +"\",\n\"source field\":\"" + JsonData.sourcefield() +"\",\n\"type\":\"" + JsonData.type() + "\"\n},");
				JsonFile.newLine();
			}
		}
		JsonFile.write("\n]");
		JsonFile.close();
	
}
	
	static String jsonMetaGDP(Class<?> GDP, String...campi) throws NoSuchMethodException, IOException{
		String gdpJson="[";
		String appoggio="";
		for(int i=0; i<campi.length; i++){
			Method attribGDP = GDP.getMethod("get"+campi[i]);
			metadati JsonData = attribGDP.getAnnotation(metadati.class);
			if(i>campi.length-2) {//serve a non mettere la virgola in ultima posizione per mantenere la formattazione del json e per inserire la struttura composta GDP;
				appoggio=gdpJson.concat("{\n\"alias\":\"" + JsonData.alias() +"\",\n\"source field\":\"" + JsonData.sourcefield() +"\",\n\"type\":\"" + JsonData.type() + "\"\n}");
				gdpJson=appoggio;
			}
			else {
				appoggio=gdpJson.concat("{\n\"alias\":\"" + JsonData.alias() +"\",\n\"source field\":\"" + JsonData.sourcefield() +"\",\n\"type\":\"" + JsonData.type() + "\"\n},");//manca l'ultima virgola
				gdpJson=appoggio;
			}
		}
		appoggio=gdpJson.concat("]");
		return appoggio;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@interface metadati{
		String alias();
		String sourcefield();
		String type();
	}

}