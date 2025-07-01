package it.eng.parer.classierrore.beans.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CodiceErroreBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cdRegistroKeyUnitaDoc;
    private BigDecimal aaKeyUnitaDoc;
    private String cdKeyUnitaDoc;
    private String cdErrPrinc;
    private LocalDateTime dtLastSesErr;

    public CodiceErroreBean() {
	super();
    }

    public CodiceErroreBean(String cdRegistroKeyUnitaDoc, BigDecimal aaKeyUnitaDoc,
	    String cdKeyUnitaDoc, String cdErrPrinc, LocalDateTime dtLastSesErr) {
	super();
	this.cdRegistroKeyUnitaDoc = cdRegistroKeyUnitaDoc;
	this.aaKeyUnitaDoc = aaKeyUnitaDoc;
	this.cdKeyUnitaDoc = cdKeyUnitaDoc;
	this.cdErrPrinc = cdErrPrinc;
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

    public String getCdErrPrinc() {
	return cdErrPrinc;
    }

    public LocalDateTime getDtLastSesErr() {
	return dtLastSesErr;
    }

}
