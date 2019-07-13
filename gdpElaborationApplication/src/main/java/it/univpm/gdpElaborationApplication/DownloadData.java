package it.univpm.gdpElaborationApplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


public class DownloadData {
	public static void selectUrl() throws IOException {
		Scanner input = new Scanner(System.in);
		BufferedReader urlinput = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Benvenuto nel programma di analisi dei GDP europei"
				+ "\nDigita 1 se vuoi inserire un url oppupre 2 per l'url di default");
		int selector=input.nextInt();
		switch (selector) {
		case 1:
			System.out.println("inserisci l'url");
			String selurl=urlinput.readLine();
			DownloadData.DownloadUrlData(selurl);
			break;
		case 2:
			DownloadData.DownloadUrlData();
			break;
		}
		input.close();
	}
	
	public static void DownloadUrlData() {

		String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=32qw3x5LQIpuI8y6C1Lfhg";
		try {
			
			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
				   System.out.println( line );
			   }
			 } finally {
			   in.close();
			 }
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));
			
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");
			        String urlD = (String)o1.get("url");
			        System.out.println(format + " | " + urlD);
			        if(format.equals("http://publications.europa.eu/resource/authority/file-type/CSV")) {
			        	System.out.println( "OK, Il link in uso contiene un csv, Effettuo il download!" );
			        	Download(urlD, "gdp.csv");
			        	System.out.println("Di seguito altri elementi presenti nel link:\n");
			        }
			    }
			}
			System.out.println( "Download Effettuato" );
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void DownloadUrlData(String selectedUrl) {

		String url = selectedUrl;
		try {
			
			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
				   System.out.println( line );
			   }
			 } finally {
			   in.close();
			 }
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));
			
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");
			        String urlD = (String)o1.get("url");
			        System.out.println(format + " | " + urlD);
			        if(format.equals("http://publications.europa.eu/resource/authority/file-type/CSV")) {
			        	System.out.println( "OK, Il link in uso contiene un csv, Effettuo il download!" );
			        	Download(urlD, "gdp.csv");
			        	System.out.println("Di seguito altri elementi presenti nel link:\n");
			        }
			    }
			}
			System.out.println( "Download Effettuato" );
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void Download(String url, String fileName) throws Exception {
	    try (InputStream in = URI.create(url).toURL().openStream()) {
	        Files.copy(in, Paths.get(fileName),StandardCopyOption.REPLACE_EXISTING);
	    }
	}
}
