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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the VRS_UNITA_DOC_NON_VERS database table.
 */
@Entity
@Table(name = "VRS_UNITA_DOC_NON_VERS")

public class VrsUnitaDocNonVer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUnitaDocNonVers;

    private BigDecimal aaKeyUnitaDoc;

    private String cdErrPrinc;

    private String cdKeyUnitaDoc;

    private String cdRegistroKeyUnitaDoc;

    private String dsErrPrinc;

    private Date dtFirstSesErr;

    private LocalDateTime dtLastSesErr;

    private OrgStrut orgStrut;

    public VrsUnitaDocNonVer() {/* Hibernate */
    }

    @Id
    @Column(name = "ID_UNITA_DOC_NON_VERS")
    public Long getIdUnitaDocNonVers() {
	return this.idUnitaDocNonVers;
    }

    @Column(name = "AA_KEY_UNITA_DOC")
    public BigDecimal getAaKeyUnitaDoc() {
	return this.aaKeyUnitaDoc;
    }

    @Column(name = "CD_ERR_PRINC")
    public String getCdErrPrinc() {
	return this.cdErrPrinc;
    }

    @Column(name = "CD_KEY_UNITA_DOC")
    public String getCdKeyUnitaDoc() {
	return this.cdKeyUnitaDoc;
    }

    @Column(name = "CD_REGISTRO_KEY_UNITA_DOC")
    public String getCdRegistroKeyUnitaDoc() {
	return this.cdRegistroKeyUnitaDoc;
    }

    @Column(name = "DS_ERR_PRINC")
    public String getDsErrPrinc() {
	return this.dsErrPrinc;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIRST_SES_ERR")
    public Date getDtFirstSesErr() {
	return this.dtFirstSesErr;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_LAST_SES_ERR")
    public LocalDateTime getDtLastSesErr() {
	return this.dtLastSesErr;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STRUT")
    public OrgStrut getOrgStrut() {
	return this.orgStrut;
    }

}
