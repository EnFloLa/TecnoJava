package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionProducto;
import mantenimientos.GestionUsuario;
import model.Producto;
import model.Usuario;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFiltro;
	private JTable tblSalida;
	DefaultTableModel modelo = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) { //esto es para que no lo editen
			return false;
		}
	};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgProducto dialog = new DlgProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProducto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtFiltro = new JTextField();
			txtFiltro.setBounds(0, 0, 184, 20);
			contentPanel.add(txtFiltro);
			txtFiltro.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 42, 434, 175);
			contentPanel.add(scrollPane);
			{
				tblSalida = new JTable();
				tblSalida.setModel(modelo);
				modelo.addColumn("Codigo");         
				modelo.addColumn("Producto");
				modelo.addColumn("Stock");         
				modelo.addColumn("Precio");
				scrollPane.setViewportView(tblSalida);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						enviarDatos();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		llenaTabla();
	}
	
	void enviarDatos() {
		int fila = tblSalida.getSelectedRow();
		if(fila==-1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
			return;
		}
		FrmBoleta.txtCodProducto.setText(tblSalida.getValueAt(fila, 0).toString());
		FrmBoleta.txtDesProducto.setText(tblSalida.getValueAt(fila, 1).toString());
		FrmBoleta.txtStockProducto.setText(tblSalida.getValueAt(fila, 2).toString());
		FrmBoleta.txtPreProducto.setText(tblSalida.getValueAt(fila, 3).toString());
		dispose();
	}
	
	void llenaTabla() {
		GestionProducto gp = new GestionProducto();
		ArrayList<Producto> lista = gp.listado();
		
		for(Producto p : lista) {
			Object datos[] = {p.getIdprod(),p.getDescripcion(),p.getStock(),p.getPrecio()};
			modelo.addRow(datos);
		}
	}

}
