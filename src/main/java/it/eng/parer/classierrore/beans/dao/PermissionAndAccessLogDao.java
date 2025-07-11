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

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import it.eng.parer.classierrore.beans.IPermissionAndAccessLogDao;
import static it.eng.parer.classierrore.beans.utils.Costants.SERVICE_NAME;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class PermissionAndAccessLogDao implements IPermissionAndAccessLogDao {

    private static final String AUTH_QUERY = "select 1 from IamUser iu "
	    + "join iu.iamAbilOrganizs iao " + "join iao.iamAutorServs ias  "
	    + "where iu.nmUserid = :nmUserid  "
	    + "and ias.nmServizioWeb = :servizioWeb and iu.tipoUser = 'AUTOMA' ";

    private static final String LOG_ACCESSI_PARAM_QUERY = "select paramApplic.nmParamApplic, valoreParamApplic.dsValoreParamApplic "
	    + " from AplValoreParamApplic valoreParamApplic join valoreParamApplic.aplParamApplic paramApplic "
	    + " WHERE paramApplic.flAppartApplic = '1' AND valoreParamApplic.tiAppart = 'APPLIC' "
	    + " AND paramApplic.tiParamApplic = :nmFunction ";

    @Inject
    EntityManager entityManager;

    @Override
    public boolean checkUserEnabledOnService(String nmUserid) {
	Query q = entityManager.createQuery(AUTH_QUERY);
	q.setParameter("nmUserid", nmUserid);
	q.setParameter("servizioWeb", SERVICE_NAME);
	return !q.getResultList().isEmpty();
    }

    @Override
    public HashMap<String, String> loadIdpLoggerParams(String nmFunction) {
	TypedQuery<Object[]> q = entityManager.createQuery(LOG_ACCESSI_PARAM_QUERY, Object[].class);
	q.setParameter("nmFunction", nmFunction);
	List<Object[]> list = q.getResultList();
	return (HashMap<String, String>) list.stream()
		.collect(Collectors.toMap(a -> (String) a[0], a -> (String) a[1]));
    }

}
