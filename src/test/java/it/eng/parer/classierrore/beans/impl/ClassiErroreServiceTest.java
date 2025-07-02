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

package it.eng.parer.classierrore.beans.impl;

import org.apache.commons.lang3.StringUtils;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import it.eng.parer.classierrore.Profiles;
import it.eng.parer.classierrore.beans.IClassiErroreService;
import it.eng.parer.classierrore.beans.model.ClassiErroreResponse;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;

@QuarkusTest
@TestProfile(Profiles.Core.class)
class ClassiErroreServiceTest {

    static final String USERID = "test_microservizi"; // userid locale al db snap/test
    static final String NM_AMBIENTE = "PARER_PROVA"; // ambiente locale al db snap/test
    static final String NM_ENTE = "ente_test"; // ente locale al db snap/test
    static final String NM_STRUT = "PARER_TEST"; // struttura locale al db snap/test
    static final String FAKE_NM_STRUT = "struttura_non_esistente";
    static final String FAKE_USERID = "user_non_esistente";

    @Inject
    IClassiErroreService service;

    /*
     * Verifica il comportamento corretto quando l'utente è abilitato e vengono restituite delle
     * classi di errore.
     */
    @Test
    void listClassiErrorePerVarsFalliti_utenteAbilitato_ok() {
	ClassiErroreResponse response = assertDoesNotThrow(
		() -> service.listClassiErrorePerVarsFalliti(USERID, NM_AMBIENTE, NM_ENTE, NM_STRUT,
			StringUtils.EMPTY));
	assertNotNull(response);
	assertEquals(NM_AMBIENTE + " / " + NM_ENTE + " / " + NM_STRUT, response.getStruttura());
	assertTrue(response.getTotale() >= 0);
	assertNotNull(response.getClassiErrore());
    }

    /*
     * Verifica che un utente non abilitato riceva una lista di classi di errore vuota.
     */
    @Test
    void listClassiErrorePerVarsFalliti_utenteNonAbilitato_ok() {
	ClassiErroreResponse response = assertDoesNotThrow(
		() -> service.listClassiErrorePerVarsFalliti(FAKE_USERID, NM_AMBIENTE, NM_ENTE,
			NM_STRUT, StringUtils.EMPTY));
	assertNotNull(response);
	assertEquals(NM_AMBIENTE + " / " + NM_ENTE + " / " + NM_STRUT, response.getStruttura());
	assertEquals(0, response.getTotale());
	assertTrue(response.getClassiErrore().isEmpty());
    }

    /*
     * Verifica che un utente vuoto generi errore.
     */
    @Test
    void listClassiErrorePerVarsFallitiEmptyUserid_ko() {
	assertThrows(ConstraintViolationException.class,
		() -> service.listClassiErrorePerVarsFalliti(StringUtils.EMPTY, null, null, null,
			StringUtils.EMPTY));
    }

    /*
     * Verifica il comportamento corretto quando la struttura non esiste.
     */
    @Test
    void listClassiErrorePerVarsFalliti_strutturaNonEsistente_ok() {
	ClassiErroreResponse response = assertDoesNotThrow(
		() -> service.listClassiErrorePerVarsFalliti(USERID, NM_AMBIENTE, NM_ENTE,
			FAKE_NM_STRUT, StringUtils.EMPTY));
	assertTrue(response.getClassiErrore().isEmpty());
    }

}
