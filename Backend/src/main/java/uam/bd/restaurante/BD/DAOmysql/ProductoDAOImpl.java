package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.Model.Producto;

public class ProductoDAOImpl implements DAO<Producto>
{

	private final Connection connection;

	public ProductoDAOImpl(Connection connection) 
	{
		super();
		this.connection = connection;
	}

	@Override
	public List<Producto> getAll() throws Exception 
	{
		List<Producto> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM producto");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Producto producto = createProducto(resultSet);        	          
            elements.add(producto);
        }
        
        return elements;
	}

	@Override
	public Producto getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM producto WHERE codigo = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createProducto(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Producto t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO producto(codigo, nombre, descripcion, valor)"
																+ " VALUES (?, ?, ?, ?)");
		
		statement.setString(1, t.getCodigo());		
		statement.setString(2, t.getNombre());
		statement.setString(3, t.getDescripcion());
		statement.setFloat(4, t.getValor());

		int affectedRows = statement.executeUpdate();

		return affectedRows;
	}

	@Override
	public int update(Producto t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE producto SET nombre=?, descripcion=?, valor=? "
																+ "WHERE codigo=?");
			
		statement.setString(1, t.getNombre());
		statement.setString(2, t.getDescripcion());
		statement.setFloat(3, t.getValor());	
		statement.setString(4, t.getCodigo());	

		return statement.executeUpdate();
	}

	@Override
	public int delete(Producto t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE producto SET is_active=?"
																+ " WHERE codigo=?");
		statement.setBoolean(1, false);		
		statement.setString(2, t.getCodigo());		

		return statement.executeUpdate();
	}
	
	private Producto createProducto(ResultSet resultSet) throws SQLException
    {
		Producto producto = new Producto(
                resultSet.getString("codigo"),  
                resultSet.getString("nombre"),
                resultSet.getString("descripcion"),
                resultSet.getFloat("valor"),
                resultSet.getBoolean("is_active")
        );

        return producto;
    }
	
}
