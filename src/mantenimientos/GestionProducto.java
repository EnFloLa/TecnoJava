package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ProductoInterface;
import model.CatProd;
import model.Producto;
import utils.MySQLConexion;

public class GestionProducto implements ProductoInterface{

	@Override
	public int registrar(Producto p) {
		int rs = 0; 
		Connection con = null; 
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = " insert into tb_productos values (?,?,?,?,?) ";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getIdprod());
			pst.setString(2, p.getDescripcion());
			pst.setInt(3, p.getStock());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getIdtipo());
			
			rs = pst.executeUpdate();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el registrar:"+e.getMessage());
		} finally {
			try{
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar :" + e.getMessage());
			}
		}
		return rs;
		
	}

	@Override
	public int editar(Producto p) {
		int rs = 0;
		Connection con = null; 
		PreparedStatement pst = null; 
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = " update tb_productos set descripcion=?,stock=?,precio=?, idtipo=? where idprod = ? ";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getDescripcion());
			pst.setInt(2, p.getStock());
			pst.setDouble(3, p.getPrecio());
			pst.setInt(4, p.getIdtipo());
			pst.setString(5, p.getIdprod());
			
			rs = pst.executeUpdate();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar:"+e.getMessage());
		} finally {
			try{
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar :" + e.getMessage());
			}
		}
		return rs;
	}

	@Override
	public ArrayList<CatProd> listadocatxprod(int idtipo) {
		ArrayList<CatProd> lista = null;
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "SELECT c.descripcion AS CATEGORIA, count(*) AS 'TOTAL PRODUCTOS' "
					+ " FROM tb_categorias c "
					+ " join tb_productos p ON c.idtipo=p.idtipo "
					+ " GROUP BY c.idtipo ";
			pst = con.prepareStatement(sql);
			//pst.setInt(1, idtipo);
			rs = pst.executeQuery();
			
			//pasar el rs al listado
			lista = new ArrayList<CatProd>();
			
			while (rs.next()) {
				CatProd c = new CatProd();
				c.setDescripcion(rs.getString(1));
				c.setIdtipo(rs.getInt(2));
				lista.add(c);
			}
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al listar:"+e.getMessage());
		} finally {
			try{
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar :" + e.getMessage());
			}
		}
		return lista;
	}
	
	public ArrayList<Producto> listado() {
		ArrayList<Producto> lista = null;
		// TODO Auto-generated method stub
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_productos";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			lista = new ArrayList<Producto>();
			
			while (rs.next()) {
				Producto p = new Producto();
				p.setIdprod(rs.getString(1));
				p.setDescripcion(rs.getString(2));
				p.setStock(rs.getInt(3));
				p.setPrecio(rs.getDouble(4));
				lista.add(p);
			}
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al listar:"+e.getMessage());
		} finally {
			try{
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar :" + e.getMessage());
			}
		}
		return lista;
	}
	
	@Override
	public Producto buscar(String codigo) {
		Producto p = null;
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_productos where idprod = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, codigo);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				p = new Producto();
				p.setIdprod(rs.getString(1));
				p.setDescripcion(rs.getString(2));
				p.setStock(rs.getInt(3));
				p.setPrecio(rs.getDouble(4));
				p.setIdtipo(rs.getInt(5));
			}
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al listar:"+e.getMessage());
		} finally {
			try{
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar :" + e.getMessage());
			}
		}
		return p;
	}

}
