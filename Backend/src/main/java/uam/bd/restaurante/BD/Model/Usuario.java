package uam.bd.restaurante.BD.Model;

public class Usuario extends Empleado
{
	private String password;

	public Usuario(String cedula, String nombre, String apellidos, String email, String telefono, boolean is_active,  String password) 
	{
		super(cedula, nombre, apellidos, email, telefono, is_active);		
		this.password = password;
	}
	
	public Usuario(String cedula, String password)
	{
		super(cedula);		
		this.password = password;
	}
	
	public Usuario()
	{
		super();
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

}
