package com.reto.autentia.control;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.reto.autentia.model.Profesor;
import com.reto.autentia.service.ProfesorService;


@Path("/profesores")
public class ProfesoresController {
	
	@Autowired
	ProfesorService profesorService;
	
	@GET
	@Produces("application/json")
	public Response getProfesores(){
		return Response.ok(profesorService.getProfesores()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
 	public Response getProfesor(@PathParam("id") String id) {
		Optional<Profesor> optProfesor = profesorService.getProfesor(id);
		if(optProfesor.isPresent()) {
			return Response.ok(optProfesor.get()).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}
}
