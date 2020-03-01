package com.reto.autentia.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.reto.autentia.model.Profesor;

@Mapper
public interface ProfesorRepository {
	
	@Select("SELECT * FROM profesor ORDER BY apellidos")
	public List<Profesor> getAllProfesores();
	
	@Select("SELECT * FROM profesor WHERE id = #{id}")
	@Results({
		@Result(id=true, property="id", column = "id")
	})
	public Optional<Profesor> getProfesorById(String id);

}
