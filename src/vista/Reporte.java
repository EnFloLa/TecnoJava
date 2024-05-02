package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mantenimientos.GestionTipo;
import mantenimientos.GestionUsuario;
import model.Tipo;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Reporte extends JInternalFrame {

	private JPanel contentPane;
	private JTextArea txtS;
	private JComboBox cboTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte frame = new Reporte();
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
	public Reporte() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Usuarios");
		setFrameIcon(new ImageIcon(Reporte.class.getResource("/img/ICONITOGOD.png")));
		setBounds(100, 100, 450, 307);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListadoDeUsuarios = new JLabel("Reporte de Usuarios");
		lblListadoDeUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblListadoDeUsuarios.setBounds(24, 11, 194, 26);
		contentPane.add(lblListadoDeUsuarios);

		JButton btnReporte = new JButton("Reporte");
		btnReporte.setForeground(Color.WHITE);
		btnReporte.setBackground(Color.BLACK);
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listado();
			}
		});
		btnReporte.setBounds(172, 236, 89, 23);
		contentPane.add(btnReporte);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 414, 141);
		contentPane.add(scrollPane);

		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 44, 46, 14);
		contentPane.add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setBounds(66, 41, 157, 20);
		contentPane.add(cboTipo);

		llenaCombo();
	}
	
	void listado() {
		int tipo = leerTipo();
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Usuario>lista = gu.listadoxtipo(tipo);
		if(lista == null) {
			JOptionPane.showMessageDialog(this, "Lista vacia");
			return;
		}
		
		txtS.setText("Codigo\tNombre\tApellido\n");
		for(Usuario u : lista) {
			txtS.append(u.getCodigo() + "\t" + u.getNombre() + "\t" + u.getApellido() + "\n");
		}
	}
	 int leerTipo() {
		 if (cboTipo.getSelectedIndex() == 0) {
				alerta("Seleccione un tipo de categoria");
				return -1; 
			}
		 return cboTipo.getSelectedIndex();
	 }
	
	void llenaCombo() {
		GestionTipo gt = new GestionTipo();
		ArrayList<Tipo> lista = gt.listado();
		cboTipo.addItem("Seleccionar");
		for(Tipo t : lista) {
			cboTipo.addItem(t.getDescripcion());
		}
	}
	
	void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.INFORMATION_MESSAGE);
	}

}
