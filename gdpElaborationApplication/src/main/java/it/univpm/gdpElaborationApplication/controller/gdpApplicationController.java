package it.univpm.gdpElaborationApplication.controller;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.gdpElaborationApplication.Filtri;

/**
 * Classe gdpApplicationController
 * @author Pistagnesi Sirocchi
 *
 */
@RestController
public class gdpApplicationController {
		
		/**
		 * Restituisce i dati contenuti in una tabella oppure con l'utilizzo dei parametri, tramite Spring, restituisce i campi della tabella
		 * che contengono un oggetto indicato oppure i campi della tabella che contengono un geo indicato
		 * @return search
		 */
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
		
		/**
		 * Restituisce il valore medio gdp dei campi contenuti in una tabella oppure con l'utilizzo dei parametri, tramite Spring, 
		 * restituisce il valore medio gdp dei campi contenuti in una tabella maggiori di un determinato valore passato come
		 * parametro oppure restituisce il valore medio gdp dei campi contenuti in una tabella minori di un determinato valore passato come
		 * parametro
		 * @return Filtri.media
		 */
		@GetMapping("/media")
		public JSONArray media(@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
		if(operatore.equals("<")|operatore.equals(">"))
			return Filtri.filterAvg(operatore,valore);
		else
			return Filtri.media();
		}
		
		/**
		 * Restituisce il valore massimo gdp dei campi contenuti in una tabella oppure con l'utilizzo dei parametri, tramite Spring, 
		 * restituisce il valore massimo gdp dei campi contenuti in una tabella maggiori di un determinato valore passato come
		 * parametro oppure restituisce il valore massimo gdp dei campi contenuti in una tabella minori di un determinato valore passato come
		 * parametro
		 * @return Filtri.max
		 */
		@GetMapping("/massimo")
		public JSONArray max(@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
			if(operatore.equals("<")|operatore.equals(">"))
				return Filtri.filterMax(operatore,valore);
			else
				return Filtri.max();
			}
		
		/**
		 * Restituisce il valore minimo gdp dei campi contenuti in una tabella oppure con l'utilizzo dei parametri, tramite Spring, 
		 * restituisce il valore minimo gdp dei campi contenuti in una tabella maggiori di un determinato valore passato come
		 * parametro oppure restituisce il valore minimo gdp dei campi contenuti in una tabella minori di un determinato valore passato come
		 * parametro
		 * @return Filtri.min
		 */
		@GetMapping("/minimo")
		public JSONArray min(@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
			if(operatore.equals("<")|operatore.equals(">"))
				return Filtri.filterMin(operatore,valore);
			else
				return Filtri.min();
			}

		@GetMapping("/variazione")
		public JSONArray variazione(@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
			if(operatore.equals("<")|operatore.equals(">"))
				return Filtri.filterVar(operatore,valore);
			else
				return Filtri.variazione();
		}
		
	
		@GetMapping("/variazione/{date1}/{date2}")
		public JSONArray variazione(@PathVariable("date1") int data1,@PathVariable("date2") int data2,@RequestParam(name="operatore",defaultValue="0") String operatore, @RequestParam(name="valore",defaultValue="0") double valore) {
			if (operatore.equals("<")|operatore.equals(">")){
				return Filtri.variazione(data1, data2, operatore, valore);

			}
			else
				return Filtri.variazione(data1,data2);
		}
		
		
}

