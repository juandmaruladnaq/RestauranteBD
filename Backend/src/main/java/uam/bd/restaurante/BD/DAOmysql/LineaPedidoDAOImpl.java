package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO_Foreign;
import uam.bd.restaurante.BD.Model.LineaPedido;
import uam.bd.restaurante.BD.Model.Producto;

public class LineaPedidoDAOImpl implements DAO_Foreign<LineaPedido>
{      
	private  final Connection connection;
	
	private ProductoDAOImpl productoDAO;

	public LineaPedidoDAOImpl(Connection connection) 
	{
		super();
		this.connection = connection;
		this.productoDAO = new ProductoDAOImpl(connection);
	}

	@Override
	public List<LineaPedido> getAll() throws Exception 
	{
        List<LineaPedido> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM productos_pedido");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
            LineaPedido lineaPedido = createLineaPedido(resultSet);
            
            elements.add(lineaPedido);
        }
        
        return elements;
	}

	@Override
	public LineaPedido getBy(String id) throws Exception 
	{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM productos_pedido WHERE id_pedido = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createLineaPedido(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(LineaPedido t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("INSERT INTO productos_pedido(id_pedido, id_producto, cantidad, valor_un)"
																+ " VALUES (?,?,?,?)");
	        
		statement.setInt(1, t.getIdPedido());
		statement.setString(2, t.getIdProducto());
		statement.setInt(3, t.getCantidad());
		statement.setFloat(4, t.getValorUn());

		int affectedRows = statement.executeUpdate();
		
		return affectedRows;
	}

	@Override
	public int update(LineaPedido t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE productos_pedido SET id_producto=?, cantidad=?, valor_un=? "
																+ "WHERE id_pedido=?");

		statement.setString(1, t.getIdProducto());
		statement.setInt(2, t.getCantidad());
		statement.setFloat(3, t.getValorUn());
		statement.setInt(3, t.getIdPedido());

		return statement.executeUpdate();
	}

	@Override
	public int delete(LineaPedido t) throws Exception 
	{

		PreparedStatement statement = connection.prepareStatement("DELETE FROM productos_pedido "
																+ " WHERE id_pedido = ? AND id_producto = ?");
		statement.setInt(1, t.getIdPedido());	
		statement.setString(2, t.getIdProducto());	

		return statement.executeUpdate();
	}

	@Override
	public List<LineaPedido> getAllById(String id) throws Exception 
	{
		List<LineaPedido> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM productos_pedido WHERE id_pedido = ?");
        statement.setString(1, id);	        
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	LineaPedido lineaPedido = createLineaPedido(resultSet);        	          
            elements.add(lineaPedido);
        }
        
        return elements;
	}
	 
	private LineaPedido createLineaPedido(ResultSet resultSet) throws Exception 
	{
		String idProducto = resultSet.getString("id_producto");
		Producto producto = productoDAO.getBy(idProducto);
		
		LineaPedido lineaPedido = new LineaPedido(
				resultSet.getInt("id_pedido"), 				
				producto,
				resultSet.getInt("cantidad"),
				resultSet.getFloat("valor_un")				
		);

		return lineaPedido;
	}	
	
}
