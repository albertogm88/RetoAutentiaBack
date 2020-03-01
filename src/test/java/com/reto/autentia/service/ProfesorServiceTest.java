package com.reto.autentia.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.reto.autentia.model.Profesor;
import com.reto.autentia.repository.ProfesorRepository;
import com.reto.autentia.service.ProfesorService;

@RunWith(MockitoJUnitRunner.class)
public class ProfesorServiceTest {
	
	@InjectMocks
	ProfesorService profesorService;
	
	@Mock
	ProfesorRepository profesorRepository;
	
	private static final Profesor profesorMock1 = new Profesor(1, "Pepe", "Pérez");
	private static final Profesor profesorMock2 = new Profesor(2, "Ramón", "Muñoz");
	
	private static final List<Profesor> listaProfesoresMock = new ArrayList<Profesor>();
	
	private static final List<Profesor> listaProfesoresMockNull = new ArrayList<Profesor>();
	

	@BeforeEach
	public void setUp() throws Exception{
		listaProfesoresMock.add(profesorMock1);
		listaProfesoresMock.add(profesorMock2);
		
	}

	@Test
	public void testGetProfesores() {
		when(profesorRepository.getAllProfesores()).thenReturn(listaProfesoresMock);
		List<Profesor> profesores = profesorService.getProfesores();
		assertNotNull(profesores);
	}
	
	@Test
	public void testEmptyGetProfesores() {
		when(profesorRepository.getAllProfesores()).thenReturn(listaProfesoresMockNull);
		List<Profesor> profesores = profesorService.getProfesores();
		assertTrue(profesores.isEmpty());
	}

	@Test
	public void testGetProfesor() {
		when(profesorRepository.getProfesorById("1")).thenReturn(Optional.of(profesorMock1));
		Optional<Profesor> profesor = profesorService.getProfesor("1");
		assertEquals(profesorMock1, profesor.get());
	}
	
	@Test
	public void testEmptyGetNivelById() {
		when(profesorRepository.getProfesorById("1")).thenReturn(Optional.ofNullable(null));
		Optional<Profesor> profesor = profesorService.getProfesor("1");
		assertEquals(profesor, Optional.empty());
	}

}
