package com.example.jaxrs.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * JAXRS Filter to handle OPTIONS requests globally.
 * Browsers will send OPTIONS requests and they must be handled.  It is best to handle them globally.
 */
@Provider
@PreMatching
public class OptionsFilter implements ContainerRequestFilter {

	/**
	 * Look for OPTIONS requests and abort the request with an OK response and the allowed methods and headers.
	 *
	 * @param requestContext
	 * 		the {@link ContainerRequestContext}
	 * @throws IOException
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getMethod().equals("OPTIONS")) {
			requestContext.abortWith(
					Response.ok()
							.header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE")
							.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, " +
									"Accept-Language, Authorization")
							.build());
		}
	}

}
