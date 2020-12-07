package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.Model.Domiciliario;

public class DomiciliarioDAOImpl implements DAO<Domiciliario>
{
	private final Connection connection;
	
	public DomiciliarioDAOImpl(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public List<Domiciliario> getAll() throws Exception
	{
		List<Domiciliario> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM domiciliario");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Domiciliario domiciliario = createDomiciliario(resultSet);        	          
            elements.add(domiciliario);
        }
        
        return elements;
	}

	@Override
	public Domiciliario getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM domiciliario WHERE cedula = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createDomiciliario(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Domiciliario t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO domiciliario(cedula, nombre, apellidos, telefono, email)"
																+ " VALUES (?, ?, ?, ?, ?)");
		
		statement.setString(1, t.getCedula());
		statement.setString(2, t.getNombre());
		statement.setString(3, t.getApellidos());
		statement.setString(4, t.getTelefono());
		statement.setString(5, t.getEmail());

		int affectedRows = statement.executeUpdate();

		return affectedRows;
	}

	@Override
	public int update(Domiciliario t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE domiciliario SET nombre=?, apellidos=?, telefono=?, email=?"
																+ " WHERE cedula=?");

		statement.setString(1, t.getNombre());		
		statement.setString(2, t.getApellidos());
		statement.setString(3, t.getTelefono());
		statement.setString(4, t.getEmail());
		statement.setString(5, t.getCedula());

		return statement.executeUpdate();
	}

	@Override
	public int delete(Domiciliario t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE domiciliario "
																+ "SET is_active=?"
																+ " WHERE cedula=?");

		statement.setBoolean(1, false);		
		statement.setString(2, t.getCedula());		

		return statement.executeUpdate();
	}
	
	private Domiciliario createDomiciliario(ResultSet resultSet) throws SQLException
    {
		Domiciliario repartidor = new Domiciliario(
                resultSet.getString("cedula"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidos"),                
                resultSet.getString("email"),
                resultSet.getBoolean("is_active"),
                resultSet.getString("telefono")
        );

        return repartidor;
    }
}
