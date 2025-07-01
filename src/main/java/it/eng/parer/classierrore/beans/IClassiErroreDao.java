package it.eng.parer.classierrore.beans;

import java.util.stream.Stream;

import it.eng.parer.classierrore.beans.dto.CodiceErroreBean;

public interface IClassiErroreDao {

    /**
     * Restituisce la stream con lista dei CODICI ERRORE (NON CLASSI!) per ogni versamento fallito
     *
     * @param nmAmbiente il nome dell'ambiente SACER
     * @param nmEnte     il nome dell'ente SACER
     * @param nmStrut    il nome della struttura SACER
     *
     * @return uno Stream di @CodiceErroreBean con la lista di tutti i versamenti falliti e relativo
     *         codice di errore
     */
    Stream<CodiceErroreBean> findCodiciErrorePerVersFalliti(String nmAmbiente, String nmEnte,
	    String nmStrut);

    /**
     * Verifica se l'utente che invoca il servizio è abilitato alla struttura per la quale richiede
     * l'elenco delle classi di errore
     *
     * @param nmAmbiente il nome dell'ambiente SACER
     * @param nmEnte     il nome dell'ente SACER
     * @param nmStrut    il nome della struttura SACER
     *
     * @return true se l'utente è abilitato, false altrimenti
     */
    public boolean isUtenteAbilitatoStrut(String nmUserid, String nmAmbiente, String nmEnte,
	    String nmStrut);

}
