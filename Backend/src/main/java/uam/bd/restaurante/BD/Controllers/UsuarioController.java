package uam.bd.restaurante.BD.Controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import uam.bd.restaurante.BD.JwtTokenService;
import uam.bd.restaurante.BD.DAO.DAO;
import uam.bd.restaurante.BD.DAOmysql.UsuarioDAOImpl;
import uam.bd.restaurante.BD.Model.Usuario;
import uam.bd.restaurante.BD.MysqlConnector.DBConnection;
import uam.bd.restaurante.BD.dto.JToken;

@RestController
public class UsuarioController 
{
	private DAO<Usuario> usuarioDAO;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsuarioController(BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		usuarioDAO = new UsuarioDAOImpl(DBConnection.getConnection());
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}	
	
	@PostMapping(path="/usuarios")
	public boolean saveUsuario(@RequestBody Usuario t) 
	{			
		try 
		{
			t.setPassword(bCryptPasswordEncoder.encode(t.getPassword()));
			return usuarioDAO.save(t) > 0;
		} 
		catch(SQLIntegrityConstraintViolationException e)
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esta Cedula No existe", e);
		}
		catch (DataIntegrityViolationException e) 
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este Usuario Ya Existe", e);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}	
	
	@GetMapping("/usuarios")		
	public List<Usuario> get()
	{		
		List<Usuario> usuarios;
		try 
		{
			usuarios = usuarioDAO.getAll();
			return usuarios;
		} 
		catch (Exception e) 
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}		
	}
	
	@DeleteMapping("/usuarios")
	public boolean deleteUsuario(@RequestBody Usuario t)
	{
		try 
		{
			return usuarioDAO.delete(t) > 0;
		} 
		catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
	
	@PutMapping("/usuarios")
	public boolean updateUsuario(@RequestBody Usuario t)
	{
		try
		{
			return usuarioDAO.update(t) > 0;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}
	}
	
	@PostMapping("/login")		
	public JToken login(@RequestBody Usuario t) throws Exception
	{
		System.out.println("Userrrrrr:");
		System.out.println("User T: "+t.getCedula());
		try 
		{
			Usuario user = usuarioDAO.getBy(t.getCedula());
			System.out.println("Password: "+t.getPassword());
			boolean match = bCryptPasswordEncoder.matches(t.getPassword(), user.getPassword());
			if(match)
			{
				String token = JwtTokenService.generateToken(t);
				System.out.println("Token: "+token);
				return new JToken(token);
				//return token;
			}
			else
			{
				//return null;
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password incorrecto !");
			}
				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password incorrecto", e);
		}		
	}
	
	@PostMapping("/getuser")		
	public String getUser(HttpServletRequest request)
	{
		String token = request.getHeader("Authorization");
		System.out.println(token);
		try 
		{
			JwtTokenService.verifyToken(token);
			Claims c = JwtTokenService.getClaimsFromToken(token);
			System.out.println("Claims");
			System.out.println(c);
			return c.getId();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Exception ", e);
		}		
	}
}