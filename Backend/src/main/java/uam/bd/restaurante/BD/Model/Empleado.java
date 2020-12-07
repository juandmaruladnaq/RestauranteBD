package uam.bd.restaurante.BD.Model;

public class Empleado extends Persona 
{

	public Empleado(String cedula, String nombre, String apellidos, String email, String telefono, boolean is_active) 
	{
		super(cedula, nombre, apellidos, email, telefono, is_active);
	}
	
	public Empleado(String cedula)
	{
		super(cedula);
	}
	
	public Empleado( )
	{
		super();
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
	
	public void setApellido(String apellido)
	{
		this.apellidos = apellido;
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
