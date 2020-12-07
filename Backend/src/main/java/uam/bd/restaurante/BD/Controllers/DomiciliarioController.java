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
import uam.bd.restaurante.BD.DAOmysql.DomiciliarioDAOImpl;
import uam.bd.restaurante.BD.Model.Cliente;
import uam.bd.restaurante.BD.Model.Domiciliario;
import uam.bd.restaurante.BD.MysqlConnector.DBConnection;

@RestController
public class DomiciliarioController 
{
	private DAO<Domiciliario> domiciliarioDAO;
	
	public DomiciliarioController()
	{
		domiciliarioDAO = new DomiciliarioDAOImpl(DBConnection.getConnection());
	}
	
	@PostMapping(path="/domiciliarios")
	public boolean saveDomiciliario(@RequestBody Domiciliario t) 
	{			
		try 
		{
			return domiciliarioDAO.save(t) > 0;
		} 
		catch (DataIntegrityViolationException e) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este Domiciliario Ya Existe", e);
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}	
	
	@GetMapping("/domiciliarios")		
	public List<Domiciliario> get()
	{		
		List<Domiciliario> domiciliarios;
		try 
		{
			domiciliarios = domiciliarioDAO.getAll();
			return domiciliarios;
		} 
		catch (Exception e) 
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}		
	}
	
	@PostMapping("/domiciliarios/id")		
	public Domiciliario getById(@RequestBody String id)
	{		
		Domiciliario domiciliario;
		try 
		{
			domiciliario = domiciliarioDAO.getBy(id);
			return domiciliario;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}		
	}
	
	@DeleteMapping("/domiciliarios")
	public boolean deleteDomiciliario(@RequestBody Domiciliario t)
	{
		try 
		{
			return domiciliarioDAO.delete(t) > 0;
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
	
	@PutMapping("/domiciliarios")
	public boolean updateDomiciliario(@RequestBody Domiciliario t)
	{
		try
		{
			return domiciliarioDAO.update(t) > 0;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
}