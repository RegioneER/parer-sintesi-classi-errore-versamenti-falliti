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

/**
 *
 */
package it.eng.parer.classierrore.runner.rest.input;

import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.QueryParam;

/* custom validator */
public class StrutParamQuery {

    @Parameter(name = "amb", description = "codice ambiente", allowEmptyValue = false, example = "PARER", schema = @Schema(type = SchemaType.STRING), required = true, in = ParameterIn.QUERY)
    @QueryParam("amb")
    @NotBlank(message = "Ambiente obbligatorio")
    public String nmAmbiente;

    @Parameter(name = "ente", description = "codice ente", allowEmptyValue = false, example = "ACER", schema = @Schema(type = SchemaType.STRING), required = true, in = ParameterIn.QUERY)
    @QueryParam("ente")
    @NotBlank(message = "Ente obbligatorio")
    public String nmEnte;

    @Parameter(name = "strut", description = "codice struttura", allowEmptyValue = false, example = "acepbo", schema = @Schema(type = SchemaType.STRING), required = true, in = ParameterIn.QUERY)
    @QueryParam("strut")
    @NotBlank(message = "Struttura obbligatoria")
    public String nmStrut;

}
