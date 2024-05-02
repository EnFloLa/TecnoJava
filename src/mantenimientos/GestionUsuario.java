package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.UsuarioInterface;
import model.Tipo;
import model.Usuario;
import utils.MySQLConexion;

public class GestionUsuario implements UsuarioInterface{

	@Override
	public int registrar(Usuario u) {
		int rs = 0; 
		Connection con = null; 
		PreparedStatement pst = null; 
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = " insert into tb_usuarios values (null,?,?,?,?,?,?,default) ";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getNombre());
			pst.setString(2, u.getApellido());
			pst.setString(3, u.getUsuario());
			pst.setString(4, u.getClave());
			pst.setString(5, u.getFnacim());
			pst.setInt(6, u.getTipo());
			
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
	public int eliminar(int codigo) {
		int rs = 0;
		Connection con = null; 
		PreparedStatement pst = null; 
		try {
			con = MySQLConexion.getConexion();
			
			String sql = " delete from tb_usuarios u where u.codigo = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			
			rs = pst.executeUpdate();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar:"+e.getMessage());
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
	public int actualizar(Usuario u) {
		int rs = 0;
		Connection con = null; 
		PreparedStatement pst = null; 
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = " update tb_usuarios set nombre=?,apellido=?,clave=?, tipo=? where codigo = ? ";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getNombre());
			pst.setString(2, u.getApellido());
			pst.setString(3, u.getClave());
			pst.setInt(4, u.getTipo());
			pst.setInt(5, u.getCodigo());
			
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
	public ArrayList<Usuario> listado() {
		ArrayList<Usuario> lista = null;
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_usuarios";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			lista = new ArrayList<Usuario>();
			
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setUsuario(rs.getString(4));
				u.setClave(rs.getString(5));
				u.setFnacim(rs.getString(6));
				u.setTipo(rs.getInt(7));
				u.setEstado(rs.getInt(8));
				lista.add(u);
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
	public Usuario buscar(int codigo) {
		Usuario u = null;
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_usuarios where codigo = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				 u = new Usuario();
				u.setCodigo(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setUsuario(rs.getString(4));
				u.setClave(rs.getString(5));
				u.setFnacim(rs.getString(6));
				u.setTipo(rs.getInt(7));
				u.setEstado(rs.getInt(8));
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
		return u;
	}

	@Override
	public ArrayList<Usuario> listadoxtipo(int tipo) {
		ArrayList<Usuario> lista = null;
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_usuarios where tipo = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, tipo);
			rs = pst.executeQuery();
			
			lista = new ArrayList<Usuario>();
			
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setUsuario(rs.getString(4));
				u.setClave(rs.getString(5));
				u.setFnacim(rs.getString(6));
				u.setTipo(rs.getInt(7));
				u.setEstado(rs.getInt(8));
				lista.add(u);
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
	public Usuario validarAcceso(String usuario, String clave) {
		Usuario u = null;
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select u.* "
					+ "	from tb_usuarios u "
					+ "	where u.usuario =? and u.clave=? ";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, usuario);
			pst.setString(2, clave);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				u = new Usuario();
				u.setCodigo(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setUsuario(rs.getString(4));
				u.setClave(rs.getString(5));
				u.setFnacim(rs.getString(6));
				u.setTipo(rs.getInt(7));
				u.setEstado(rs.getInt(8));
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
		return u;
	}

}
