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
