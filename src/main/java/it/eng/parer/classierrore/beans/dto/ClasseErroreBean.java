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

package it.eng.parer.classierrore.beans.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClasseErroreBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cdRegistroKeyUnitaDoc;
    private BigDecimal aaKeyUnitaDoc;
    private String cdKeyUnitaDoc;
    private String clErrLast;
    private LocalDateTime dtLastSesErr;

    public ClasseErroreBean() {
	super();
    }

    public ClasseErroreBean(String cdRegistroKeyUnitaDoc, BigDecimal aaKeyUnitaDoc,
	    String cdKeyUnitaDoc, String clErrLast, LocalDateTime dtLastSesErr) {
	super();
	this.cdRegistroKeyUnitaDoc = cdRegistroKeyUnitaDoc;
	this.aaKeyUnitaDoc = aaKeyUnitaDoc;
	this.cdKeyUnitaDoc = cdKeyUnitaDoc;
	this.clErrLast = clErrLast;
	this.dtLastSesErr = dtLastSesErr;
    }

    public String getCdRegistroKeyUnitaDoc() {
	return cdRegistroKeyUnitaDoc;
    }

    public BigDecimal getAaKeyUnitaDoc() {
	return aaKeyUnitaDoc;
    }

    public String getCdKeyUnitaDoc() {
	return cdKeyUnitaDoc;
    }

    public String getClErrLast() {
	return clErrLast;
    }

    public LocalDateTime getDtLastSesErr() {
	return dtLastSesErr;
    }

}
