package com.reto.autentia.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.reto.autentia.model.Curso;

@Mapper
public interface CursoRepository {

    @Select("SELECT * FROM cursos ORDER BY titulo")
    @Results({
		@Result(id=true, property="id", column = "id"),
		@Result(property="profesor", column = "profesor_id", 
		one=@One(select="com.reto.autentia.repository.ProfesorRepository.getProfesorById")),
		@Result(property="nivel", column = "nivel_id",
		one=@One(select= "com.reto.autentia.repository.NivelRepository.getNivelById"))
	})
    public List<Curso> getAllCursos();
    
    
    @Select("SELECT * FROM cursos WHERE activo = 1 ORDER BY titulo LIMIT #{min}, 3")
    @Results({
		@Result(id=true, property="id", column = "id"),
		@Result(property="profesor", column = "profesor_id", 
		one=@One(select="com.reto.autentia.repository.ProfesorRepository.getProfesorById")),
		@Result(property="nivel", column = "nivel_id",
		one=@One(select= "com.reto.autentia.repository.NivelRepository.getNivelById"))
	})
    public List<Curso> getCursosActivos(int pagina);
    
    @Select("SELECT count(*) FROM cursos WHERE activo = 1")
    public int getCountCursosActivos();
    
    
    @Select("SELECT * FROM cursos WHERE id = #{id}")
    @Results({
		@Result(id=true, property="id", column = "id")
	})
    public Curso getCursoById(long id);
    
    
    @Insert("INSERT INTO cursos (titulo, profesor_id, nivel_id, numHoras, temario, activo) "
    		+ "VALUES (#{titulo}, #{profesor.id}, #{nivel.id}, #{numHoras}, #{temario}, #{activo})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void altaCurso(Curso curso);
    
    
}
