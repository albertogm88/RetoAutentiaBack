package com.reto.autentia.model;

public class Nivel {
	
	private int id;
	
	private String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Nivel(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Nivel() {
		super();
	}

	public Nivel(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	
}
