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

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package it.eng.parer.classierrore.beans.dao;

import java.util.stream.Stream;

import org.hibernate.jpa.HibernateHints;

import it.eng.parer.classierrore.beans.IClassiErroreDao;
import it.eng.parer.classierrore.beans.dto.CodiceErroreBean;
import it.eng.parer.classierrore.jpa.IamAbilOrganiz;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class ClassiErroreDao implements IClassiErroreDao {

    private static final String CHECK_UTENTE_ABILITATO_STRUT = "SELECT abilOrg from IamAbilOrganiz abilOrg "
	    + "join OrgStrut strut on (strut.idStrut = abilOrg.idOrganizApplic and strut.flCessato = '0') "
	    + "where strut.nmStrut = :nmStrut AND strut.orgEnte.nmEnte = :nmEnte "
	    + "and abilOrg.iamUser.nmUserid = :nmUserid ";

    private static final String FIND_CODICI_ERRORE_VERS_FALLITI = "SELECT new it.eng.parer.classierrore.beans.dto.CodiceErroreBean "
	    + "(ud.cdRegistroKeyUnitaDoc, ud.aaKeyUnitaDoc, ud.cdKeyUnitaDoc, v.cdErrPrinc, COALESCE(v.dtChiusura, ud.dtLastSesErr)) "
	    + "FROM VrsUnitaDocNonVer ud LEFT JOIN VrsSessioneVersKo v "
	    + "on (ud.cdRegistroKeyUnitaDoc = v.cdRegistroKeyUnitaDoc "
	    + "AND ud.aaKeyUnitaDoc = v.aaKeyUnitaDoc AND ud.cdKeyUnitaDoc = v.cdKeyUnitaDoc AND ud.orgStrut.idStrut = v.orgStrut.idStrut AND v.tiSessioneVers = 'VERSAMENTO' ) "
	    + "JOIN ud.orgStrut strut JOIN strut.orgEnte ente "
	    + "WHERE strut.nmStrut = :nmStrut AND ente.nmEnte = :nmEnte and strut.flCessato = '0' ";

    @Inject
    EntityManager entityManager;

    // strad fac
    @Override
    public Stream<CodiceErroreBean> findCodiciErrorePerVersFalliti(String nmAmbiente, String nmEnte,
	    String nmStrut) {
	TypedQuery<CodiceErroreBean> query = entityManager
		.createQuery(FIND_CODICI_ERRORE_VERS_FALLITI, CodiceErroreBean.class);

	// hibernate hint
	query.setHint(HibernateHints.HINT_READ_ONLY, true);
	query.setHint(HibernateHints.HINT_CACHEABLE, true);

	// params
	query.setParameter("nmStrut", nmStrut);
	query.setParameter("nmEnte", nmEnte);

	return query.getResultStream();
    }

    @Override
    public boolean isUtenteAbilitatoStrut(String nmUserid, String nmAmbiente, String nmEnte,
	    String nmStrut) {
	TypedQuery<IamAbilOrganiz> query = entityManager.createQuery(CHECK_UTENTE_ABILITATO_STRUT,
		IamAbilOrganiz.class);

	// hibernate hint
	query.setHint(HibernateHints.HINT_READ_ONLY, true);
	query.setHint(HibernateHints.HINT_CACHEABLE, true);

	// params
	query.setParameter("nmStrut", nmStrut);
	query.setParameter("nmEnte", nmEnte);
	query.setParameter("nmUserid", nmUserid);
	query.setMaxResults(1);

	return !query.getResultList().isEmpty();
    }

}
