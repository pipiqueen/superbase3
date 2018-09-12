package com.utn.vista;

public class Libro {

	private String nombre;
	private String autor;
	private String editorial;
	private String ubicacion;
	private int retirado;
	private String prestadoA;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getRetirado() {
		return retirado;
	}

	public void setRetirado(int retirado) {
		this.retirado = retirado;
	}

	public String getPrestadoA() {
		return prestadoA;
	}

	public void setPrestadoA(String prestadoA) {
		this.prestadoA = prestadoA;
	}

}
