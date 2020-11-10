package br.com.bugtracker.rest;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.bugtracker.dto.UsersDto;
import br.com.bugtracker.service.UsersService;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersRest {
	
	@Inject
		UsersService service;
	
	@Inject
		Validator validator;
	
	@GET
	@Path("/list")
	@Operation(summary = "List users",
    description = "List of users with name, e-mail, the company position and role")
	@APIResponse(responseCode = "200",
    description = "user",
    content = {
            @Content(mediaType =  "application/json",
                    schema = @Schema(implementation = UsersDto.class))
    	}
	)
	public Response listAllUsers() {
		return Response
				.status(Response.Status.OK)
				.entity(service.listAllUsers())
				.build();
	}
}
