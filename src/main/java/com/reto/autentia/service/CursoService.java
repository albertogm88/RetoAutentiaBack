package com.reto.autentia.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.autentia.dto.CursoForm;
import com.reto.autentia.model.Curso;
import com.reto.autentia.model.Nivel;
import com.reto.autentia.model.Profesor;
import com.reto.autentia.repository.CursoRepository;
import com.reto.autentia.repository.NivelRepository;
import com.reto.autentia.repository.ProfesorRepository;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;
    
    @Autowired
    ProfesorRepository profesorRepository;
    
    @Autowired
    NivelRepository nivelRepository;

    public List<Curso> getCursos(){
        return cursoRepository.getAllCursos();
    }
    
    public List<Curso> getCursosActivos(int pagina){
    	if(pagina != 0) {
    		pagina--;
    		pagina = pagina * 3;
    	}
    	return cursoRepository.getCursosActivos(pagina);
    }
    
    public void altaCurso(Curso curso) {
    	cursoRepository.altaCurso(curso);
    }
    
    public void crearCurso(CursoForm cursoForm) throws NoSuchElementException{
    	
    	Optional<Profesor> optProfesor = profesorRepository.getProfesorById(cursoForm.getProfesor());
    	if(optProfesor.isPresent()) {
    		Optional<Nivel> optNivel = nivelRepository.getNivelById(cursoForm.getNivel());
    		if(optNivel.isPresent()) {
    			Curso curso = new Curso(cursoForm.getTitulo(), Integer.valueOf(cursoForm.getHoras()), cursoForm.getTemario(), 
    					cursoForm.isActivo()?1:0, optProfesor.get(), optNivel.get());
    			altaCurso(curso);
    		}else {
    			throw new NoSuchElementException("El nivel elegido no existe");
    		}
    	}else {
    		throw new NoSuchElementException("El profesor elegido no existe");
    	}
    }
    
    public int getCountCursosActivos() {
    	return cursoRepository.getCountCursosActivos();
    }
}
