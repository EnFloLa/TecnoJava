package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.ChangeListener;

import hilo.HIloContador;
import mantenimientos.GestionUsuario;
import model.Usuario;

import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Logueo extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	public static Logueo frame;
	private JButton btnAceptar;
	public static JLabel lblTiempo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Logueo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Logueo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Logueo.class.getResource("/img/ICONITOGOD.png")));
		setTitle("TECNOSTORE - Acceso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 233);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(114, 36, 96, 20);
		contentPane.add(lblUsuario);

		JLabel lblClave = new JLabel("Contrase\u00F1a:");
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClave.setBounds(114, 83, 96, 20);
		contentPane.add(lblClave);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(205, 36, 103, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtClave = new JPasswordField();
		txtClave.setBounds(205, 80, 103, 20);
		contentPane.add(txtClave);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(Color.BLACK);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPrincipal();
			}
		});
		btnAceptar.setBounds(114, 116, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.BLACK);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(226, 116, 89, 23);
		contentPane.add(btnSalir);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Logueo.class.getResource("/img/new.png")));
		lblFondo.setBounds(0, 11, 127, 184);
		contentPane.add(lblFondo);

		lblTiempo = new JLabel("10s");
		lblTiempo.setBounds(270, 11, 46, 14);
		contentPane.add(lblTiempo);

		JLabel lblMensaje = new JLabel("Esta ventana se cerrar\u00E1 en ");
		lblMensaje.setHorizontalTextPosition(SwingConstants.LEFT);
		lblMensaje.setBounds(114, 11, 194, 14);
		contentPane.add(lblMensaje);
		
		setLocationRelativeTo(null);
		
		iniciaContador();
	} 
	
	public static Usuario u = new Usuario();
	
	void abrirPrincipal() {
		String usuario = leerUsuario();
		String clave = leerClave();
		GestionUsuario gu = new GestionUsuario();
		u = gu.validarAcceso(usuario, clave);
		if(u == null) {
			alerta("Usuario o clave incorrrecto");
		}else {
		alerta("Bienvenido al sistema: " + u.getNombre() + " " + u.getApellido());	
		FrmPreLoader v = new FrmPreLoader();
		v.setVisible(true);
		dispose();
		}
	}
	
	void iniciaContador() {
		HIloContador h = new HIloContador(this);
		h.start();
	}
	
	private String leerClave() {
		if(txtClave.getText().isEmpty()) {
			alerta("Ingrese la clave.....");
		}
		return txtClave.getText();
	}

	private String leerUsuario() {
		if(txtUsuario.getText().isEmpty()) {
			alerta("Ingrese el usuario.....");
		}
		return txtUsuario.getText();
	}
	
	void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.INFORMATION_MESSAGE);
	}

}
