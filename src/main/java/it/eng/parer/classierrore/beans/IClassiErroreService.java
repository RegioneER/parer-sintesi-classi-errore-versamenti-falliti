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

import it.eng.parer.classierrore.beans.model.ClassiErroreResponse;
import jakarta.validation.constraints.NotBlank;

public interface IClassiErroreService {

    /**
     * Restituisce la lista delle classi di errore per versamenti falliti
     *
     * @param userId     userdId utente associato alla struttura per la quale ricercare i versamenti
     *                   falliti
     * @param nmAmbiente nome ambiente SACER
     * @param nmEnte     nome ente SACER
     * @param nmStrut    nome struttura SACER
     * @param uri        absolute uri
     *
     * @return la response decorata (vedi {@link ClassiErroreResponse})
     */
    ClassiErroreResponse listClassiErrorePerVarsFalliti(
	    @NotBlank(message = "userId non valorizzato") String userId, String nmAmbiente,
	    String nmEnte, String nmStrut, String uri);

}
