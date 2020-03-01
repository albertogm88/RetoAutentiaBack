package com.reto.autentia.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.reto.autentia.model.Nivel;

@Mapper
public interface NivelRepository {
	
	@Select("SELECT * FROM nivel ORDER BY id")
	public List<Nivel> getAllNiveles();
	
	@Select("SELECT * FROM nivel WHERE id = #{id}")
	@Results({
		@Result(id=true, property="id", column = "id")
	})
	public Optional<Nivel> getNivelById(String id);

}
