package it.eng.parer.classierrore.beans.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.eng.parer.classierrore.beans.dto.ClasseErroreDto;

@JsonInclude(Include.NON_NULL)
public class ClassiErroreResponse {

    // multi request
    private String path;
    private Integer totale;
    private String struttura;
    private List<ClasseErroreDto> classiErrore;

    public ClassiErroreResponse() {
	super();
    }

    public ClassiErroreResponse(String struttura, List<ClasseErroreDto> classiErrore,
	    Integer totaleClassiErrore, String uri) {
	super();
	this.struttura = struttura;
	this.classiErrore = classiErrore;
	this.totale = totaleClassiErrore;
	this.path = uri;
    }

    public String getPath() {
	return path;
    }

    public Integer getTotale() {
	return totale;
    }

    public List<ClasseErroreDto> getClassiErrore() {
	return classiErrore;
    }

    public String getStruttura() {
	return struttura;
    }

}
