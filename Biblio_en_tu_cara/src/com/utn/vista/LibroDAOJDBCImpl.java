package com.utn.vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LibroDAOJDBCImpl implements LibroDAO {

	

	@Override
	public void close() {
		
		
		
	}

	@Override
	public void aniadirLibro() {
		Connection con = null;
		
		PreparedStatement stmt = null;
		 
		try{
			con = DriverManager.getConnection("jdbc, arg1, arg2) 
		}
		
	}

	@Override
	public void borrarLibro() {
		
		
	}

	@Override
	public void verLibro() {
		
		
	}

	@Override
	public void prestarLibro() {
		
		
	}

	@Override
	public void devolverLibro() {
		
		
	}

	@Override
	public void retirarLibro() {
		
		
	}

	@Override
	public void modificarLibro() {
		
		
	}
}
