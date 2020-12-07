package uam.bd.restaurante.BD.Model;

public class Domiciliario extends Persona 
{	
	public Domiciliario(String cedula, String nombre, String apellidos, String email, boolean is_active, String telefono) 
	{
		super(cedula, nombre, apellidos, email, telefono, is_active);		
	}

	public String getCedula() 
	{
		return cedula;
	}
	
	public void setCedula(String cedula) 
	{
		this.cedula = cedula;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String getApellidos() 
	{
		return apellidos;
	}
	
	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getTelefono()
	{
		return telefono;
	}
	
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}	
}
