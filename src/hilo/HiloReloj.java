package hilo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import vista.FrmPrincipal;

public class HiloReloj extends Thread{
 @Override
public void run() {
	 while (true) {
			//capturar la hora del sistema
			Date hora = new Date(); //fecha y hora 
			//mostrar la hora en el lblReloj
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
			FrmPrincipal.lblReloj.setText(sdf.format(hora));
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Error en pausa del Reloj"+ e.getMessage());
			}
		}
}
}
