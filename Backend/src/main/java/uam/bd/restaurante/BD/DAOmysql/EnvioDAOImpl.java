package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO_Foreign;
import uam.bd.restaurante.BD.Model.Domiciliario;
import uam.bd.restaurante.BD.Model.Envio;

public class EnvioDAOImpl implements DAO_Foreign<Envio> 
{
	private final Connection connection;
	
	private DomiciliarioDAOImpl domiciliarioDAO;
	
	public EnvioDAOImpl(Connection connection)
	{
		this.connection = connection;
		this.domiciliarioDAO = new DomiciliarioDAOImpl(connection);
	}

	@Override
	public List<Envio> getAll() throws Exception 
	{
		List<Envio> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM envio");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Envio envio = createEnvio(resultSet);        	          
            elements.add(envio);
        }
        
        return elements;
	}

	@Override
	public Envio getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM envio WHERE numero = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createEnvio(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Envio t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO envio( domiciliario)"
																+ " VALUES (?)");
		
				
		statement.setString(1, t.getDomiciliario().getCedula());

		int affectedRows = statement.executeUpdate();

		return affectedRows;
	}

	@Override
	public int update(Envio t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE envio SET fecha=?, domiciliario=? "
																+ "WHERE numero=?");

		statement.setTimestamp(1, t.getFecha());		
		statement.setString(2, t.getDomiciliario().getCedula());		

		return statement.executeUpdate();
	}

	@Override
	public int delete(Envio t) throws Exception 
	{

		PreparedStatement statement = connection.prepareStatement("DELETE FROM envio "
																+ " WHERE numero=?");
		statement.setInt(1, t.getNumero());			

		return statement.executeUpdate();
	}

	@Override
	public List<Envio> getAllById(String id) throws Exception 
	{
		List<Envio> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM envio WHERE domiciliario = ?");
        statement.setString(1, id);	        
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Envio envio = createEnvio(resultSet);        	          
            elements.add(envio);
        }
        
        return elements;
	}
	
	private Envio createEnvio(ResultSet resultSet) throws Exception 
	{
		String idDomiciliario = resultSet.getString("domiciliario");
		Domiciliario domiciliario = domiciliarioDAO.getBy(idDomiciliario);
		
		Envio envio = new Envio(
				resultSet.getInt("numero"),				
				resultSet.getTimestamp("fecha"),
				domiciliario				
		);

		return envio;
	}	
	
}
