package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.CategoriaInterface;
import model.Categoria;
import utils.MySQLConexion;

public class GestionCategoria implements CategoriaInterface {

	@Override
	public ArrayList<Categoria> listado() {
		ArrayList<Categoria> lista = null;
		// plantilla
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null; //almacenado el resultado de la consulta
		try {
			con = MySQLConexion.getConexion();
			
			String sql = " select * from tb_categorias ";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			//pasar el rs al listado
			lista = new ArrayList<Categoria>();
			
			while (rs.next()) {//lee la fila del rs,hasta que no hayan datos
				Categoria c = new Categoria();
				c.setIdtipo(rs.getInt(1));
				c.setDescripcion(rs.getString(2));
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

}
