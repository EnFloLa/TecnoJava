package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import mantenimientos.GestionTipo;
import mantenimientos.GestionUsuario;
import model.Tipo;
import model.Usuario;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class FrmRegistro extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JDateChooser txtFecha;
	private JTextField txtCodigo;
	private JComboBox cboOpcion;
	private JComboBox cboTipo;
	private JTable tblListado;
	DefaultTableModel modelo = new DefaultTableModel();
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistro frame = new FrmRegistro();
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
	public FrmRegistro() {
		setIconifiable(true);
		setClosable(true);
		setFrameIcon(new ImageIcon(FrmRegistro.class.getResource("/img/ICONITOGOD.png")));
		setTitle("Mantenimiento de Usuarios");
		setBounds(100, 100, 450, 492);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(28, 41, 46, 14);
		contentPane.add(lblCdigo);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(28, 70, 64, 14);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(28, 97, 64, 14);
		contentPane.add(lblApellido);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(28, 122, 64, 14);
		contentPane.add(lblUsuario);

		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(28, 152, 64, 14);
		contentPane.add(lblClave);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(28, 182, 64, 14);
		contentPane.add(lblFecha);

		txtNombre = new JTextField();
		txtNombre.setBounds(87, 67, 169, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(87, 94, 169, 20);
		contentPane.add(txtApellido);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(87, 122, 95, 20);
		contentPane.add(txtUsuario);

		txtClave = new JPasswordField();
		txtClave.setBounds(87, 149, 71, 20);
		contentPane.add(txtClave);

		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setForeground(Color.WHITE);
		btnProcesar.setBackground(Color.BLACK);
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion = cboOpcion.getSelectedIndex();
				switch(opcion) {
				case 0:
					registrarDatos();
				break;
				case 1:
					actualizarUsuario();
				break;
				case 2:
					eliminarUsuario();
				break;
				case 3:
					buscarUsuario();
				break;
				default:
					listado();
				}
			}
		});
		btnProcesar.setBounds(302, 78, 89, 23);
		contentPane.add(btnProcesar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setBackground(Color.BLACK);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.requestFocus();
				txtCodigo.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtUsuario.setText("");
				txtClave.setText("");
				txtFecha.setDateFormatString("");
				cboTipo.setSelectedIndex(0);
				modelo.setRowCount(0);
				
			}
		});
		btnLimpiar.setBounds(302, 122, 89, 23);
		contentPane.add(btnLimpiar);

		txtFecha = new JDateChooser();
		txtFecha.setDateFormatString("dd-MM-yyyy");
		txtFecha.setBounds(87, 176, 95, 20);
		contentPane.add(txtFecha);
		
		txtCodigo = new JTextField();
		txtCodigo.setText("Autogenerado");
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(87, 38, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		cboOpcion = new JComboBox();
		cboOpcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activarCampos();
			}
		});
		cboOpcion.setModel(new DefaultComboBoxModel(new String[] {"Registrar", "Actualizar", "Eliminar", "Consultar", "Listar"}));
		cboOpcion.setBounds(302, 37, 110, 22);
		contentPane.add(cboOpcion);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(28, 207, 46, 14);
		contentPane.add(lblTipo);
		
		cboTipo = new JComboBox();
		cboTipo.setBounds(87, 203, 157, 22);
		contentPane.add(cboTipo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 251, 380, 162);
		contentPane.add(scrollPane);
		
		tblListado = new JTable();
		scrollPane.setViewportView(tblListado);
		tblListado.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Usuario");
		modelo.addColumn("Clave");
		modelo.addColumn("Fecha");
		modelo.addColumn("Tipo");
		llenaCombo();
	}
	
	void listado() {
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Usuario> lista = gu.listado();
		if(lista == null) {
			alerta("Lista vacia");
			return;
		} 
		
		try{
	        Object[] fila = new Object[modelo.getColumnCount()];
	        
	        for (int i = 0;i<lista.size();i++) {
	            fila[0] = lista.get(i).getCodigo();
	            fila[1] = lista.get(i).getNombre();
	            fila[2] = lista.get(i).getApellido();
	            fila[3] = lista.get(i).getUsuario();
	            fila[4] = lista.get(i).getClave();
	            fila[5] = lista.get(i).getFnacim();
	            fila[6] = lista.get(i).getTipo();
	            modelo.addRow(fila);
	         }

	        tblListado.setModel(modelo); 

	       }
	       catch (Exception e) {
	          alerta("Ha ocurrido un error!" + e.getMessage());
	       }  
		
	}
	
	void llenaCombo() {
		GestionTipo gt = new GestionTipo();
		ArrayList<Tipo> lista = gt.listado();
		cboTipo.addItem("Seleccionar");
		for(Tipo t : lista) {
			cboTipo.addItem(t.getDescripcion());
		}
	}
	
	void buscarUsuario() {
		int codigo = leerCodigo();
		GestionUsuario gu = new GestionUsuario();
		Usuario u = gu.buscar(codigo);
		if(u == null) {
			alerta("Codigo no existe");
		} else {
			txtNombre.setText(u.getNombre());
			txtApellido.setText(u.getApellido());
			txtUsuario.setText(u.getUsuario());
			txtClave.setText(u.getClave());
			cboTipo.setSelectedIndex(u.getTipo());
		}
	}
	
	void actualizarUsuario() {
		int codigo = leerCodigo();
		String nombre = leerNombre();
		String apellido = leerApellido();
		String clave = leerClave();
		int tipo = leerTipo();
		
		Usuario u = new Usuario();
		u.setCodigo(codigo);
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setClave(clave);
		u.setTipo(tipo);
		
		GestionUsuario gu = new GestionUsuario();
		int ok = gu.actualizar(u);
		if (ok == 0) {
			alerta("Error al actualizar\nCodigo NO EXISTE!!");
		} else {
			alerta("Usuario actualizado con exito");
		}
	}
	
	void eliminarUsuario() {
		int codigo = leerCodigo();
		GestionUsuario gu = new GestionUsuario();
		int ok = gu.eliminar(codigo);
		if (ok == 0) {
			alerta("Error al eliminar\nCodigo NO EXISTE!!");
		} else {
			alerta("Usuario eliminado con exito");
		}
	}
	
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	void activarCampos() {
		int opcion = cboOpcion.getSelectedIndex();
		switch(opcion) {
		case 0:
			txtCodigo.setEnabled(false);
			txtCodigo.setText("Autogenerado");
			txtNombre.setEditable(true);
			txtNombre.requestFocus();
			txtApellido.setEnabled(true);
			txtClave.setEnabled(true);
			break;
		case 1:
			txtCodigo.setEnabled(true);
			txtCodigo.setText("");
			txtCodigo.requestFocus();
			txtNombre.requestFocus();
			txtApellido.setEnabled(true);
			txtUsuario.setEnabled(false);
			txtClave.setEnabled(true);
			txtFecha.setEnabled(false);
			break;	
		case 2:
			txtCodigo.setEnabled(true);
			txtCodigo.setText("");
			txtCodigo.requestFocus();
			txtNombre.setEditable(false);
			txtApellido.setEnabled(false);
			txtClave.setEnabled(false);
			break;	
		 case 3:
			txtCodigo.setEnabled(true); 
			txtCodigo.setText("");
			txtCodigo.requestFocus();
			txtNombre.setEditable(false);
			txtApellido.setEditable(false);
			txtUsuario.setEnabled(false);
			txtClave.setEnabled(false);
			txtFecha.setEnabled(false);
		}
	}
	
	void registrarDatos() {
		String nombre, apellido, usuario, clave, fnacim;
		int tipo;
		nombre = leerNombre();
		apellido = leerApellido();
		usuario = leerUsuario();
		clave = leerClave();
		fnacim = leerFecha();
		tipo = leerTipo();
		
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setUsuario(usuario);
		u.setClave(clave);
		u.setFnacim(fnacim);
		u.setTipo(tipo);

		GestionUsuario gu = new GestionUsuario();
		int ok = gu.registrar(u);
		if(ok == 0) {
			alerta("Error al registrar...!");
		} else {
			alerta("Usuario" + u.getNombre()+"registrado!!!");
		}
	}

	private String leerFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(txtFecha.getDate());
	}
	
	private int leerTipo() {
		if (cboTipo.getSelectedIndex() == 0) {
			alerta("Seleccione un tipo de categoria");
			return -1; 
		}
		return cboTipo.getSelectedIndex();
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

	private String leerApellido() {
		if(txtApellido.getText().isEmpty()) {
			alerta("Ingrese el apellido.....");
		}
		return txtApellido.getText();
	}

	private String leerNombre() {
		if(txtNombre.getText().isEmpty()) {
			alerta("Ingrese el nombre.....");
		}
		return txtNombre.getText();
	}
	
	void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.INFORMATION_MESSAGE);
	}
}
