package interfaces;

import java.util.ArrayList;

import model.Usuario;

//metodos public --> mantenimiento --> "QUE" procesos realizaremos
public interface UsuarioInterface {
	
	//0 -> Error / != -> Exito
	public int registrar(Usuario u);
	
	//para eliminar un usuario
	public int eliminar(int codigo);
	
	//para actualizar
	public int actualizar(Usuario u);
	
	//para obtener un listado de todos los usuarios
	public ArrayList<Usuario> listado();
	
	//para obtener un usuario
	public Usuario buscar(int codigo);
	
	public ArrayList<Usuario> listadoxtipo(int tipo);
	
	public Usuario validarAcceso(String usuario, String clave);
}
