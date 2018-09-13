package com.utn.vista;

public interface LibroDAO extends AutoCloseable {

	public int aniadirLibro(Libro libro);

	public int borrarLibro(Libro libro);

	public void verLibro(Libro libro);

	public int prestarLibro(Libro libro);

	public void devolverLibro();

	public void retirarLibro();

	public void modificarLibro();

}
