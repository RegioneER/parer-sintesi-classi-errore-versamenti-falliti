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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@JsonInclude(Include.NON_NULL)
public class ClasseErroreDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clErrLast;
    private int totale;
    @Schema(type = SchemaType.STRING, required = true, format = "dd-MM-yyyy hh:mm:ss", implementation = LocalDateTime.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime dtLastSesErr;

    public ClasseErroreDto() {
	super();
    }

    public ClasseErroreDto(String clErrLast, int totale, LocalDateTime dtLastSesErr) {
	super();
	this.clErrLast = clErrLast;
	this.totale = totale;
	this.dtLastSesErr = dtLastSesErr;
    }

    public String getClErrLast() {
	return clErrLast;
    }

    public int getTotale() {
	return totale;
    }

    public LocalDateTime getDtLastSesErr() {
	return dtLastSesErr;
    }

    // Serializer personalizzato
    public static class CustomLocalDateTimeSerializer extends LocalDateTimeSerializer {
	private static final long serialVersionUID = -2778229130488410075L;

	public CustomLocalDateTimeSerializer() {
	    super(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
    }

}
