package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionProducto;
import mantenimientos.GestionVenta;
import model.Cabecera;
import model.Detalle;

import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Toolkit;

public class FrmBoleta extends JInternalFrame {

	private JPanel contentPane;
	
	public static JTextField txtCodCliente;
	public static JTextField txtNomCompletoCliente;
	
	private JTextArea txtSalida;
	private JTextField txtFechaActual;
	private JButton btnNuevo;
	private JButton btnFinalizar;
	private JTextField txtTotal;
	private JTextField txtNumBoleta;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblProducto;
	public static JTextField txtCodProducto;
	public static JTextField txtDesProducto;
	public static JTextField txtPreProducto;
	public static JTextField txtStockProducto;
	private JTextField txtCantidadAComprar;
	private JButton btnAgregar;
	private JLabel lblCantidad;
	private JButton btnConsultarProducto;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBoleta frame = new FrmBoleta();
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
	public FrmBoleta() {
		setFrameIcon(new ImageIcon(FrmBoleta.class.getResource("/img/ICONITOGOD.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Boleta");
		setBounds(100, 100, 620, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSalida = new JTextArea();
		txtSalida.setFont(new Font("Consolas", Font.PLAIN, 20));
		txtSalida.setBounds(22, 249, 561, 114);
		contentPane.add(txtSalida);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			limpiaGUI();
			}
		});
		btnNuevo.setBounds(23, 387, 89, 23);
		contentPane.add(btnNuevo);
		
		btnFinalizar = new JButton("Finalizar Compra");		
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			finalizarCompra();
			}
		});
		btnFinalizar.setBounds(137, 387, 144, 23);
		contentPane.add(btnFinalizar);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(494, 374, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(429, 377, 46, 14);
		contentPane.add(lblTotal);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(319, 25, 261, 105);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFecha_1 = new JLabel("Fecha:");
		lblFecha_1.setBounds(10, 56, 48, 14);
		panel.add(lblFecha_1);
		
		txtFechaActual = new JTextField();
		txtFechaActual.setEditable(false);
		txtFechaActual.setText("A\u00F1o/Mes/D\u00EDa");
		txtFechaActual.setBounds(68, 53, 97, 20);
		panel.add(txtFechaActual);
		txtFechaActual.setColumns(10);
		
		JLabel lblNm = new JLabel("N\u00FAm");
		lblNm.setBounds(10, 14, 33, 14);
		panel.add(lblNm);
		
		txtNumBoleta = new JTextField();
		txtNumBoleta.setEditable(false);
		txtNumBoleta.setText("B0000");
		txtNumBoleta.setColumns(10);
		txtNumBoleta.setBounds(68, 11, 162, 20);
		panel.add(txtNumBoleta);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 34, 261, 96);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 21, 70, 25);
		panel_1.add(lblCliente);
		
		txtCodCliente = new JTextField();
		txtCodCliente.setEditable(false);
		txtCodCliente.setBounds(67, 23, 97, 20);
		panel_1.add(txtCodCliente);
		txtCodCliente.setColumns(10);
		
		txtNomCompletoCliente = new JTextField();
		txtNomCompletoCliente.setEditable(false);
		txtNomCompletoCliente.setBounds(67, 54, 173, 20);
		panel_1.add(txtNomCompletoCliente);
		txtNomCompletoCliente.setColumns(10);
		
		JButton btnConsultarCliente = new JButton("");
		btnConsultarCliente.setBounds(184, 9, 37, 37);
		panel_1.add(btnConsultarCliente);
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgCliente d = new DlgCliente();
				d.setVisible(true);
			}
		});
		btnConsultarCliente.setBorder(null);
		btnConsultarCliente.setBorderPainted(false);
		btnConsultarCliente.setContentAreaFilled(false);
		btnConsultarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		btnConsultarCliente.setIcon(new ImageIcon(FrmBoleta.class.getResource("/img/busca.png")));
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(20, 141, 563, 96);
		contentPane.add(panel_2);
		
		lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(10, 21, 70, 25);
		panel_2.add(lblProducto);
		
		txtCodProducto = new JTextField();
		txtCodProducto.setEditable(false);
		txtCodProducto.setText("P0001");
		txtCodProducto.setColumns(10);
		txtCodProducto.setBounds(87, 23, 86, 20);
		panel_2.add(txtCodProducto);
		
		txtCantidadAComprar = new JTextField();
		txtCantidadAComprar.setColumns(10);
		txtCantidadAComprar.setBounds(87, 54, 86, 20);
		panel_2.add(txtCantidadAComprar);
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregar.setRolloverIcon(new ImageIcon(FrmBoleta.class.getResource("/img/cartph.png")));
		btnAgregar.setIcon(new ImageIcon(FrmBoleta.class.getResource("/img/cartph.png")));
		btnAgregar.setContentAreaFilled(false);
		btnAgregar.setBorderPainted(false);
		btnAgregar.setBorder(null);
		btnAgregar.setBounds(183, 48, 37, 37);
		panel_2.add(btnAgregar);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 57, 70, 14);
		panel_2.add(lblCantidad);
		
		btnConsultarProducto = new JButton("");
		btnConsultarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgProducto d = new DlgProducto();
				d.setVisible(true);
			}
		});
		btnConsultarProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		btnConsultarProducto.setIcon(new ImageIcon(FrmBoleta.class.getResource("/img/busca.png")));
		btnConsultarProducto.setContentAreaFilled(false);
		btnConsultarProducto.setBorderPainted(false);
		btnConsultarProducto.setBorder(null);
		btnConsultarProducto.setBounds(183, 9, 37, 37);
		panel_2.add(btnConsultarProducto);
		
		txtDesProducto = new JTextField();
		txtDesProducto.setEditable(false);
		txtDesProducto.setColumns(10);
		txtDesProducto.setBounds(235, 23, 143, 20);
		panel_2.add(txtDesProducto);
		
		txtPreProducto = new JTextField();
		txtPreProducto.setEditable(false);
		txtPreProducto.setColumns(10);
		txtPreProducto.setBounds(387, 23, 70, 20);
		panel_2.add(txtPreProducto);
		
		txtStockProducto = new JTextField();
		txtStockProducto.setEditable(false);
		txtStockProducto.setColumns(10);
		txtStockProducto.setBounds(471, 23, 70, 20);
		panel_2.add(txtStockProducto);
		
		JLabel lblAgregarProducto = new JLabel("Agregar producto ");
		lblAgregarProducto.setBounds(230, 57, 148, 14);
		panel_2.add(lblAgregarProducto);
		
		
	}
	
	Cabecera c = new Cabecera();
	
	void finalizarCompra() {
		System.out.println("Cantidad de elementos : "+ carro.size());
		for(Detalle d : carro) {
			System.out.println(d.getIdprod()+" "+d.getCantidad()+" "+d.getPreciovta());
		}
		String numbol = obtenerNumBoleta();
		String fchbol = obtenerFecha();
		int codcliente = leerCodCliente();
		int codvendedor = obtenerCodVendedor();
		double totbol = total;
		c.setNumBoleta(numbol);
		c.setFchBoleta(fchbol);
		c.setCodCliente(codcliente);
		c.setCodVendedor(codvendedor);
		c.setTotalBoleta(totbol);
		
		GestionVenta gv = new GestionVenta();
		int ok =  gv.realizarVenta(c, carro);
		
		if(ok == 0) {
			alerta("Error en la venta...");
		}
		else {
			aviso2("Venta exitosa......");
			imprimirPDF();
			limpiaGUI();
		}
	}
	
	void imprimirPDF() {
		String nomarchivo = "reportes/"+ c.getNumBoleta() +".pdf";
		try {
			Document doc = new Document();
			FileOutputStream fos = new FileOutputStream(nomarchivo);
			PdfWriter pdfw = PdfWriter.getInstance(doc, fos);
			
			doc.open();
			
			doc.add(new Paragraph("Boleta de Venta"));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Num de Boleta : " + c.getNumBoleta()));
			doc.add(new Paragraph("Fecha : " + c.getFchBoleta()));
			doc.add(new Paragraph("Monto pagado : " + String.format("S/%5.2f",c.getTotalBoleta())));
			
			PdfPTable tabla = new PdfPTable(5);
			for(Detalle d : carro) {
				tabla.addCell(d.getIdprod());
				tabla.addCell(new GestionProducto().buscar(d.getIdprod()).getDescripcion());
				tabla.addCell(d.getCantidad()+"");
				tabla.addCell(String.format("S/%5.2f",d.getPreciovta()));
				tabla.addCell(String.format("S/%5.2f",d.getImporte()));
			}
			doc.add(tabla);
			doc.add(new Paragraph("-------------------------------------------------------------------------------"));
			doc.close();
			
			Desktop.getDesktop().open(new File(nomarchivo));
		}catch(Exception e){
			alerta("No se puede crear el archivo PDF");
			e.printStackTrace();
		}	
	}
	
	void imprimir(String msg) {
		txtSalida.append(msg + "\n");
	}
	
	void limpiaGUI() {
		total = 0;
		carro.clear();
		txtSalida.setText("");
		txtTotal.setText("");
		txtCantidadAComprar.setText("");
		txtCantidadAComprar.requestFocus();
	}
	
	double total = 0;
	ArrayList<Detalle> carro =new ArrayList<Detalle>();
	
	void agregarProducto() {
		String codpro = leerCodProd();
		String nompro = leerNomProd();
		double precio = leerPrecio();
		int stock = leerStock();
		int cantidad = leerCantidad();
		
		if(cantidad == -1) {
			return;
		}
		
		if(cantidad > stock) {
			alerta("Stock no disponible.....\nSolo se cuenta con "+ stock + " unidades");
			return;
		}
		
		double importe = cantidad * precio;
		total += importe;
		
		Detalle d = new Detalle();
		d.setIdprod(codpro);
		d.setCantidad(cantidad);
		d.setPreciovta(precio); 
		d.setImporte(importe);
		
		carro.add(d);
		aviso2("Producto agregado....");
		
		String linea = "%s %20s %3d S/%.2f S/%5.2f";
		imprimir(String.format(linea, codpro,nompro,cantidad,precio,importe));
		
		txtTotal.setText(String.format("S/%5.2f", total));
	}
	
	void aviso2(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso...",
			JOptionPane.INFORMATION_MESSAGE,
			new ImageIcon(FrmBoleta.class.getResource("/img/cartph.png")));
	}

	private int obtenerCodVendedor() {
		return Logueo.u.getCodigo();
	}

	private int leerCodCliente() {
		if(txtCodCliente.getText().isEmpty()) {
			alerta("Ingrese el cliente.....");
			return -1;
		}
		return Integer.parseInt(txtCodCliente.getText());
	}

	private String obtenerNumBoleta() {		
		GestionVenta gv = new GestionVenta();
		return gv.generaNumBoleta();
	}

	private String obtenerFecha() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	private int leerCantidad() {
		// TODO Auto-generated method stub
		if(txtCantidadAComprar.getText().isEmpty()) {
			alerta("Debe ingresar una cantidad...");
			return -1;
		}
		return Integer.parseInt(txtCantidadAComprar.getText());
	}
	
	void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Error...",JOptionPane.ERROR_MESSAGE);
	}
	
	void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso...",JOptionPane.ERROR_MESSAGE);
	}

	private int leerStock() {
		// TODO Auto-generated method stub
		return Integer.parseInt(txtStockProducto.getText());
	}

	private double leerPrecio() {
		// TODO Auto-generated method stub
		return Double.parseDouble(txtPreProducto.getText());
	}

	private String leerNomProd() {
		// TODO Auto-generated method stub
		return txtDesProducto.getText();
	}

	private String leerCodProd() {
		// TODO Auto-generated method stub
		return txtCodProducto.getText();
	}
}