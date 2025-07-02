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

package it.eng.parer.classierrore.beans.dao;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import it.eng.parer.classierrore.Profiles;
import it.eng.parer.classierrore.beans.IClassiErroreDao;
import it.eng.parer.classierrore.beans.dto.CodiceErroreBean;
import jakarta.inject.Inject;

@QuarkusTest
@TestProfile(Profiles.Core.class)
class ClassiErroreDaoTest {

    static final String USERID = "test_microservizi"; // userid locale al db snap/test
    static final String NM_AMBIENTE = "PARER_PROVA"; // ambiente locale al db snap/test
    static final String NM_ENTE = "ente_test"; // ente locale al db snap/test
    static final String NM_STRUT = "PARER_TEST"; // struttura locale al db snap/test
    static final String FAKE_NM_STRUT = "struttura_non_esistente"; // struttura locale al db
								   // snap/test
    static final String FAKE_USERID = "user_non_esistente";

    @Inject
    IClassiErroreDao dao;

    /*
     * Verifica l'assenza di errori e che la query restituisca una lista non nulla.
     */
    @Test
    void findCodiciErrorePerVersFalliti_ok() {
	assertDoesNotThrow(
		() -> dao.findCodiciErrorePerVersFalliti(NM_AMBIENTE, NM_ENTE, NM_STRUT));
	List<CodiceErroreBean> resultList = dao
		.findCodiciErrorePerVersFalliti(NM_AMBIENTE, NM_ENTE, NM_STRUT)
		.collect(Collectors.toList());
	assertNotNull(resultList);
    }

    /*
     * Verifica che la query restituisca una lista vuota quando non ci sono risultati.
     */
    @Test
    void findCodiciErrorePerVersFalliti_emptyResult() {
	List<CodiceErroreBean> resultList = dao
		.findCodiciErrorePerVersFalliti(NM_AMBIENTE, NM_ENTE, FAKE_NM_STRUT)
		.collect(Collectors.toList());
	assertNotNull(resultList);
	assertTrue(resultList.isEmpty());
    }

    /*
     * Verifica che un utente abilitato venga considerato tale.
     */
    @Test
    void isUtenteAbilitatoStrut_ok() {
	boolean result = assertDoesNotThrow(
		() -> dao.isUtenteAbilitatoStrut(USERID, NM_AMBIENTE, NM_ENTE, NM_STRUT));
	assertTrue(result);
    }

    /*
     * Verifica che un utente non abilitato non venga considerato abilitato.
     */
    @Test
    void isUtenteAbilitatoStrut_notAbilitato() {
	boolean result = assertDoesNotThrow(
		() -> dao.isUtenteAbilitatoStrut(FAKE_USERID, NM_AMBIENTE, NM_ENTE, NM_STRUT));
	assertFalse(result);
    }

    /*
     * Verifica che un utente non venga considerato abilitato per una struttura inesistente.
     */
    @Test
    void isUtenteAbilitatoStrut_strutturaNonEsistente() {
	boolean result = assertDoesNotThrow(
		() -> dao.isUtenteAbilitatoStrut(USERID, NM_AMBIENTE, NM_ENTE, FAKE_NM_STRUT));
	assertFalse(result);
    }

}
