package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionProducto;
import mantenimientos.GestionUsuario;
import model.CatProd;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class List_Prod extends JInternalFrame {

	private JPanel contentPane;
	private JTable tblListado;
	
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List_Prod frame = new List_Prod();
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
	public List_Prod() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Reporte Categorias");
		setFrameIcon(new ImageIcon(List_Prod.class.getResource("/img/ICONITOGOD.png")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total de Productos");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(27, 26, 198, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBackground(Color.BLACK);
		btnListar.setForeground(Color.WHITE);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListar.setBounds(163, 227, 89, 23);
		contentPane.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 65, 397, 152);
		contentPane.add(scrollPane);
		
		tblListado = new JTable();
		modelo.addColumn("Categoria");
		modelo.addColumn("Total Productos");
		scrollPane.setViewportView(tblListado);
	}
	
	void listado() {
		int idtipo = leerIdTipo();
		GestionProducto gp = new GestionProducto();
		ArrayList<CatProd> lista = gp.listadocatxprod(idtipo);
		if(lista == null) {
			alerta("Lista vacia");
			return;
		} 
		
		try{
	        Object[] fila = new Object[modelo.getColumnCount()];
	        
	        for (int i = 0;i<lista.size();i++) {
	            fila[0] = lista.get(i).getDescripcion();
	            fila[1] = lista.get(i).getIdtipo();      
	            modelo.addRow(fila);
	         }

	        tblListado.setModel(modelo); 

	       }
	       catch (Exception e) {
	          alerta("Ha ocurrido un error!" + e.getMessage());
	       }  
		
	}

	private int leerIdTipo() {
		return 0;
	}
	
	void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.INFORMATION_MESSAGE);
	}
	
}
