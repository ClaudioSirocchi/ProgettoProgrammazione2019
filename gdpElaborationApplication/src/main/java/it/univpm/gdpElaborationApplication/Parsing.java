package it.univpm.gdpElaborationApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

import it.univpm.gdpElaborationApplication.dataclass.GDP;
import it.univpm.gdpElaborationApplication.dataclass.Rilevazione;

public class Parsing {
private static Scanner input;
final static String DELIMITER1 = ";|,";	

	public static void selectUrl() throws IOException {
		input = new Scanner(System.in);
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
	}
	
	public static Vector<Rilevazione> fileparsing() {
		
			Vector<Rilevazione> tabrilev = new Vector<Rilevazione>();
			
			try (BufferedReader br = new BufferedReader(new FileReader("gdp.csv"))) {
				String line;
				int startyear=0;
				while ((line = br.readLine()) != null) {
					String[] campi = line.split(DELIMITER1);
					if(startyear!=0)
						tabrilev.add(new Rilevazione (campi[0].charAt(0), campi[1], campi[2], campi[3], addOnGdpSer(campi,startyear) ));
					else {
						campi[4]=campi[4].trim();
						startyear=Integer.parseInt(campi[4]);
					}	
				}
				br.close();
			} catch (IOException i) {
				i.printStackTrace();
				}
			return tabrilev;
	}
	
	private static Vector<GDP> addOnGdpSer(String[] campi,int starty) {
		Vector<GDP> gdpdata = new Vector<GDP>();
		for(int i=4;i<campi.length;i++) {
		gdpdata.add(new GDP((starty++), Double.parseDouble(campi[i].trim())));
		}
		/* Trim serve ad eliminare gli spazi a inizio e fine stringa*/
		return gdpdata;
	}
}