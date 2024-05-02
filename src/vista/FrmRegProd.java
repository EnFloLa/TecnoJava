package vista;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionCategoria;
import mantenimientos.GestionProducto;
import mantenimientos.GestionUsuario;
import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Color;

public class FrmRegProd extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtProducto;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JComboBox cboTipo;
	DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegProd frame = new FrmRegProd();
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
	public FrmRegProd() {
		setIconifiable(true);
		setClosable(true);
		getContentPane().setBackground(Color.ORANGE);
		setFrameIcon(new ImageIcon(FrmRegProd.class.getResource("/img/ICONITOGOD.png")));
		setTitle("CRUD Productos");
		setBounds(100, 100, 506, 212);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("C\u00F3digo:");
		label.setBounds(30, 48, 75, 14);
		getContentPane().add(label);

		txtCodigo = new JTextField();
		txtCodigo.setText("");
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(88, 42, 86, 20);
		getContentPane().add(txtCodigo);

		JLabel label_1 = new JLabel("Producto:");
		label_1.setBounds(30, 73, 75, 14);
		getContentPane().add(label_1);

		txtProducto = new JTextField();
		txtProducto.setText("");
		txtProducto.setColumns(10);
		txtProducto.setBounds(88, 70, 86, 20);
		getContentPane().add(txtProducto);

		JLabel label_2 = new JLabel("Tipo:");
		label_2.setBounds(30, 98, 53, 14);
		getContentPane().add(label_2);

		cboTipo = new JComboBox();
		cboTipo.setBounds(88, 94, 123, 20);
		getContentPane().add(cboTipo);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(30, 122, 60, 14);
		getContentPane().add(lblStock);

		txtCantidad = new JTextField();
		txtCantidad.setText("");
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(88, 119, 53, 20);
		getContentPane().add(txtCantidad);

		JLabel label_4 = new JLabel("Precio:");
		label_4.setBounds(178, 122, 46, 14);
		getContentPane().add(label_4);

		txtPrecio = new JTextField();
		txtPrecio.setText("");
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(220, 119, 60, 20);
		getContentPane().add(txtPrecio);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.requestFocus();
				txtCodigo.setText("");
				txtProducto.setText("");
				txtCantidad.setText("");
				txtPrecio.setText("");
				cboTipo.setSelectedIndex(0);
			}
		});
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.BLACK);
		btnNuevo.setBounds(328, 28, 116, 34);
		getContentPane().add(btnNuevo);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.BLACK);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresar();
			}
		});
		btnGuardar.setBounds(328, 78, 116, 34);
		getContentPane().add(btnGuardar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idprod, descripcion;
				int stock, idtipo;
				double precio;
				idprod = leerCodigo();
				descripcion = leerProducto();
				stock = leerCantidad();
				idtipo = leerTipo();
				precio = leerPrecio();
				
				Producto p = new Producto();
				p.setIdprod(idprod);
				p.setDescripcion(descripcion);
				p.setIdtipo(idtipo);
				p.setStock(stock);
				p.setPrecio(precio);
				
				GestionProducto gp = new GestionProducto();
				int ok = gp.editar(p);
				if (ok == 0) {
					alerta("Error al editar\nCodigo NO EXISTE!!");
				} else {
					alerta("Producto editado con exito");
				}
			}
		});
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.BLACK);
		btnEditar.setBounds(328, 123, 116, 34);
		getContentPane().add(btnEditar);

		JLabel lblMantenimientoDeProductos = new JLabel("Mantenimiento de Productos");
		lblMantenimientoDeProductos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMantenimientoDeProductos.setBounds(21, 13, 227, 20);
		getContentPane().add(lblMantenimientoDeProductos);
		modelo.addColumn("Código");
		modelo.addColumn("Producto");
		modelo.addColumn("Tipo");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio");
		
		llenaCombo();
	}
	
	void llenaCombo() {
		GestionCategoria gc = new GestionCategoria();
		ArrayList<Categoria> lista = gc.listado();
		
		cboTipo.addItem("Seleccionar");
		for(Categoria c : lista) {
			cboTipo.addItem(c.getDescripcion());
		}
	}
	
	
	void ingresar() {
				String idprod, descripcion;
				int stock, idtipo;
				double precio;
				idprod = leerCodigo();
				descripcion = leerProducto();
				stock = leerCantidad();
				idtipo = leerTipo();
				precio = leerPrecio();
				
				Producto p = new Producto();
				p.setIdprod(idprod);
				p.setDescripcion(descripcion);
				p.setIdtipo(idtipo);
				p.setStock(stock);
				p.setPrecio(precio);

				GestionProducto gu = new GestionProducto();
				int ok = gu.registrar(p);
				if(ok == 0) {
					alerta("Error al registrar producto...!");
				} else {
					alerta("Producto " + p.getDescripcion()+" registrado!!!");
				}
		
	}
	
	

	private String leerCodigo() {
		if (txtCodigo.getText().equals("")) {
			alerta("Ingrese Código");
			return null; 
		}
		if(!txtCodigo.getText().matches("[pP][0-9]{4}")) {
			alerta("Codigo No valido \n Debe tener la forma: P0000");
			return null;
		}
		return txtCodigo.getText();
	}

	String leerProducto() {
		if(txtProducto.getText().isEmpty()) {
			alerta("Ingrese el producto.....");
		}
		return txtProducto.getText();
	}
	
	int leerTipo() {
		if (cboTipo.getSelectedIndex() == 0) {
			alerta("Seleccione un tipo de categoria");
			return -1; 
		}
		return cboTipo.getSelectedIndex();
	}

	int leerCantidad() {
		if (txtCantidad.getText().equals("")) {
			alerta("Ingrese Cantidad");
			return -1; 
		} try { 
		return Integer.parseInt(txtCantidad.getText());
		} catch (NumberFormatException e) {
			alerta("Valor de cantidad no valido!!! \n Solo acepta numeros"+e.getMessage());
			return -1;
		}
	}

	double leerPrecio() {
		if (txtPrecio.getText().isEmpty()) {
			alerta("Ingrese Precio");
			return -1;
	}
		try { 
		return Double.parseDouble(txtPrecio.getText());
		} catch (NumberFormatException e) {
			alerta("Valor de precio no valido!!! \n Solo acepta numeros"+e.getMessage());
			return -1;
		}
	}
	
	void alerta(String msg) {
		JOptionPane.showMessageDialog(this,
				msg, "Error!!",JOptionPane.WARNING_MESSAGE);
	}
	
}
