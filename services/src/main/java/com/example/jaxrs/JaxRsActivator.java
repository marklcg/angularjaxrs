package com.example.jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class extending {@link Application} and annotated with @ApplicationPath is
 * the Java EE 7 "no XML" approach to activating JAX-RS.  This class can be generalized so it is not repeated in
 * every service.
 * <p>
 * Resources are served relative to the servlet path specified in the
 * {@link ApplicationPath} annotation.
 */
@ApplicationPath("/api")
public class JaxRsActivator extends Application {

	/**
	 * Logger for this class
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JaxRsActivator.class);

	/**
	 * Log a message so we know we activated.
	 */
	public JaxRsActivator() {
		super();
		LOG.info("Activating JAX-RS under the URL /api");
	}

}
