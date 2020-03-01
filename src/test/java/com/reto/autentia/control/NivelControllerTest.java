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

import com.reto.autentia.model.Nivel;
import com.reto.autentia.service.NivelService;


@RunWith(MockitoJUnitRunner.class)
public class NivelControllerTest {
	
	@InjectMocks
	NivelController nivelController;
	
	@Mock
	NivelService nivelService;
	
	private static final Nivel nivelMock1 = new Nivel(1, "Básico");
	private static final Nivel nivelMock2 = new Nivel(2, "Intermedio");
	
	private static final List<Nivel> listaNivelesMock = new ArrayList<Nivel>();

	@Before
	public void setUp() throws Exception {
		listaNivelesMock.add(nivelMock1);
		listaNivelesMock.add(nivelMock2);
	}

	@Test
	public void testGetNiveles() {
		assertEquals(Status.OK.getStatusCode(), nivelController.getNiveles().getStatus());
	}

	@Test
	public void testGetNivel() {
		when(nivelService.getNivelById("3")).thenReturn(Optional.of(nivelMock1));
		assertEquals(Status.OK.getStatusCode(), nivelController.getNivel("3").getStatus());
	}
	
	@Test
	public void testGetNivelNotFound() {
		when(nivelService.getNivelById("4")).thenReturn(Optional.empty());
		assertEquals(Status.NOT_FOUND.getStatusCode(), nivelController.getNivel("4").getStatus());
	}

}
