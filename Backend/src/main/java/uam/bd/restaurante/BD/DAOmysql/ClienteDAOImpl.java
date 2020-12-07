package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.Model.Cliente;


public class ClienteDAOImpl implements DAO<Cliente>
{
	private final Connection connection;
	
	public ClienteDAOImpl(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public List<Cliente> getAll() throws Exception
	{
		List<Cliente> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Cliente cliente = createCliente(resultSet);        	          
            elements.add(cliente);
        }
        
        return elements;
	}

	@Override
	public Cliente getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE cedula = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createCliente(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Cliente t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO cliente"
																+ "(cedula, nombre, apellidos, direccion, telefono, email)"
																+ " VALUES (?, ?, ?, ?, ?, ?)");
		
		statement.setString(1, t.getCedula());
		statement.setString(2, t.getNombre());
		statement.setString(3, t.getApellidos());
		statement.setString(4, t.getDireccion());
		statement.setString(5, t.getTelefono());
		statement.setString(6, t.getEmail());

		int affectedRows = statement.executeUpdate();

		return affectedRows;
	}

	@Override
	public int update(Cliente t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE cliente "
																+ "SET nombre=?, apellidos=?, telefono=?, email=?"
																+ " WHERE cedula=?");
        
		statement.setString(1, t.getNombre());		
		statement.setString(2, t.getApellidos());
		statement.setString(3, t.getTelefono());
		statement.setString(4, t.getEmail());
		

		return statement.executeUpdate();
	}

	@Override
	public int delete(Cliente t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE cliente "
																+ "SET is_active=?"
																+ " WHERE cedula=?");

		statement.setBoolean(1, false);		
		statement.setString(2, t.getCedula());		

		return statement.executeUpdate();
	}
	
	private Cliente createCliente(ResultSet resultSet) throws SQLException
    {
		Cliente cliente = new Cliente(
                resultSet.getString("cedula"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidos"), 
                resultSet.getString("direccion"),
                resultSet.getString("email"),
                resultSet.getBoolean("is_active"),
                resultSet.getString("telefono")
        );

        return cliente;
    }

}
