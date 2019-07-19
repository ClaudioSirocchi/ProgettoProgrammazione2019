# Progetto Programmazione Ad Oggetti

Il progetto presente in questa repository si occupa di modellare un data set ottenuto da un link contenente diversi contenuti. Di tale link si andra a selezionare il file csv che contiene il data-set da elaborare. L' elaborazione del dataset è effettuata sviluppando un software Java sfruttando i vantaggi della programmazione ad aggetti per modellare mediante opportune classi e metodi la struttura dati e per definire le funzion richieste in fase di specifiche del progetto. Le funzioni poi vengono sfruttate da Spring, un framework concepito per lo sviluppo di applicazioni in java, che consente di avviare un server web locale che ci permette di effettuare richieste GET che, tramite una rigorosa configurazione del controller di spring e delle funzioni con cui quest'ultimo si interfaccia, restituiscono dati in formato JSON ai quali è possibile applicare una serie di filtri, specificandoli direttamente tramite il metodo GET, sui dati di partenza.

Nel nostro caso il file csv in analisi contiene un'indagine statistica sul GDP, cioè il prodotto interno lordo, di diverse localita in diversi periodi, ognuno dei quali ha un'anno e un rispettivo valore per quell'anno. I campi del dataset sono:
FREQ, GEO,UNIT,OBJECTIV\TIME_PERIOD
a questi campi seguono una serie di dati composti da anno e valore in corrispondenza dell'anno.

