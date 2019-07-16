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

public class Parsing {
final static String DELIMITER1 = ";|,";
private static String []header; //usato per stampare il nuovo file csv

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
	
	public static Vector<Rilevazione> fileParsing() {
		
			Vector<Rilevazione> tabrilev = new Vector<Rilevazione>();
			
			try (BufferedReader br = new BufferedReader(new FileReader("gdp.csv"))) {
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

	private static Vector<GDP> addOnGdp(String[] campi,int starty) {
		Vector<GDP> gdpdata = new Vector<GDP>();
		for(int i=4;i<campi.length;i++) {
		gdpdata.add(new GDP((starty++), Double.parseDouble(campi[i].trim())));
		}
		/* Trim serve ad eliminare gli spazi a inizio e fine stringa*/
		return gdpdata;
	}
	
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
	
	public static void outputCsvData(Vector<Rilevazione> tabella) 
	{ 
	    File file = new File("outputData.csv"); 
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
	
	/**public static void outputJson() {
		//First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");
         
        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);
         
        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");
         
        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);
         
        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);
         
        //Write JSON file
        try (FileWriter file = new FileWriter("employees.json")) {
 
            file.write(employeeList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}*/
}