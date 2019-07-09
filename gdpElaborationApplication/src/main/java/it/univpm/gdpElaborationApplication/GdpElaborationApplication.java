package it.univpm.gdpElaborationApplication;

import java.io.*;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GdpElaborationApplication {

	

	
	public static void main(String[] args) throws IOException{
		SpringApplication.run(GdpElaborationApplication.class, args);
		selectUrl();

	}
	
	private static Scanner input;

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

}
