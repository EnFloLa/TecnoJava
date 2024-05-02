package model;

public class UsuaTip {
	private int codigo;
	private String nombre;
	private String apellido;
	private String descripcionTipo;
	private String fnacim;
	
	
	
	@Override
	public String toString() {
		return "UsuaTip [codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", descripcionTipo="
				+ descripcionTipo + ", fnacim=" + fnacim + "]";
	}
	
	public UsuaTip(int codigo, String nombre, String apellido, String descripcionTipo, String fnacim) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.descripcionTipo = descripcionTipo;
		this.fnacim = fnacim;
	}
	public UsuaTip() {
		
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDescripcionTipo() {
		return descripcionTipo;
	}
	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}
	public String getFnacim() {
		return fnacim;
	}
	public void setFnacim(String fnacim) {
		this.fnacim = fnacim;
	}
	
	
}
