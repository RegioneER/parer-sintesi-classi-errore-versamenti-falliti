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
 * The persistent class for the ORG_STRUT database table.
 */
@Entity
@Table(name = "ORG_STRUT")
public class OrgStrut implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idStrut;
    private String nmStrut;
    private OrgEnte orgEnte;
    private String flCessato;

    public OrgStrut() {/* Hibernate */
    }

    @Id
    @Column(name = "ID_STRUT")
    public Long getIdStrut() {
	return this.idStrut;
    }

    @Column(name = "NM_STRUT")
    public String getNmStrut() {
	return this.nmStrut;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ENTE")

    public OrgEnte getOrgEnte() {
	return this.orgEnte;
    }

    @Column(name = "FL_CESSATO", columnDefinition = "char")
    public String getFlCessato() {
	return this.flCessato;
    }

    public void setFlCessato(String flCessato) {
	this.flCessato = flCessato;
    }
}
