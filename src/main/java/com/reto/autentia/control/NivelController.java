package com.reto.autentia.control;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.reto.autentia.model.Nivel;
import com.reto.autentia.service.NivelService;

@Path("/niveles")
public class NivelController {
	
	@Autowired
	NivelService nivelService;
	
	@GET
	@Produces("application/json")
	public Response getNiveles(){
		return Response.ok(nivelService.getNiveles()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getNivel(@PathParam("id") String id) {
		Optional<Nivel> optNivel = nivelService.getNivelById(id);
		if(optNivel.isPresent()) {
			return Response.ok(optNivel.get()).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
