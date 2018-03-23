package com.example;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A sample MicroService that retrieves {@link Employee} information.
 */
@Path("/employee")
@RequestScoped
public class EmployeeService {

	/**
	 * Logger for this class
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	@Inject
	EmployeeRepository employeeRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response listAll() {
		LOG.info("Retrieving all employees ...");
		List<Employee> employees = employeeRepository.queryAll();
		return Response.ok().entity(employees).build();
	}

}
