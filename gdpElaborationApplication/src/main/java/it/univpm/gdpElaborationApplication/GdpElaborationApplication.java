package it.univpm.gdpElaborationApplication;

import java.io.*;
import java.util.Vector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.gdpElaborationApplication.dataclass.Rilevazione;

/**
 * Classe GdpElaborationApplication contenente il main
 * @author Pistagnesi Sirocchi
 *
 */
@SpringBootApplication
public class GdpElaborationApplication {

	private static Vector<Rilevazione> tabella;
	private static Filtri fileFilter;

	public static void main(String[] args) throws IOException, NoSuchMethodException{
		SpringApplication.run(GdpElaborationApplication.class, args);
		Parsing.selectUrl();
		tabella = Parsing.fileParsing();
		tabella = Parsing.zeroDelete(tabella);
		Parsing.outputCsvData(tabella);
		System.out.println("Ho eliminato tutte le righe contenenti valori delle rilevazioni tutti nulli\n"
				+ "Ho salvato il file elaborato outputData.csv, per visionarlo sarà sufficente importarlo in excel usando la \",\" come separatore");
		Filtri.getMetaDati();
		System.out.println("Ho salvato il file Json contenente i metadati del DataSet\nOra è possibile usare le funzioni messe a disposizione dal WebServer");
		fileFilter = new Filtri(tabella);
	}


}
