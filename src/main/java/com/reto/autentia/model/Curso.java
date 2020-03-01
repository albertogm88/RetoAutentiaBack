package com.reto.autentia.model;

public class Curso {

    private int id;

    private String titulo;

    private int numHoras;

    private String temario;

    private int activo;

    private Profesor profesor;

    private Nivel nivel;


    public Curso(String titulo, int numHoras, String temario, int activo, Profesor profesor, Nivel nivel) {
        this.titulo = titulo;
        this.numHoras = numHoras;
        this.temario = temario;
        this.activo = activo;
        this.profesor = profesor;
        this.nivel = nivel;
    }
    
    
    public Curso() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public String getTemario() {
        return temario;
    }

    public void setTemario(String temario) {
        this.temario = temario;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }


	public Curso(int id, String titulo, int numHoras, String temario, int activo, Profesor profesor, Nivel nivel) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.numHoras = numHoras;
		this.temario = temario;
		this.activo = activo;
		this.profesor = profesor;
		this.nivel = nivel;
	}
    
    

}
