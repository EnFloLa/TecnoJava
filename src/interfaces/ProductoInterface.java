package interfaces;

import java.util.ArrayList;

import model.CatProd;
import model.Producto;

public interface ProductoInterface {
public int registrar(Producto p);

public int editar(Producto p);

public ArrayList<CatProd> listadocatxprod(int idtipo);

public Producto buscar(String codigo);
}
