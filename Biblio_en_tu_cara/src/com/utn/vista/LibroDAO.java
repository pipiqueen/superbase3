package com.utn.vista;

public interface LibroDAO extends AutoCloseable {

	public int aniadirLibro(Libro libro);

	public int borrarLibro(Libro libro);

	public void verLibro(Libro libro);

	public int prestarLibro(Libro libro);

	public int devolverLibro(Libro libro);

	public int retirarLibro(Libro libro);

	public int modificarLibro(Libro libro);
	
	

}
