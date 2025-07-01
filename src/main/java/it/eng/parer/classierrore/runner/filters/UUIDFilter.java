/**
 *
 */
package it.eng.parer.classierrore.runner.filters;

import java.util.Optional;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import it.eng.parer.classierrore.beans.utils.UUIDMdcLogUtil;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.container.ContainerRequestContext;

/*
 * Filtro che intercetta le chiamate di tipo GET e inietta sull'MCD un UUID
 */
public class UUIDFilter {

    @ServerRequestFilter
    public Optional<RestResponse<Void>> getFilter(ContainerRequestContext ctx) {

	if (ctx.getMethod().equals(HttpMethod.GET)) {
	    UUIDMdcLogUtil.genUuid();
	}

	return Optional.empty();
    }
}
