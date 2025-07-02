/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

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
