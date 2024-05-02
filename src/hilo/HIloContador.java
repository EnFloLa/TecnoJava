package hilo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vista.Logueo;

public class HIloContador extends Thread {
	
	private JFrame ventana;
	
	public  HIloContador(JFrame ventana) {
		this.ventana = ventana;
	}
	
	@Override
	public void run() {
		for (int x = 30; x >= 0; x--) {
			Logueo.lblTiempo.setText(x + "s");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Error en pausa del contador");
			}
		}
		ventana.dispose();
	}
}
