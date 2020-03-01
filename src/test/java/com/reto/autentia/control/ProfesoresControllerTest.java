package com.reto.autentia.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.reto.autentia.model.Profesor;
import com.reto.autentia.service.ProfesorService;

@RunWith(MockitoJUnitRunner.class)
public class ProfesoresControllerTest {

	@InjectMocks
	ProfesoresController profesorController;
	
	@Mock
	ProfesorService profesorService;
	
	private static final Profesor profesorMock1 = new Profesor(1, "Pepe", "Pérez");
	private static final Profesor profesorMock2 = new Profesor(2, "Ramón", "Muñoz");
	
	private static final List<Profesor> listaProfesoresMock = new ArrayList<Profesor>();

	@Before
	public void setUp() throws Exception {
		listaProfesoresMock.add(profesorMock1);
		listaProfesoresMock.add(profesorMock2);
	}

	@Test
	public void testGetProfesores() {
		assertEquals(Status.OK.getStatusCode(), profesorController.getProfesores().getStatus());
	}

	@Test
	public void testGetProfeso() {
		when(profesorService.getProfesor("3")).thenReturn(Optional.of(profesorMock1));
		assertEquals(Status.OK.getStatusCode(), profesorController.getProfesor("3").getStatus());
	}
	
	@Test
	public void testGetProfesorNotFound() {
		when(profesorService.getProfesor("4")).thenReturn(Optional.empty());
		assertEquals(Status.NOT_FOUND.getStatusCode(), profesorController.getProfesor("4").getStatus());
	}

}
