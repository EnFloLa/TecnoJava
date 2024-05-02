package model;

public class CatProd {
	private String descripcion;
	private int idtipo;
	
	@Override
	public String toString() {
		return "CatProd [descripcion=" + descripcion + ", idtipo=" + idtipo + "]";
	}
	public CatProd() {
		
	}
	public CatProd(String descripcion, int idtipo) {
		super();
		this.descripcion = descripcion;
		this.idtipo = idtipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}
	
}
