package com.utn.vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LibroDAOJDBCImpl implements LibroDAO {
	private Connection con = null;
	private PreparedStatement stmt2 = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	@Override
	public void close() {

	}

	@Override
	public int aniadirLibro(Libro libro) {

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
						"INSERT INTO index_biblio_final_1(Nombre, Autor, Editorial, Ubiacion, retirado,precio) VALUES (?, ?, ?, ?, ?,?) ");

				stmt.setString(1, libro.getNombre());
				stmt.setString(2, libro.getAutor());

				stmt.setString(3, libro.getEditorial());
				stmt.setString(4, libro.getUbicacion());
				stmt.setInt(5, libro.getRetirado());
				stmt.setFloat(6, libro.getPrecio());
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
				JOptionPane.showMessageDialog(null, "Libro eliminado");

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

			libro.setPrecio(rs.getInt("precio"));

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

		try {
			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");
			stmt = con.prepareStatement("SELECT * FROM index_biblio_final_1 WHERE nombre = ?");
			String temp = libro.getNombre();
			stmt.setString(1, temp);
			rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("Prestado_a").equals("no")) {
					stmt2 = con.prepareStatement(
							"UPDATE index_biblio_final_1 SET Prestado_a = ?, retirado = ?, ubiacion = ? WHERE Nombre = ?");

					stmt2.setString(1, libro.getPrestadoA());
					stmt2.setInt(2, 1);
					stmt2.setString(3, "no");
					stmt2.setString(4, libro.getNombre());
					JOptionPane.showMessageDialog(null, "Se ha prestado el libro a " + libro.getPrestadoA());

					return stmt2.executeUpdate();

				} else {
					JOptionPane.showMessageDialog(null, "Error, el libro ya esta prestado");
					JOptionPane.showMessageDialog(null, "El libro esta prestado a: " + libro.getPrestadoA());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error el libro " + libro.getNombre() + " no esta ingresado");
			}
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
		return 0;
	}

	@Override
	public int devolverLibro(Libro libro) {

		try {
			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");

			stmt = con.prepareStatement("SELECT * from index_biblio_final_1 WHERE nombre = ?");
			String temp = libro.getNombre();
			stmt.setString(1, temp);
			rs = stmt.executeQuery();

			if (rs.next()) {
				if (!rs.getString("Prestado_a").equals("no")) {

					stmt2 = con.prepareStatement("UPDATE index_biblio_final_1 SET Prestado_a = ? WHERE Nombre = ?");
					stmt2.setString(1, "no");
					stmt2.setString(2, libro.getNombre());
					JOptionPane.showMessageDialog(null, "Se ha devuelto el libro " + libro.getNombre());

					return stmt2.executeUpdate();
				} else {
					JOptionPane.showMessageDialog(null, "El libro no se encuentra prestado!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, el libro no se encuentra en la biblioteca");

			}
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
		return 0;

	}

	@Override
	public int retirarLibro(Libro libro) {
		try {
			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");

			stmt = con.prepareStatement("SELECT * from index_biblio_final_1 WHERE nombre = ?");
			String temp = libro.getNombre();
			stmt.setString(1, temp);
			rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt("retirado") == 0) {

					stmt2 = con.prepareStatement("UPDATE index_biblio_final_1 SET retirado = 1 WHERE nombre = ?");

					stmt2.setString(1, libro.getNombre());
					JOptionPane.showMessageDialog(null, "Se ha retirado el libro con exito");
					stmt2.execute();

				} else {
					JOptionPane.showMessageDialog(null, "El libro ya se encuentra retirado");
				}
			} else {
				JOptionPane.showMessageDialog(null, "El libro no se encuentra ingresado");
			}
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
		return 0;

	}

	@Override
	public int modificarLibro(Libro libro) {

		try {

			String url = "jdbc:mysql://localhost:3306/biblio2";
			con = DriverManager.getConnection(url, "root", "");
			stmt = con.prepareStatement("SELECT * FROM index_biblio_final_1 WHERE nombre = ?");
			String temp = libro.getNombre();
			stmt.setString(1, temp);
			rs = stmt.executeQuery();

			if (rs.next()) {

				stmt2 = con.prepareStatement(
						"UPDATE index_biblio_final_1 SET  Autor = ?, Editorial = ?, ubiacion = ?, retirado = ? WHERE nombre = ?");

				stmt2.setString(1, JOptionPane.showInputDialog(null, "Ingrese el nombre del autor"));
				stmt2.setString(2, JOptionPane.showInputDialog(null, "Ingrse el nombre de la editorial"));
				stmt2.setString(3, JOptionPane.showInputDialog(null, "Ingrese la ubicacion. ej: A2"));
				stmt2.setInt(4, JOptionPane.showConfirmDialog(null, "Esta en la biblioteca?"));

				stmt2.setString(5, temp);
				JOptionPane.showMessageDialog(null, "Libro Ingresado con Exito.");

				return stmt2.executeUpdate();

			} else {
				JOptionPane.showMessageDialog(null, "Error, debe ingresar el libro primero");
			}
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
		return 0;

	}

}
