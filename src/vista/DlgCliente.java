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

import mantenimientos.GestionUsuario;
import model.Usuario;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCliente extends JDialog {

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
			DlgCliente dialog = new DlgCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCliente() {
		setTitle("Busqueda de Clientes");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(0, 0, 230, 37);
		contentPanel.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 48, 434, 169);
		contentPanel.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Codigo");				//0
		modelo.addColumn("Nombre Completo");	//1
		tblSalida.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		
		scrollPane.setViewportView(tblSalida);
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
		if(fila == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
			return;
		}
		FrmBoleta.txtCodCliente.setText(tblSalida.getValueAt(fila, 0).toString());
		FrmBoleta.txtNomCompletoCliente.setText(tblSalida.getValueAt(fila, 1).toString());
		dispose();
	}
	
	void llenaTabla() {
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Usuario> lista = gu.listadoxtipo(2); 
		
		for(Usuario u : lista) {
			Object datos[] = {u.getCodigo(), u.getNombre() + " " + u.getApellido()};
			modelo.addRow(datos);
		}
	}
}
