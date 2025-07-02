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

package it.eng.parer.classierrore.runner.rest;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.smallrye.common.annotation.Blocking;
import io.vertx.core.http.HttpServerRequest;
import it.eng.parer.classierrore.beans.IClassiErroreService;
import it.eng.parer.classierrore.beans.exceptions.AppGenericRuntimeException;
import it.eng.parer.classierrore.beans.model.ClassiErroreResponse;
import static it.eng.parer.classierrore.beans.utils.Costants.RESOURCE_CLASSIERRORE;
import static it.eng.parer.classierrore.beans.utils.Costants.URL_API_BASE;
import it.eng.parer.classierrore.runner.rest.input.StrutParamQuery;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Tag(name = "Lista classi errore per versamenti falliti", description = "Api che restituisce la lista delle classi di errore per versamenti falliti")
@SecurityScheme(securitySchemeName = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer")
@RequestScoped
@Path(URL_API_BASE)
public class ClassiErroreEndpoint {

    /* constants */
    private static final String ETAG = "sintesi-classi-errore-versamenti-falliti-v1.0";

    @ConfigProperty(name = "quarkus.uuid")
    String instanceUUID;

    /* interfaces */
    private final IClassiErroreService classiErroreService;
    private final SecurityContext securityCtx;

    @Inject
    public ClassiErroreEndpoint(IClassiErroreService classiErroreService,
	    SecurityContext securityCtx) {
	this.classiErroreService = classiErroreService;
	this.securityCtx = securityCtx;
    }

    @Operation(summary = "Lista classi errore versamenti falliti per struttura", description = "Lista classi errore versamenti falliti per struttura tramite query string")
    @SecurityRequirement(name = "bearerAuth")
    @APIResponses(value = {
	    @APIResponse(responseCode = "200", description = "Lista classi errore recuperata con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClassiErroreResponse.class))),
	    @APIResponse(responseCode = "400", description = "Richiesta non valida"),
	    @APIResponse(responseCode = "401", description = "Autenticazione fallita"),
	    @APIResponse(responseCode = "403", description = "Non autorizzato ad accedere al servizio"),
	    @APIResponse(responseCode = "405", description = "Invocazione non corretta"),
	    @APIResponse(responseCode = "500", description = "Errore generico (richiesta non valida secondo specifiche)", content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = AppGenericRuntimeException.class))) })
    @GET
    @Path(RESOURCE_CLASSIERRORE)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Response listclassierrore(@BeanParam @Valid StrutParamQuery org,
	    @Context HttpServerRequest request) {
	// do something .....
	ClassiErroreResponse results = getClassiErroreResponseFromDto(org, request);
	//
	return Response.ok(results)
		.lastModified(
			Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
		.tag(new EntityTag(ETAG)).build();
    }

    private ClassiErroreResponse getClassiErroreResponseFromDto(StrutParamQuery org,
	    HttpServerRequest request) {
	String nmAmbiente = org.nmAmbiente;
	String nmEnte = org.nmEnte;
	String nmStrut = org.nmStrut;
	String uri = URLDecoder.decode(request.uri(), Charset.defaultCharset());
	return classiErroreService.listClassiErrorePerVarsFalliti(
		securityCtx.getUserPrincipal().getName(), nmAmbiente, nmEnte, nmStrut, uri);
    }
}
