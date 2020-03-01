package com.reto.autentia.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.reto.autentia.dto.CursoForm;
import com.reto.autentia.model.Curso;
import com.reto.autentia.model.Nivel;
import com.reto.autentia.model.Profesor;
import com.reto.autentia.service.CursoService;

@RunWith(MockitoJUnitRunner.class)
public class CursoControllerTest {
	
	@InjectMocks
	CursoController cursoController;
	
	@Mock
	CursoService cursoService;
	
	CursoForm cursoForm;
	
	private static final Profesor profesorMock1 = new Profesor(1, "Pepe", "Pérez");
	private static final Profesor profesorMock2 = new Profesor(2, "Ramón", "Muñoz");
	
	private static final Nivel nivelMock1 = new Nivel(1, "Básico");
	private static final Nivel nivelMock2 = new Nivel(2, "Intermedio");
	
	private static final Curso cursoMock1 = new Curso(1, "Curso 1", 25, null, 1, profesorMock1, nivelMock2);
	private static final Curso cursoMock2 = new Curso(2, "Curso 2", 50, null, 0, profesorMock2, nivelMock1);
	private static final Curso cursoMock3 = new Curso(3, "Curso 3", 150, null, 1, profesorMock2, nivelMock1);

	
	private static final List<Curso> listaCursosMock = new ArrayList<Curso>();
	
	private static final List<Curso> listaActivos = new ArrayList<Curso>();

	@Before
	public void setUp() throws Exception {
		listaCursosMock.add(cursoMock1);
		listaCursosMock.add(cursoMock2);
		listaCursosMock.add(cursoMock3);
		listaActivos.addAll(listaCursosMock.stream().filter(curso -> curso.getActivo() == 1).collect(Collectors.toList()));
		cursoForm = new CursoForm(true, "1", "Curso ejemplo", "1", "250", null);
	}

	@Test
	public void testGetCursos() {
		when(cursoService.getCursos()).thenReturn(listaCursosMock);
		assertEquals(cursoController.getCursos(0, 0, null).getEntity(), Response.ok(listaCursosMock).build().getEntity());
	}
	
	@Test
	public void testGetCursosActivos() {
		when(cursoService.getCursosActivos(1)).thenReturn(listaActivos);
		assertEquals(cursoController.getCursos(1, 1, null).getEntity(), Response.ok(listaActivos).build().getEntity());
	
	}
	
	@Test
	public void testGetCursosTodos() {
		when(cursoService.getCountCursosActivos()).thenReturn(3);
		assertEquals(Response.ok(3).build().getEntity(), cursoController.getCursos(0, 0, "S").getEntity());
	}

	@Test
	public void testAltaCurso() {
		assertEquals(Status.OK.getStatusCode(), cursoController.altaCurso(cursoForm).getStatus());
	}
	
	@Test
	public void testAltaCursoException() {
		doThrow(new NoSuchElementException()).when(cursoService).crearCurso(cursoForm);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), cursoController.altaCurso(cursoForm).getStatus());
	}

}
