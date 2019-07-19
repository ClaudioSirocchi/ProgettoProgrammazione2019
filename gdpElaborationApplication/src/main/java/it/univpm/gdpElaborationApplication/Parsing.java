package it.univpm.gdpElaborationApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

import com.opencsv.CSVWriter;

import it.univpm.gdpElaborationApplication.dataclass.GDP;
import it.univpm.gdpElaborationApplication.dataclass.Rilevazione;

/**
 * Classe Parsing che esegue il parsing dei dati
 * @author Pistagnesi Sirocchi
 *
 */
public class Parsing {
final static String DELIMITER1 = ";|,";
private static String []header; //usato per stampare il nuovo file csv

	/**
	 * Seleziona l'Url di default o quello inserito
	 * @throws IOException gestisce gli errori del flusso di lettura dell'url
	 */
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
			input.close();
			break;
		case 2:
			DownloadData.DownloadUrlData();
			input.close();
			break;
		}
	}
	
	/**
	 * Resituisce la tabella delle rilevzioni
	 * @return tabrilev
	 */
	public static Vector<Rilevazione> fileParsing() {
		
			Vector<Rilevazione> tabrilev = new Vector<Rilevazione>();
			
			try (BufferedReader br = new BufferedReader(new FileReader("savedfile/gdp.csv"))) {
				String line;
				int startyear=0;
				while ((line = br.readLine()) != null) {
					String[] campi = line.split(DELIMITER1);
					if(startyear!=0)
						tabrilev.add(new Rilevazione (campi[0].charAt(0), campi[1], campi[2], campi[3], addOnGdp(campi,startyear) ));
					else {
						header=campi;
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

	/**
	 * Restituisce i valori gdp
	 * @param campi
	 * @param starty
	 * @return gdp data
	 */
	private static Vector<GDP> addOnGdp(String[] campi,int starty) {
		Vector<GDP> gdpdata = new Vector<GDP>();
		for(int i=4;i<campi.length;i++) {
		gdpdata.add(new GDP((starty++), Double.parseDouble(campi[i].trim())));
		}
		/* Trim serve ad eliminare gli spazi a inizio e fine stringa*/
		return gdpdata;
	}
	
	/**
	 * Rimuove le righe contenenti solo zero
	 * @param tabella tabella da cui devo eliminare le righe nulle
	 * @return tabella tabella con le righe nulle eliminate 
	 */
	public static Vector<Rilevazione> zeroDelete(Vector<Rilevazione> tabella) {
		for(int i=0;i<tabella.size();i++) {
			Rilevazione riga=tabella.get(i);
			int controller=0;
			Vector<GDP> gdpVect = riga.getGdpdata();
			for(int k=0;k<riga.getGdpdata().size();k++) {
					GDP gdpElem=gdpVect.get(k);
					if(gdpElem.getValue()==0|gdpElem.getValue()==0.0)
						controller++;
			}
			if (controller ==riga.getGdpdata().size())
				tabella.remove(i--);//-- perche se rimuovo una riga il vettore scala le posizioni
			//quindi la riga successiva da esaminare verra posta nella posizione di
			//quella appena eliminata
		}
		return tabella;
	}
	
	/**
	 * Salva il file csv nella posizione "savedfile/outputData.csv"
	 * @param tabella tabella da cui prendere i dati da salvare
	 */
	public static void outputCsvData(Vector<Rilevazione> tabella) 
	{ 
	    File file = new File("savedfile/outputData.csv"); //utilizzando la libreria open csv
	    try { 
	        FileWriter outputfile = new FileWriter(file);
	        CSVWriter writer = new CSVWriter(outputfile);
	        writer.writeNext(header);
	        int vectorSize=4+tabella.get(0).getGdpdata().size();
	        for(int i=0; i<tabella.size();i++) {
	        	Rilevazione riga=tabella.get(i);
		        writer.writeNext(riga.setRigaAsString(riga,vectorSize)); 
	        }
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	}
	
}