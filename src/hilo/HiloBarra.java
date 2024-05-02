package hilo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vista.FrmPreLoader;
import vista.FrmPrincipal;

public class HiloBarra extends Thread{
	
	private JFrame ventana;
	public  HiloBarra(JFrame ventana) {
		this.ventana = ventana;
	}
	
	@Override
	public void run() {
		//contador de 0.......100
				for (int x = 0; x <= 100; x++) {
				//dar valor a la barra del contador
				FrmPreLoader.prbCarga.setValue(x);
				switch (x) {
				case 25: FrmPreLoader.lblMensajes.setText("Cargando datos..."); break;
				case 45: FrmPreLoader.lblMensajes.setText("Revisando stock..."); break;
				case 70: FrmPreLoader.lblMensajes.setText("Estableciendo conexion..."); break;
				case 90: FrmPreLoader.lblMensajes.setText("YOU ARE IN..."); break;
				default:
					break;
				}
				
				try {
					//pausa
					Thread.sleep(150);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null,"Error en pausa de barra");
				}
				}
		FrmPrincipal v = new FrmPrincipal();
		v.setVisible(true);
		ventana.dispose();
	}
}
