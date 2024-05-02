package model;


public class Producto {
	private String idprod;
	private String descripcion;
	private int stock;
	private double precio;
	private int idtipo;
	
	@Override
	public String toString() {
		return "Producto [idprod=" + idprod + ", descripcion=" + descripcion + ", stock=" + stock + ", precio=" + precio
				+ ", idtipo=" + idtipo + "]";
	}
	public Producto() {
	}
	public Producto(String idprod, String descripcion, int stock, double precio, int idtipo) {
		super();
		this.idprod = idprod;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.idtipo = idtipo;
	}
	public String getIdprod() {
		return idprod;
	}
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

}




