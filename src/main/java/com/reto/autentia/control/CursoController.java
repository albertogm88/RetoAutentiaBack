package com.reto.autentia.control;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.reto.autentia.dto.CursoForm;
import com.reto.autentia.service.CursoService;

@Path("/cursos")
public class CursoController {

	@Autowired
	CursoService cursoService;

	@GET
	@Produces("application/json")
	public Response getCursos(@QueryParam("activos") int activos, @QueryParam("pag") int pagina, @QueryParam("todos") String todos) {
		if(null != todos && todos.equals("S")){
			return Response.ok(cursoService.getCountCursosActivos()).build();
		}
		if(activos==1) {
			return Response.ok(cursoService.getCursosActivos(pagina)).build();
		}
		return Response.ok(cursoService.getCursos()).build();
	}
	
	@POST
	public Response altaCurso(CursoForm curso) {
		
		try {
			cursoService.crearCurso(curso);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}


}
