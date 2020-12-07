package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.Model.Usuario;

public class UsuarioDAOImpl implements DAO<Usuario>
{
	private final Connection connection;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsuarioDAOImpl(Connection connection)
	{
		this.connection = connection;
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public List<Usuario> getAll() throws Exception
	{
		List<Usuario> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario u JOIN empleado e ON u.id_empleado = e.cedula");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Usuario usuario = createUsuario(resultSet);        	          
            elements.add(usuario);
        }
        
        return elements;
	}

	@Override
	public Usuario getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario u JOIN empleado e ON u.id_empleado = e.cedula WHERE u.id_empleado = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createUsuario(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Usuario t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO usuario(id_empleado, password)"
																+ " VALUES (?, ?)");
		
		statement.setString(1, t.getCedula());		
		statement.setString(2, t.getPassword());

		int affectedRows = statement.executeUpdate();

		return affectedRows;
	}

	@Override
	public int update(Usuario t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE usuario SET password=? "
																+ "WHERE id_empleado=?");

		statement.setString(1, t.getPassword());		
		statement.setString(2, t.getCedula());		

		return statement.executeUpdate();
	}

	@Override
	public int delete(Usuario t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE usuario SET is_active=?"
																+ " WHERE id_empleado=?");
		statement.setBoolean(1, false);		
		statement.setString(2, t.getCedula());		

		return statement.executeUpdate();
	}
	
	private Usuario createUsuario(ResultSet resultSet) throws SQLException
    {
		Usuario usuario = new Usuario(
                resultSet.getString("cedula"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidos"),                
                resultSet.getString("email"),
                resultSet.getString("telefono"),
                resultSet.getBoolean("is_active"),
                resultSet.getString("password")                
        );

        return usuario;
    }
}
