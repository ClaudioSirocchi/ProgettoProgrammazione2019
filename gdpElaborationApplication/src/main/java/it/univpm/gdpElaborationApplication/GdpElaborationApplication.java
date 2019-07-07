package it.univpm.gdpElaborationApplication;

import java.io.*;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.gdpElaborationApplication.program.DownloadData;

@SpringBootApplication
public class GdpElaborationApplication {

	

	private static Scanner input;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GdpElaborationApplication.class, args);
		DownloadData gdp=new DownloadData();
		input = new Scanner(System.in);
		BufferedReader urlinput = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Benvenuto nel programma di analisi dei GDP europei"
				+ "\nDigita 1 se vuoi inserire un url oppupre 2 per l'url di default");
		int selector=input.nextInt();
		if(selector==1) {
			System.out.println("inserisci l'url");
			String selurl=urlinput.readLine();
			gdp.DownloadUrlData(selurl);
		}
		else if(selector==2){
			gdp.DownloadUrlData();
		}

	}

}
