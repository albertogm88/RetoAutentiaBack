package com.reto.autentia.dto;

public class CursoForm {
	
	private boolean activo;
	
	private String profesor;
	
	private String titulo;
	
	private String nivel;
	
	private String horas;
	
	private String temario;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String getTemario() {
		return temario;
	}

	public void setTemario(String temario) {
		this.temario = temario;
	}

	public CursoForm(boolean activo, String profesor, String titulo, String nivel, String horas, String temario) {
		super();
		this.activo = activo;
		this.profesor = profesor;
		this.titulo = titulo;
		this.nivel = nivel;
		this.horas = horas;
		this.temario = temario;
	}
	
	

}
