package uam.bd.restaurante.BD.Controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.DAOmysql.ClienteDAOImpl;
import uam.bd.restaurante.BD.Model.Cliente;
import uam.bd.restaurante.BD.MysqlConnector.DBConnection;

@RestController
public class ClienteController 
{
	private DAO<Cliente> clienteDAO;
	
	public ClienteController()
	{
		clienteDAO = new ClienteDAOImpl(DBConnection.getConnection());
	}
	
	@PostMapping(path="/clientes")
	public boolean saveCliente(@RequestBody Cliente t) 
	{			
		try 
		{
			return clienteDAO.save(t) > 0;
		} 
		catch (DataIntegrityViolationException e) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este Cliente Ya Existe", e);
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}	
	
	@GetMapping("/clientes")		
	public List<Cliente> get()
	{		
		List<Cliente> clientes;
		try 
		{
			clientes = clienteDAO.getAll();
			return clientes;
		} 
		catch (Exception e) 
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}		
	}
	
	@DeleteMapping("/clientes")
	public boolean deleteCliente(@RequestBody Cliente t)
	{
		try 
		{
			return clienteDAO.delete(t) > 0;
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
	
	@PutMapping("/clientes")
	public boolean updateCliente(@RequestBody Cliente t)
	{
		try
		{
			return clienteDAO.update(t) > 0;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
}
