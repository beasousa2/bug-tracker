package br.com.bugtracker.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	//LIST ALL USERS
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
	
	// SEARCH USER BY ID
	@GET
	@Path("/list/user/{id}")
	@Operation(summary = "User by Id",
    description = "Show a user searching by id")
	@APIResponse(responseCode = "200",
    description = "user id",
    content = {
            @Content(mediaType =  "application/json",
                    schema = @Schema(implementation = UsersDto.class))
    	}
	)
	public Response userById(@PathParam("id") Long id) {
		return Response
				.status(Response.Status.OK)
				.entity(service.userById(id))
				.build();
	}
	
	//INSERT USER
	@POST
	@Path("/insert")
	@Operation(summary = "Insert user",
	description = "Insert a new user on data base")
	@APIResponse(responseCode = "201",
	description = "new user",
	content = {
			@Content(mediaType = "application/json",
					 schema = @Schema(implementation = UsersDto.class)
			)
	})
	
	public Response insertUser(UsersDto user) {
		Set<ConstraintViolation<UsersDto>> errors =
				validator.validate(user);
		if(errors.isEmpty()) {
			service.insert(user);
		} else {
			List<String> errorsList = errors.stream()
			.map(ConstraintViolation::getMessage)
			.collect(Collectors.toList());
			throw new NotFoundException(errorsList.get(0));
		}
		
		return Response
				.status(Response.Status.CREATED)
				.build();		
	}
}
