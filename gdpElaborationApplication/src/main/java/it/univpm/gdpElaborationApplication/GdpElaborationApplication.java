package it.univpm.gdpElaborationApplication;

import java.io.*;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GdpElaborationApplication {

	

	

	public static void main(String[] args) throws IOException{
		SpringApplication.run(GdpElaborationApplication.class, args);
		Parsing.selectUrl();

	}

	

}
