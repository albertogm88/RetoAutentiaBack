package com.reto.autentia.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.reto.autentia.repository.CursoRepository;
import com.reto.autentia.repository.NivelRepository;
import com.reto.autentia.repository.ProfesorRepository;


@RunWith(MockitoJUnitRunner.class)
public class CursoServiceTest {
	
	@InjectMocks
	CursoService cursoService;
	
	@Mock
    CursoRepository cursoRepository;
    
	@Mock
    ProfesorRepository profesorRepository;
    
	@Mock
    NivelRepository nivelRepository;
	
	CursoForm cursoForm;
	
	private static final Profesor profesorMock1 = new Profesor(1, "Pepe", "Pérez");
	private static final Profesor profesorMock2 = new Profesor(2, "Ramón", "Muñoz");
	

	private static final Nivel nivelMock1 = new Nivel(1, "Básico");
	private static final Nivel nivelMock2 = new Nivel(2, "Intermedio");

	
	private static final Curso cursoMock1 = new Curso(1, "Curso 1", 25, null, 1, profesorMock1, nivelMock2);
	private static final Curso cursoMock2 = new Curso(2, "Curso 2", 50, null, 0, profesorMock2, nivelMock1);
	private static final Curso cursoMock3 = new Curso(3, "Curso 3", 150, null, 1, profesorMock2, nivelMock1);
	
	private static final List<Curso> listaActivos = new ArrayList<Curso>();
	
	private static final List<Curso> listaFiltrada = new ArrayList<Curso>();
	
	private static final List<Curso> listaCursosMock = new ArrayList<Curso>();


	@Before
	public void setUp() throws Exception {
		cursoForm = new CursoForm(true, "1", "Curso ejemplo", "1", "250", null);
		listaCursosMock.add(cursoMock1);
		listaCursosMock.add(cursoMock2);
		listaCursosMock.add(cursoMock3);
		listaFiltrada.addAll(listaCursosMock.stream().filter(curso -> curso.getActivo() == 1).collect(Collectors.toList()));
		listaActivos.add(cursoMock1);
		listaActivos.add(cursoMock3);
	}

	@Test
	public void testGetCursos() {
		when(cursoRepository.getAllCursos()).thenReturn(listaCursosMock);
		List<Curso> cursos = cursoService.getCursos();
		assertNotNull(cursos);
	}
	

	@Test
	public void testGetCursosActivosZero() {
		when(cursoRepository.getCursosActivos(0)).thenReturn(listaFiltrada);
		List<Curso> cursos = cursoService.getCursosActivos(0);
		assertEquals(1, cursos.get(0).getActivo());
	}
	
	@Test
	public void testGetCursosActivos() {
		when(cursoRepository.getCursosActivos(3)).thenReturn(listaFiltrada);
		List<Curso> cursos = cursoService.getCursosActivos(2);
		assertEquals(1, cursos.get(0).getActivo());
	}

	@Test
	public void testAltaCurso() {
		cursoService.altaCurso(cursoMock1);
	}

	@Test
	public void testCrearCursoActivo() {
		cursoForm.setActivo(true);
		when(profesorRepository.getProfesorById(cursoForm.getProfesor())).thenReturn(Optional.of(profesorMock1));
		when(nivelRepository.getNivelById(cursoForm.getNivel())).thenReturn(Optional.of(nivelMock1));
		cursoService.crearCurso(cursoForm);
	}
	
	@Test
	public void testCrearCursoInactivo() {
		cursoForm.setActivo(false);
		when(profesorRepository.getProfesorById(cursoForm.getProfesor())).thenReturn(Optional.of(profesorMock1));
		when(nivelRepository.getNivelById(cursoForm.getNivel())).thenReturn(Optional.of(nivelMock1));
		cursoService.crearCurso(cursoForm);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testCrearCursoNivelNoSuchElementException() {
		when(profesorRepository.getProfesorById(cursoForm.getProfesor())).thenReturn(Optional.of(profesorMock1));
		when(nivelRepository.getNivelById(cursoForm.getNivel())).thenReturn(Optional.empty());
		cursoService.crearCurso(cursoForm);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testCrearCursoProfesorNoSuchElementException() {
		when(profesorRepository.getProfesorById(cursoForm.getProfesor())).thenReturn(Optional.empty());
		cursoService.crearCurso(cursoForm);
	}

	@Test
	public void testGetCountCursosActivos() {
		when(cursoRepository.getCountCursosActivos()).thenReturn(2);
		int countCursosActivos = cursoService.getCountCursosActivos();
		assertEquals(2, countCursosActivos);
	}

}
