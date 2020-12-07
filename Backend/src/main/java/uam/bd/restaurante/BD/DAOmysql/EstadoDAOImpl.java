package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.Model.Estado;

public class EstadoDAOImpl implements DAO<Estado>
{
	private final Connection connection;	
	
	public EstadoDAOImpl(Connection connection) 
	{
		this.connection = connection;
	}

	@Override
	public List<Estado> getAll() throws Exception 
	{
		List<Estado> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM estado_pedido");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Estado estado = createEstado(resultSet);        	          
            elements.add(estado);
        }
        
        return elements;
	}

	@Override
	public Estado getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM estado_pedido WHERE numero = ?");

		statement.setInt(1, Integer.parseInt(id));		
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createEstado(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Estado t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO estado_pedido(nombre)"
																+ " VALUES (?)");
		
		statement.setString(1, t.getNombre());				

		int affectedRows = statement.executeUpdate();

		return affectedRows;
	}

	@Override
	public int update(Estado t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE estado_pedido SET nombre=? "
																+ "WHERE numero=?");

		statement.setString(1, t.getNombre());		
		statement.setInt(2, t.getNumero());		

		return statement.executeUpdate();
	}

	@Override
	public int delete(Estado t) throws Exception 
	{

		PreparedStatement statement = connection.prepareStatement("DELETE FROM estado_pedido "
																+ " WHERE numero=?");
		statement.setInt(1, t.getNumero());			

		return statement.executeUpdate();
	}
	
	private Estado createEstado(ResultSet resultSet) throws SQLException
    {		
		Estado estado = new Estado(
                resultSet.getInt("numero"),
                resultSet.getString("nombre")                            
        );

        return estado;
    }

}
