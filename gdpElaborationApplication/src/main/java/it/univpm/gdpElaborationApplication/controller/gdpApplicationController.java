package it.univpm.gdpElaborationApplication.controller;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.gdpElaborationApplication.Filtri;



@RestController
public class gdpApplicationController {
		@GetMapping("/media")
		public JSONArray media() {
		return Filtri.media();
		}
		@GetMapping("/massimo")
		public JSONArray max() {
		return Filtri.max();
		}
		@GetMapping("/minimo")
		public JSONArray min() {
		return Filtri.min();
		}
		@GetMapping("/variazione")
		public JSONArray variazione() {
		return Filtri.variazione();
		}
		@GetMapping("/variazioneFromTo")
		public JSONArray variazioneFromTo() {
		return Filtri.variazione();
		}

		
}

