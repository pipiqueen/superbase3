package com.utn.vista;

public interface LibroDAO extends AutoCloseable {

	public void aniadirLibro();

	public void borrarLibro();

	public void verLibro();

	public void prestarLibro();

	public void devolverLibro();

	public void retirarLibro();

	public void modificarLibro();

}
