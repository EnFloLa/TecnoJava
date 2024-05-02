package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.TipoInterface;
import model.Tipo;
import utils.MySQLConexion;

public class GestionTipo implements TipoInterface {

	@Override
	public ArrayList<Tipo> listado() {
		ArrayList<Tipo> lista = null;
		Connection con = null; 
		PreparedStatement pst = null; 
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_tipos";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			lista = new ArrayList<Tipo>();
			
			while (rs.next()) {
				Tipo t = new Tipo();
				t.setIdtipo(rs.getInt(1));
				t.setDescripcion(rs.getString(2));
				lista.add(t);
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
