package it.univpm.gdpElaborationApplication.controller;

import java.io.File;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.gdpElaborationApplication.Filtri;
import it.univpm.gdpElaborationApplication.dataclass.GDP;



@RestController
public class gdpApplicationController {
		@GetMapping("/dati")
		public JSONArray dati(@RequestParam(name="campo", defaultValue="0") String campo,@RequestParam(name="valore", defaultValue="0") String valore) {
		JSONArray search;
		switch (campo) {
		case "geo":
			search=Filtri.searchGeo(valore);
			break;
		case "oggetto":
			search=Filtri.searchOggetto(valore);
			break;
		default:
			search=Filtri.dati();
			}
		return search;
		}
		
		@GetMapping("/media")
		public JSONArray media(@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
		if(operatore.equals("<")|operatore.equals(">"))
			return Filtri.filterAvg(operatore,valore);
		else
			return Filtri.media();
		}
		
		@GetMapping("/massimo")
		public JSONArray max(@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
			if(operatore.equals("<")|operatore.equals(">"))
				return Filtri.filterMax(operatore,valore);
			else
				return Filtri.max();
			}
		
		@GetMapping("/minimo")
		public JSONArray min(@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
			if(operatore.equals("<")|operatore.equals(">"))
				return Filtri.filterMin(operatore,valore);
			else
				return Filtri.min();
			}
		
		@GetMapping("/variazione")
		public JSONArray variazione() {
		return Filtri.variazione();
		}
		
		@GetMapping("/variazione/{date1}/{date2}")
		public JSONArray variazione(@PathVariable("date1") int date1,@PathVariable("date2") int date2) {
		return Filtri.variazione(date1,date2);
		}
		
		
}

