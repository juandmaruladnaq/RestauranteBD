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
import uam.bd.restaurante.BD.DAOmysql.EmpleadoDAOImpl;
import uam.bd.restaurante.BD.Model.Empleado;
import uam.bd.restaurante.BD.MysqlConnector.DBConnection;

@RestController
public class EmpleadoController 
{
	private DAO<Empleado> empleadoDAO;
	
	public EmpleadoController()
	{
		empleadoDAO = new EmpleadoDAOImpl(DBConnection.getConnection());
	}
	
	@PostMapping(path="/empleados")
	public boolean saveEmpleado(@RequestBody Empleado t) 
	{			
		try 
		{
			return empleadoDAO.save(t) > 0;
		} 
		catch (DataIntegrityViolationException e) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este Empleado Ya Existe", e);
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}	
	
	@GetMapping("/empleados")		
	public List<Empleado> get()
	{		
		List<Empleado> empleados;
		try 
		{
			empleados = empleadoDAO.getAll();
			return empleados;
		} 
		catch (Exception e) 
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}		
	}
	
	@DeleteMapping("/empleados")
	public boolean deleteEmpleado(@RequestBody Empleado t)
	{
		try 
		{
			return empleadoDAO.delete(t) > 0;
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
	
	@PutMapping("/empleados")
	public boolean updateEmpleado(@RequestBody Empleado t)
	{
		try
		{
			return empleadoDAO.update(t) > 0;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
}
