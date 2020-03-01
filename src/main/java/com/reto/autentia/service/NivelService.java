package com.reto.autentia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.autentia.model.Nivel;
import com.reto.autentia.repository.NivelRepository;

@Service
public class NivelService {

	
	@Autowired
	NivelRepository nivelRepository;
	
	
	public List<Nivel> getNiveles(){
		return nivelRepository.getAllNiveles();
	}
	
	public Optional<Nivel> getNivelById(String id) {
		return nivelRepository.getNivelById(id);
	}
}
