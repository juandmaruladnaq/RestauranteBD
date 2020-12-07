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
import uam.bd.restaurante.BD.DAOmysql.ProductoDAOImpl;
import uam.bd.restaurante.BD.Model.Producto;
import uam.bd.restaurante.BD.MysqlConnector.DBConnection;

@RestController
public class ProductoController 
{
	private DAO<Producto> productoDAO;
	
	public ProductoController()
	{
		productoDAO = new ProductoDAOImpl(DBConnection.getConnection());
	}
	
	@PostMapping(path="/productos")
	public boolean saveProducto(@RequestBody Producto t) 
	{			
		try 
		{
			return productoDAO.save(t) > 0;
		} 
		catch (DataIntegrityViolationException e) 
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este Producto Ya Existe", e);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}	
	
	@GetMapping("/productos")		
	public List<Producto> get()
	{		
		List<Producto> productos;
		try 
		{
			productos = productoDAO.getAll();
			return productos;
		} 
		catch (Exception e) 
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}		
	}
	
	@DeleteMapping("/productos")
	public boolean deleteProducto(@RequestBody Producto t)
	{
		try 
		{
			return productoDAO.delete(t) > 0;
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
	
	@PutMapping("/productos")
	public boolean updateProducto(@RequestBody Producto t)
	{
		try
		{
			return productoDAO.update(t) > 0;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
}
	

