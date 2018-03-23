package com.example.jaxrs.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

/**
 * Allows Cross Origin Resource Sharing
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext request,
	                   ContainerResponseContext response) throws IOException {
		if (!StringUtils.equals(request.getMethod(), "OPTIONS")) {
			response.getHeaders().add("Access-Control-Allow-Origin", "*");
		}
	}
}