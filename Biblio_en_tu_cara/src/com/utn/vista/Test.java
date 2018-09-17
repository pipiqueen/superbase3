package com.utn.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:\\Users\\Andrés\\Downloads\\img1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame = new JFrame();
		Image dimg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		frame.setContentPane(new JLabel(imageIcon));
		frame.setBounds(350, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Libro libro = new Libro();
		LibroDAOJDBCImpl impl = new LibroDAOJDBCImpl();

		JButton btnNewButton = new JButton("Buscar un Libro");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nombreLibro = JOptionPane.showInputDialog("Porfavor, ingrese el titulo del libro a buscar.");
				libro.setNombre(nombreLibro);
				impl.verLibro(libro);

				if (libro.getRetirado() == 1) {

					if (!libro.getPrestadoA().equals("no")) {

						JOptionPane.showMessageDialog(null,
								"El libro " + libro.getNombre() + " del autor " + libro.getAutor() + " de la editorial "
										+ libro.getEditorial() + " esta prestado a " + libro.getPrestadoA());

					} else {
						JOptionPane.showMessageDialog(null,
								"El libro " + libro.getNombre() + " del autor " + libro.getAutor() + " de la editorial "
										+ libro.getEditorial() + " se encuentra retirado, aunque dentro de la casa");
					}
				} else {

					JOptionPane.showMessageDialog(null,
							"El libro " + libro.getNombre() + " del autor " + libro.getAutor() + " de la editorial "
									+ libro.getEditorial() + " esta ubicado en " + libro.getUbicacion());

				}

			}
		});
		btnNewButton.setBounds(179, 479, 187, 94);
		frame.getContentPane().add(btnNewButton);

		JButton button = new JButton("Retirar un Libro");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				libro.setNombre(JOptionPane.showInputDialog(null, "Ingrese el libro a retirar"));

				impl.retirarLibro(libro);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(179, 605, 187, 94);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("Añadir un Nuevo Libro");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				libro.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del libro"));
				libro.setAutor(JOptionPane.showInputDialog(null, "Ingrese el nombre del autor"));

				libro.setEditorial(JOptionPane.showInputDialog(null, "Ingrse el nombre de la editorial"));
				libro.setUbicacion(JOptionPane.showInputDialog(null, "Ingrese la ubicacion. ej: A2"));
				libro.setRetirado((JOptionPane.showConfirmDialog(null, "Esta en la biblioteca?")));
				libro.setPrecio(Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el valor del libro, se aceptan .")));

				impl.aniadirLibro(libro);
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(394, 479, 187, 94);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("Eliminar un Libro");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				libro.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del libro a borrar"));

				impl.borrarLibro(libro);

			}

		});
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(394, 605, 187, 94);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("Modificar un Libro");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				libro.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del libro a modificar"));
				
				
				
				impl.modificarLibro(libro);
			}
		});
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_3.setBackground(new Color(192, 192, 192));
		button_3.setBounds(615, 479, 187, 94);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("Prestar un Libro");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				libro.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del libro a prestar"));
				libro.setPrestadoA(
						JOptionPane.showInputDialog(null, "Ingrese el nombre de la persona a quien se lo presta"));

				impl.prestarLibro(libro);

			}
		});

		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.setBounds(615, 605, 187, 94);
		frame.getContentPane().add(button_4);

		JButton button_5 = new JButton("Devolver un Libro");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				libro.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del libro a devolver"));

				impl.devolverLibro(libro);

			}
		});
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_5.setForeground(Color.BLACK);
		button_5.setBackground(Color.LIGHT_GRAY);
		button_5.setBounds(840, 605, 187, 94);
		frame.getContentPane().add(button_5);

		JLabel lblBienvenideALa = new JLabel("Bienvenide a la Interfaz de la Bilioteca Inteligente");
		lblBienvenideALa.setVerticalAlignment(SwingConstants.TOP);
		lblBienvenideALa.setForeground(new Color(255, 255, 255));
		lblBienvenideALa.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenideALa.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblBienvenideALa.setBounds(0, 136, 1184, 184);
		frame.getContentPane().add(lblBienvenideALa);

		JLabel lblPorFavorSeleccione = new JLabel("Seleccione una opci\u00F3n");
		lblPorFavorSeleccione.setForeground(Color.WHITE);
		lblPorFavorSeleccione.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorFavorSeleccione.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblPorFavorSeleccione.setBounds(0, 348, 1184, 106);
		frame.getContentPane().add(lblPorFavorSeleccione);
	}
}
