package com.reto.autentia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.autentia.model.Profesor;
import com.reto.autentia.repository.ProfesorRepository;

@Service
public class ProfesorService {

	@Autowired
	ProfesorRepository profesorRepository;

	public List<Profesor> getProfesores() {

		return profesorRepository.getAllProfesores();
	}

	public Optional<Profesor> getProfesor(String id) {
		return profesorRepository.getProfesorById(id);
	}

}
