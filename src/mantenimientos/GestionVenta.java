package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.VentaInterface;
import model.Cabecera;
import model.Detalle;
import model.Producto;
import model.UsuaTip;
import utils.MySQLConexion;

public class GestionVenta implements VentaInterface{

	@Override
	public String generaNumBoleta() {
		String codigo = "B0001";
		// Plantilla de bd ---> busqueda o consulta
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = " select substring(max(num_bol),2) from tb_cab_boleta ";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				codigo = String.format("B%04d", rs.getInt(1)+1);
			}
		}catch(Exception e) {
			System.out.println("Error generar numero boleta " + e.getMessage());
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Error al cerrar " + e.getMessage());
			}
		}
		return codigo;
	}

	@Override
	public int realizarVenta(Cabecera c, ArrayList<Detalle> detalles) {
		int rs = 0;
		// Plantilla de bd ---> registrar
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			
			String sql1 = " insert into tb_cab_boleta values (?,?,?,?,?)";
			pst1 = con.prepareStatement(sql1);
			pst1.setString(1, c.getNumBoleta());
			pst1.setString(2, c.getFchBoleta());
			pst1.setInt(3, c.getCodCliente());
			pst1.setInt(4, c.getCodVendedor());
			pst1.setDouble(5, c.getTotalBoleta());
			rs = pst1.executeUpdate();
			
			String sql2 = " insert into tb_det_boleta values (?,?,?,?,?)";
			String sql3 = " update tb_productos set stock = stock - ? where idprod = ?";
			
			for(Detalle d : detalles) {
				pst2 = con.prepareStatement(sql2);
				pst2.setString(1, c.getNumBoleta());
				pst2.setString(2, d.getIdprod());
				pst2.setInt(3, d.getCantidad());
				pst2.setDouble(4, d.getPreciovta());
				pst2.setDouble(5, d.getImporte());
				rs += pst2.executeUpdate();
				
				pst3 = con.prepareStatement(sql3);
				pst3.setInt(1, d.getCantidad());
				pst3.setString(2, d.getIdprod());
				rs += pst3.executeUpdate();
			}
			con.commit();
		}catch(Exception e) {
			System.out.println("Error en realizar venta " + e.getMessage());
			rs=0;
			try {
				con.rollback();
			}catch(Exception e1){
				System.out.println("Error al deshacer " + e1.getMessage());
			}
		}finally {
			try {
				con.close();
			}catch(Exception e){
				System.out.println("Error al cerrar " + e.getMessage());
			}
		}
		return rs;
	}

	@Override
	public ArrayList<Cabecera> listadoxFecha(String fecha) {
		ArrayList<Cabecera> lista = null;
		// TODO Auto-generated method stub
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			
			String sql = " select * from tb_cab_boleta where fch_bol = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, fecha);
			rs = pst.executeQuery();
			
			//pasar el rs al listado
			lista = new ArrayList<Cabecera>();
			
			while (rs.next()) {
				Cabecera c = new Cabecera();
				c.setNumBoleta(rs.getString(1));
				c.setFchBoleta(rs.getString(2));
				c.setCodCliente(rs.getInt(3));
				c.setCodVendedor(rs.getInt(4));
				c.setTotalBoleta(rs.getDouble(5));
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

	@Override
	public ArrayList<Detalle> listadoDetalleBoleta(String numbol) {
		ArrayList<Detalle> lista = null;
		// TODO Auto-generated method stub
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; //almacenado el resultado de la consulta
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_det_boleta where num_bol = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, numbol);
			rs = pst.executeQuery();
			
			//pasar el rs al listado
			lista = new ArrayList<Detalle>();
			
			while (rs.next()) {//lee la fila del rs,hasta que no hayan datos
				Detalle d = new Detalle();
				d.setIdprod(rs.getString(2));
				d.setCantidad(rs.getInt(3));
				d.setPreciovta(rs.getDouble(4));
				d.setImporte(rs.getDouble(5));
				lista.add(d);
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
	
}
