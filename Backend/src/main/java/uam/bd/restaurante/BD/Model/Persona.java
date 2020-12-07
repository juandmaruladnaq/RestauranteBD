package uam.bd.restaurante.BD.Model;

public abstract class Persona
{	
	protected String cedula;
	protected String nombre;
	protected String apellidos;
	protected String email;
	protected String telefono;
	protected boolean is_active;
	
	public Persona(String cedula, String nombre, String apellidos, String email, String telefono, boolean is_active)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.is_active = is_active;
	}
	
	public Persona(String cedula)
	{
		this.cedula = cedula;
	}
	
	public Persona( )
	{		
	}
}
