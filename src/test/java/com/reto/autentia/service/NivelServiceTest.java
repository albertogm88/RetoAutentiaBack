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

import com.reto.autentia.model.Nivel;
import com.reto.autentia.repository.NivelRepository;
import com.reto.autentia.service.NivelService;

@RunWith(MockitoJUnitRunner.class)
public class NivelServiceTest {
	
	@InjectMocks
	NivelService nivelService;
	
	@Mock
	NivelRepository nivelRepository;
	
	//Creación de objetos para pruebas
	private static final Nivel nivelMock1 = new Nivel(1, "Básico");
	private static final Nivel nivelMock2 = new Nivel(2, "Intermedio");
	
	private static final List<Nivel> listaNivelesMock = new ArrayList<Nivel>();
	
	private static final List<Nivel> listaNivelesMockNull = new ArrayList<Nivel>();
	
	@BeforeEach
	public void setUp() throws Exception{
		listaNivelesMock.add(nivelMock1);
		listaNivelesMock.add(nivelMock2);
	}

	@Test
	public void testGetNiveles() {
		when(nivelRepository.getAllNiveles()).thenReturn(listaNivelesMock);
		List<Nivel> niveles = nivelService.getNiveles();
		assertNotNull(niveles);
	}
	
	@Test
	public void testEmptyGetNiveles() {
		when(nivelRepository.getAllNiveles()).thenReturn(listaNivelesMockNull);
		List<Nivel> niveles = nivelService.getNiveles();
		assertTrue(niveles.isEmpty());
	}

	@Test
	public void testGetNivelById() {
		when(nivelRepository.getNivelById("1")).thenReturn(Optional.of(nivelMock1));
		Optional<Nivel> nivel = nivelService.getNivelById("1");
		assertEquals(nivelMock1, nivel.get());
	}
	
	@Test
	public void testEmptyGetNivelById() {
		when(nivelRepository.getNivelById("1")).thenReturn(Optional.ofNullable(null));
		Optional<Nivel> nivel = nivelService.getNivelById("1");
		assertEquals(nivel, Optional.empty());
	}

}
