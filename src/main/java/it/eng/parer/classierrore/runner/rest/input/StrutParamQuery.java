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
