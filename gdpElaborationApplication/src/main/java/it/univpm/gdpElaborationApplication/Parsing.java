package it.univpm.gdpElaborationApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Parsing {
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
