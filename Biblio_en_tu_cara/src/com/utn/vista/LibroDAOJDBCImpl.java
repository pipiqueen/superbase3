package com.utn.vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LibroDAOJDBCImpl implements LibroDAO {

	@Override
	public void close() {

	}

	@Override
	public int aniadirLibro(Libro libro) {
		Connection con = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");

			stmt2 = con.prepareStatement("SELECT * FROM index_biblio_final_1 WHERE nombre = ?");

			String temp = libro.getNombre();
			stmt2.setString(1, temp);
			rs = stmt2.executeQuery();

			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "Libro ya ingresado");

				throw new RuntimeException();
			} else {

				stmt = con.prepareStatement(
						"INSERT INTO index_biblio_final_1(Nombre, Autor, Editorial, Ubiacion, retirado) VALUES (?, ?, ?, ?, ?) ");

				stmt.setString(1, libro.getNombre());
				stmt.setString(2, libro.getAutor());

				stmt.setString(3, libro.getEditorial());
				stmt.setString(4, libro.getUbicacion());
				stmt.setInt(5, libro.getRetirado());
				

				JOptionPane.showMessageDialog(null, "Libro Ingresado con Exito.");
				return stmt.executeUpdate();

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			try {

				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int borrarLibro(Libro libro) {
		
		Connection con = null;

		PreparedStatement stmt2 = null;

		ResultSet rs = null;

		PreparedStatement stmt = null;
		try {
			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");

			stmt2 = con.prepareStatement("SELECT * FROM index_biblio_final_1 WHERE nombre = ?");

			String temp = libro.getNombre();
			stmt2.setString(1, temp);
			rs = stmt2.executeQuery();

			if (rs.next()) {

				stmt = con.prepareStatement("DELETE FROM index_biblio_final_1 WHERE nombre = ?");
				JOptionPane.showConfirmDialog(null, "Esta accion no se puede deshacer, seguro deseas proseguir?");
				stmt.setString(1, libro.getNombre());
				JOptionPane.showMessageDialog(null, "Libro eliminado"
						);
				
			} else {
				JOptionPane.showMessageDialog(null, "El libro que ha ingresado no existe");

			}
			
			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void verLibro(Libro libro) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");
			stmt = con.prepareStatement("SELECT * FROM index_biblio_final_1 WHERE nombre = ?");
			String temp = libro.getNombre();
			stmt.setString(1, temp);
			rs = stmt.executeQuery();

			rs.next();

			libro.setNombre(rs.getString("Nombre"));

			libro.setAutor(rs.getString("Autor"));

			libro.setEditorial(rs.getString("Editorial"));

			libro.setUbicacion(rs.getString("ubiacion"));

			libro.setRetirado(rs.getInt("retirado"));

			libro.setPrestadoA(rs.getString("prestado_a"));

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR, REVISE EL LIBRO, O CONTACTESE CON ADMIN 8) ");
			throw new RuntimeException(e);

		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}

		}

	}

	@Override
	public int prestarLibro(Libro libro) {

		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");
			stmt = con.prepareStatement("SELECT * FROM index_biblio_final_1 WHERE nombre = ?");
			String temp = libro.getNombre();
			stmt.setString(1, temp);
			rs = stmt.executeQuery();
			
			
			
			if(rs.next()) {
				if(rs.getString("Prestado_a").equals("no")) {
					stmt2 = con.prepareStatement("UPDATE index_biblio_final_1 SET Prestado_a = ? WHERE Nombre = ?");
					stmt2.setString(1, libro.getPrestadoA());
					stmt2.setString(2, libro.getNombre());
					return stmt2.executeUpdate();
				}else {
					JOptionPane.showMessageDialog(null, "Error, el libro ya esta prestado");
				} else {
					JOptionPane.showMessageDialog(null, "Error el libro no esta ingresado");
				}
				
				
				 
				catch (SQLException e) {
						throw new RuntimeException(e);
					} finally {
						try {
							stmt.close();
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					
					
					
				}
			}
		}
		

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
