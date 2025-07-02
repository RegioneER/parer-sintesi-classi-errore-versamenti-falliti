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

package it.eng.parer.classierrore.jpa;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The persistent class for the IAM_AUTOR_SERV database table.
 */
@Entity
@Table(name = "IAM_AUTOR_SERV")
public class IamAutorServ implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idAutorServ;

    private String nmServizioWeb;

    private IamAbilOrganiz iamAbilOrganiz;

    public IamAutorServ() {/* Hibernate */
    }

    @Id
    @Column(name = "ID_AUTOR_SERV")
    public Long getIdAutorServ() {
	return this.idAutorServ;
    }

    public void setIdAutorServ(Long idAutorServ) {
	this.idAutorServ = idAutorServ;
    }

    @Column(name = "NM_SERVIZIO_WEB")
    public String getNmServizioWeb() {
	return this.nmServizioWeb;
    }

    public void setNmServizioWeb(String nmServizioWeb) {
	this.nmServizioWeb = nmServizioWeb;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ABIL_ORGANIZ")
    public IamAbilOrganiz getIamAbilOrganiz() {
	return this.iamAbilOrganiz;
    }

    public void setIamAbilOrganiz(IamAbilOrganiz iamAbilOrganiz) {
	this.iamAbilOrganiz = iamAbilOrganiz;
    }

}
