package uam.bd.restaurante.BD.DAOmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uam.bd.restaurante.BD.DAO.DAO_Foreign;
import uam.bd.restaurante.BD.Model.Cliente;
import uam.bd.restaurante.BD.Model.Envio;
import uam.bd.restaurante.BD.Model.Estado;
import uam.bd.restaurante.BD.Model.Pedido;
import uam.bd.restaurante.BD.Model.Usuario;

public class PedidoDAOImpl implements DAO_Foreign<Pedido>
{
	private final Connection connection;
	
	private ClienteDAOImpl clienteDAOImpl;
	
	private UsuarioDAOImpl usuarioDAOImpl;
	
	private EstadoDAOImpl estadoDAOImpl;
	
	private EnvioDAOImpl envioDAOImpl;

	public PedidoDAOImpl(Connection connection)
	{
		this.connection = connection;
		this.clienteDAOImpl = new ClienteDAOImpl(connection);
		this.usuarioDAOImpl = new UsuarioDAOImpl(connection);
		this.estadoDAOImpl = new EstadoDAOImpl(connection);
		this.envioDAOImpl = new EnvioDAOImpl(connection);
	}

	@Override
	public List<Pedido> getAll() throws Exception 
	{
		List<Pedido> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Pedido pedido = createPedido(resultSet);        	          
            elements.add(pedido);
        }
        
        return elements;
	}

	@Override
	public Pedido getBy(String id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido WHERE numero = ?");

		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createPedido(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Pedido t) throws Exception 
	{		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO pedido(cliente, usuario, estado)"
																+ " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);		
				
		statement.setString(1, t.getCliente().getCedula());
		statement.setString(2, t.getUsuario().getCedula());
		statement.setInt(3, Estado.EN_PREPARACION);		

		int affectedRows = statement.executeUpdate();
		if(affectedRows == 1)
		{
			ResultSet generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();
			t.setNumero(generatedKeys.getInt(1));
		}		

		return t.getNumero();
	}

	@Override
	public int update(Pedido t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("UPDATE pedido SET fecha=?, cliente=?, usuario=?, estado=?, id_envio=? "
																+ "WHERE numero=?");

		statement.setTimestamp(1, t.getFecha());		
		statement.setString(2, t.getCliente().getCedula());
		statement.setString(3, t.getUsuario().getCedula());
		statement.setInt(4, t.getEstado().getNumero());
		statement.setInt(5, t.getEnvio().getNumero());
		statement.setInt(6, t.getNumero());
		return statement.executeUpdate();
	}
	


	@Override
	public int delete(Pedido t) throws Exception 
	{

		PreparedStatement statement = connection.prepareStatement("DELETE FROM pedido "
																+ " WHERE numero=?");
		statement.setInt(1, t.getNumero());			

		return statement.executeUpdate();
	}

	@Override
	public java.util.List<Pedido> getAllById(String id) throws Exception 
	{
		List<Pedido> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido WHERE id_envio = ?");
        statement.setString(1, id);	        
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Pedido pedido = createPedido(resultSet);        	          
            elements.add(pedido);
        }
        
        return elements;
	}
	
	private Pedido createPedido(ResultSet resultSet) throws Exception 
	{
		String idCliente = resultSet.getString("cliente");
		Cliente cliente = clienteDAOImpl.getBy(idCliente);
		
		String idUsuario = resultSet.getString("usuario");
		Usuario usuario = usuarioDAOImpl.getBy(idUsuario);
		
		int idEstado = resultSet.getInt("estado");
		Estado estado = estadoDAOImpl.getBy(String.valueOf(idEstado));
		
		int idEnvio = resultSet.getInt("id_envio");
		Envio envio = envioDAOImpl.getBy(String.valueOf(idEnvio));
		
		Pedido pedido = new Pedido(
				resultSet.getInt("numero"),				
				resultSet.getTimestamp("fecha"),
				cliente,
				usuario,
				estado
		);
		
		pedido.setEstado(estado);
		pedido.setEnvio(envio);		

		return pedido;
	}
	
}
