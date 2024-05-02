package interfaces;

import java.util.ArrayList;

import model.Cabecera;
import model.Detalle;

public interface VentaInterface {
	//devuelve el autogenerado de numeros de boleta
	public String generaNumBoleta();
	
	public int realizarVenta(Cabecera c, ArrayList<Detalle>detalles);

	public ArrayList<Cabecera> listadoxFecha(String fecha);
	
	public ArrayList<Detalle> listadoDetalleBoleta(String numbol);
}
