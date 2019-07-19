# Progetto Programmazione Ad Oggetti

Il progetto presente in questa repository si occupa di modellare un data set ottenuto da un link contenente diversi contenuti. Di tale link si andra a selezionare il file csv che contiene il data-set da elaborare. L' elaborazione del dataset è effettuata sviluppando un software Java sfruttando i vantaggi della programmazione ad aggetti per modellare mediante opportune classi e metodi la struttura dati e per definire le funzion richieste in fase di specifiche del progetto. Le funzioni poi vengono sfruttate da Spring, un framework concepito per lo sviluppo di applicazioni in java, che consente di avviare un server web locale che ci permette di effettuare richieste GET che, tramite una rigorosa configurazione del controller di spring e delle funzioni con cui quest'ultimo si interfaccia, restituiscono dati in formato JSON ai quali è possibile applicare una serie di filtri, specificandoli direttamente tramite il metodo GET, sui dati di partenza.

Nel nostro caso il file csv in analisi contiene un'indagine statistica sul GDP, cioè il prodotto interno lordo, di diverse localita in diversi periodi, ognuno dei quali ha un'anno e un rispettivo valore per quell'anno. I campi del dataset sono:
FREQ, GEO,UNIT,OBJECTIV\TIME_PERIOD
a questi campi seguono una serie di dati composti da anno e valore in corrispondenza dell'anno.

## Funzionamento
All'avvio (!ATTENZIONE!: potrebbe essere necessario reimportare la libreria json-simple.jar presente all'interno della cartella library)del programma all'interno del main verrà avviata l'applicazione springboot tuttavia prima di effettuare qualsiasi operazione è necessario indicare al programma se si vuole utilizzare l'url di default(quello assegnato in sede di specifica del progetto) oppure utilizzare un proprio url(Parsing.selectUrl()). 
Si è deciso di offrire questa possibilità in quando trattando il data-set una rilevazione statistica del GDP si presume che di anno in anno questa analisi venga aggiornata di conseguenza sarà possibile negli anni futuri riutilizzare il software per analizzare strutture dati de medesimo tipo(sebbene il file in nostra analisi contenga le rilevazioni di 18 anni tuttavia il software è stato sviluppato,mediante metodi come length() e getsize() messi a disposizione dalle varie classi, in modo tale che il numero di anni su cui effettuare la rilevazione sia variabile quindi se si inserisce un file che contiene ad esempio solo 5 rilevazione il software si adattera su una struttura con quel numero di rilevazioni). 

Dopo aver indicato al main come si vuole procedere quest'ultimo provvedere a effettuareil download del file,salvarlo("savedfile/gdp.csv") e successivamente effettuare il parsing del data-set scaricato e memorizzarlo all'interno di una variabile di tipo Vector<Rilevazione> tabella, cioè un vettore in cui ogni elemento corrisponde ad una riga del data-set, analizzando il file scaricando e procedendo alla rimozione di tutte le righe che contengo rilevazioni nulle per ogni data(Parsing.selectUrl(); tabella = Parsing.fileParsing();tabella = Parsing.zeroDelete(tabella);).
  
Il file elaborato(cioè senza righe nulle) oltre ad essere memorizzato in una variabile verrà salvato in formato csv ("savedfile/outputData.csv") ma con i separatori rielaborati in modo tale che sarà possibile visualizzarlo importandolo in excel (usando la virgola come separatore). Successivamente il main richiamerà la funzione Filtri.getMetaDati(); e salverà un file ("savedfile/metadati.json") che conterrà i metadati del data-set in formato json.

Effettuate queste operazioni il main crea un nuovo oggetto di tipo Filtri. Da qui in poi si potrà interagire direttamente con il software utilizzando direttamente i comandi GET,sfruttando software quali ad esempio Postman di cui si riportano alcuni esempi:

#### Comandi GET:
*!Attenzione! potrebbe essere necessario copiare e incollare manualmente la parte che specifica i filtri

- (http://localhost:8080/dati) stampa tutte le righe

- * (http://localhost:8080/media?operatore=<&valore=0.04) stampa tutte le righe che hanno la media inferiore a 0.04

- (http://localhost:8080/media) stampa tutte le righe e la loro media

- * (http://localhost:8080/media?operatore=>&valore=0.04) stampa tutte le righe che hanno la media superiore a 0.04

- (http://localhost:8080/dati?campo=geo&valore=AT) stampa tutte le righe che il cui valore geo corrisponde a AT

- (http://localhost:8080/dati?campo=oggetto&valore=OBJ01) stampa tutte le righe che hanno come oggetto OBJ01

- (http://localhost:8080/variazione) stampa tutte le righe che contengono una variazione fra la prima data e l’ultima data dell’oggetto gdp 70%

- * (http://localhost:8080/variazione?operatore=<&valore=70) stampa tutte le righe che contengono una variazione fra la prima data e l’ultima data dell’oggetto gdp 70%

- * (http://localhost:8080/variazione/2010/2012?operatore=>&valore=20) stampa tutte le righe che contengono una variazione fra la data 2010 e la data 2012 maggiore del 20%

- (http://localhost:8080/variazione/2011/2013) stampa tutte le righe che contengono una variazione fra la data 2010 e la data 2012

## Diagrammi 

### UML Struttura
Nel seguente uml è possibile visualizzare come è organizzata la struttura del programma.
![Alt text](https://github.com/ClaudioSirocchi/ProgettoProgrammazione2019/blob/master/gdpElaborationApplication/diagrammi/PackageApplication.jpg?raw=true)

### UML Data-Set
Nel seguente uml è possibile visionare la gestione delle classi usate per gestire i dati ottenuti dal dataset.
![Alt text](https://github.com/ClaudioSirocchi/ProgettoProgrammazione2019/blob/master/gdpElaborationApplication/diagrammi/DataSetjpg.jpg?raw=true)

### UML Use Case
Nel seguente uml è possibile visionare i servizi offerti dal programma e come esso si comporta.
![Alt text]
(https://github.com/ClaudioSirocchi/ProgettoProgrammazione2019/blob/master/gdpElaborationApplication/diagrammi/UseCase.jpg)

### UML Sequence Diagram
Nel seguente uml è possibile visionare la rappresentazione di come interagiscono i vari oggetti nell’arco temporale.
![Alt text]
(https://github.com/ClaudioSirocchi/ProgettoProgrammazione2019/blob/master/gdpElaborationApplication/diagrammi/SequenceDiagram.JPG)
