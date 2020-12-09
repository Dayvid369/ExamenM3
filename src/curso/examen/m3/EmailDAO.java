package curso.examen.m3;

import java.sql.Connection;
import java.sql.PreparedStatement;



public class EmailDAO {
	
	public EmailDAO(String nombre, String emailDestinatario, String provincia, String mensajeDestinatario) {
		// TODO Auto-generated constructor stub
	}

	public void almacenarDatos(String nombre,String emailDestinatario,String provincia,String mensajeDestinatario)throws Exception {
		// TODO Auto-generated method stub
		
		Conexion conexion = new Conexion();
		Connection miConexion = null;
		PreparedStatement miStatement=null;
		
		try {
			
			//miConexion=origenDatos.getConnection();

			miConexion = conexion.conectar();

			String sql="INSERT INTO datosemail (id,nombre,provincia,email,mensaje) VALUES (?,?,?,?,?) ";
			miStatement=miConexion.prepareStatement(sql);
		
		String id = null;
		miStatement.setString(1, id);
		miStatement.setString(2, nombre);
		miStatement.setString(3, provincia);
		miStatement.setString(4, emailDestinatario);
		miStatement.setString(5, mensajeDestinatario);

		
		miStatement.execute();
		
		 
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally {
			
			miStatement.close();
			miConexion.close();
		}
		
		
	}

}
