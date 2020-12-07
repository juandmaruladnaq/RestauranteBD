package uam.bd.restaurante.BD.Model;

public class Estado 
{
	private int numero;
	
	private String nombre;
	
	public static final int EN_PREPARACION = 1;
	
	public static final int EN_REPARTO = 2;
	
	public static final int ENTREGADO = 3;
	
	public static final int CANCELADO = 4;
	
	public Estado(int numero, String nombre)
	{
		this.numero = numero;
		this.nombre = nombre;
	}

	public int getNumero() 
	{
		return numero;
	}

	public void setNumero(int numero) 
	{
		this.numero = numero;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}	
}
