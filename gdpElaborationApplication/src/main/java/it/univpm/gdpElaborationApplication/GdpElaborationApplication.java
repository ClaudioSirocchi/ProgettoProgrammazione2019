package it.univpm.gdpElaborationApplication;

import java.io.*;
import java.util.Vector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import it.univpm.gdpElaborationApplication.dataclass.Rilevazione;

@SpringBootApplication
public class GdpElaborationApplication {

	private static Vector<Rilevazione> tabella;

	public static void main(String[] args) throws IOException{
		SpringApplication.run(GdpElaborationApplication.class, args);
		Parsing.selectUrl();
		tabella = Parsing.fileParsing();
		tabella=Parsing.zeroDelete(tabella);
		System.out.println(tabella.toString());
		Parsing.outputCsvData(tabella);
		System.out.println("ho salvato il file aggiornato della tabella");
	}


}
