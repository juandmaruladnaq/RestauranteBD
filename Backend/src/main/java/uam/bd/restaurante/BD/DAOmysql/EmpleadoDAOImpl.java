package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.Model.Empleado;

public class EmpleadoDAOImpl implements DAO<Empleado>
{
	private final Connection connection;
	
	public EmpleadoDAOImpl(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public List<Empleado> getAll() throws Exception
	{
		List<Empleado> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM empleado");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Empleado empleado = createEmpleado(resultSet);        	          
            elements.add(empleado);
        }
        
        return elements;
	}

	@Override
	public Empleado getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM empleado WHERE cedula = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createEmpleado(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Empleado t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO empleado"
																+ "(cedula, nombre, apellidos, telefono, email)"
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
	public int update(Empleado t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE empleado "
																+ "SET nombre=?, apellidos=?, telefono=?, email=?"
																+ " WHERE cedula=?");

		statement.setString(1, t.getNombre());		
		statement.setString(2, t.getApellidos());
		statement.setString(3, t.getTelefono());
		statement.setString(4, t.getEmail());
		statement.setString(5, t.getCedula());

		return statement.executeUpdate();
	}

	@Override
	public int delete(Empleado t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE empleado "
																+ "SET is_active=?"
																+ " WHERE cedula=?");

		statement.setBoolean(1, false);		
		statement.setString(2, t.getCedula());		

		return statement.executeUpdate();
	}
	
	private Empleado createEmpleado(ResultSet resultSet) throws SQLException
    {
		Empleado empleado = new Empleado(
                resultSet.getString("cedula"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidos"),                
                resultSet.getString("email"),
                resultSet.getString("telefono"),
                resultSet.getBoolean("is_active")                
        );

        return empleado;
    }

}
