package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.HiloReloj;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane escritorio;
	public static JLabel lblReloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/img/ICONITOGOD.png")));
		setTitle("Bienvenido: " + Logueo.u.getNombre() + " " + Logueo.u.getApellido());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		mnSistema.setForeground(Color.WHITE);
		mnSistema.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/system.png")));
		mnSistema.setMnemonic('e');
		menuBar.add(mnSistema);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.setForeground(Color.WHITE);
		mntmCerrar.setBackground(Color.BLACK);
		mntmCerrar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/cerrar.png")));
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Está seguro de salir?")==0)
				dispose();
			}
		});
		mntmCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnSistema.add(mntmCerrar);
		
		JMenu mnMantenimiento = new JMenu("Mantenimineto");
		mnMantenimiento.setForeground(Color.WHITE);
		mnMantenimiento.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/manticon.png")));
		mnMantenimiento.setMnemonic('m');
		menuBar.add(mnMantenimiento);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRegistro r = new FrmRegistro();
				r.setVisible(true);
				escritorio.add(r);
			}
		});
		mntmClientes.setForeground(Color.WHITE);
		mntmClientes.setBackground(Color.BLACK);
		mntmClientes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/userimagexd.png")));
		mnMantenimiento.add(mntmClientes);
		
		JMenuItem mntmProductos = new JMenuItem("Productos");
		mntmProductos.setForeground(Color.WHITE);
		mntmProductos.setBackground(Color.BLACK);
		mntmProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRegProd v = new FrmRegProd();
				v.setVisible(true);
				escritorio.add(v);
			}
		});
		mntmProductos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/productos.png")));
		mnMantenimiento.add(mntmProductos);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/ojito.png")));
		mnReportes.setMnemonic('r');
		menuBar.add(mnReportes);
		
		JMenuItem mntmRepoUsuarios = new JMenuItem("Usuarios");
		mntmRepoUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reporte rp = new Reporte();
				rp.setVisible(true);
				escritorio.add(rp);
			}
		});
		mntmRepoUsuarios.setForeground(Color.WHITE);
		mntmRepoUsuarios.setBackground(Color.BLACK);
		mntmRepoUsuarios.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/userimagexd.png")));
		mnReportes.add(mntmRepoUsuarios);
		
		JMenuItem mntmCategorias = new JMenuItem("Categor\u00EDas");
		mntmCategorias.setBackground(Color.BLACK);
		mntmCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List_Prod lp = new List_Prod();
				lp.setVisible(true);
				escritorio.add(lp);
			}
		});
		mntmCategorias.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/8956812_categories_menu_ui_options_grid_icon.png")));
		mntmCategorias.setForeground(Color.WHITE);
		mnReportes.add(mntmCategorias);
		
		JMenu mnTransaccion = new JMenu("Transacción");
		mnTransaccion.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/4288566_banking_bill_card_credit_expenditure_icon.png")));
		mnTransaccion.setForeground(Color.WHITE);
		menuBar.add(mnTransaccion);
		
		JMenuItem mntmBoleta = new JMenuItem("Boleta");
		mntmBoleta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/7007504_payment_bill_invoice_receipt_business_icon.png")));
		mntmBoleta.setForeground(Color.WHITE);
		mntmBoleta.setBackground(Color.BLACK);
		mntmBoleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmBoleta v = new FrmBoleta();
				v.setVisible(true);
				escritorio.add(v);
			}
		});
		mnTransaccion.add(mntmBoleta);
		
		JMenuItem mntmReporteVenta = new JMenuItem("Reporte de Venta");
		mntmReporteVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgReporteVenta rptv = new DlgReporteVenta();
				rptv.setVisible(true);
			}
		});
		mntmReporteVenta.setBackground(Color.BLACK);
		mntmReporteVenta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/ventas.png")));
		mntmReporteVenta.setForeground(Color.WHITE);
		mnTransaccion.add(mntmReporteVenta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(Color.ORANGE);
		contentPane.add(escritorio, BorderLayout.CENTER);
		
		JInternalFrame reloj = new JInternalFrame("Hora");
		reloj.setClosable(true);
		reloj.setIconifiable(true);
		reloj.setBounds(58, 164, 131, 55);
		escritorio.add(reloj);
				
		lblReloj = new JLabel("00:00:00");
		lblReloj.setForeground(Color.BLACK);
		lblReloj.setBackground(Color.GRAY);
		reloj.getContentPane().add(lblReloj, BorderLayout.CENTER);
		reloj.setVisible(true);
		
		iniciaReloj();
		
		switch(Logueo.u.getTipo()) {
		case 2:
			mnMantenimiento.setVisible(false);
			mnTransaccion.setVisible(false);
			mntmRepoUsuarios.setEnabled(false);
		case 3:
			mnMantenimiento.setVisible(false);
		default:
			break;
		}
	}
	
	void iniciaReloj() {
		HiloReloj h = new HiloReloj();
		h.start();
	}
	
}
