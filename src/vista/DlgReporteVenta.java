package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDayChooser;

import mantenimientos.GestionProducto;
import mantenimientos.GestionVenta;
import model.Cabecera;
import model.Detalle;
import utils.MySQLConexion;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;

public class DlgReporteVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDateChooser txtFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgReporteVenta dialog = new DlgReporteVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgReporteVenta() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgReporteVenta.class.getResource("/img/ICONITOGOD.png")));
		getContentPane().setForeground(Color.LIGHT_GRAY);
		setTitle("Reporte Venta por Fecha");
		setBounds(100, 100, 372, 173);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.ORANGE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtFecha = new JDateChooser();
		txtFecha.setBounds(10, 76, 145, 20);
		contentPanel.add(txtFecha);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listadoPDF();
			}
		});
		btnConsultar.setBounds(184, 73, 89, 23);
		contentPanel.add(btnConsultar);
		
		JLabel lblNewLabel = new JLabel("REPORTE DE VENTA POR FECHA");
		lblNewLabel.setBounds(21, 23, 181, 14);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	
	
	void listadoPDF() {
		String fecha = leerFecha();
		GestionVenta gv = new GestionVenta();
		ArrayList<Cabecera> lista = gv.listadoxFecha(fecha);
		if(lista.size() == 0) {
			alerta("No hay ventas en la fecha indicada");
			return;
		}
		String nomarchivo = "reportes/reporteventa.pdf";
		try {
			Document doc = new Document();
			FileOutputStream fos = new FileOutputStream(nomarchivo);
			PdfWriter pdfw = PdfWriter.getInstance(doc, fos);
			
			doc.open();
			
			doc.add(new Paragraph("Reporte de Venta"));
			doc.add(new Paragraph("Del " + fecha));
			doc.add(new Paragraph(" "));
			for(Cabecera c : lista) {
				doc.add(new Paragraph(c.getNumBoleta() + " : "));
				doc.add(new Paragraph(String.format("					\tTotal S/%5.2f", c.getTotalBoleta())));
				doc.add(new Paragraph("-----------------------------------------------------"));
			}
			
			doc.close();
			
			Desktop.getDesktop().open(new File(nomarchivo));
		}catch(Exception e){
			alerta("No se puede crear el archivo PDF");
			e.printStackTrace();
		}	
	}
	
	private String leerFecha() {
		return new SimpleDateFormat("yyyy/MM/dd").format(txtFecha.getDate());
	}
	
	
	void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.INFORMATION_MESSAGE);
	}
}
