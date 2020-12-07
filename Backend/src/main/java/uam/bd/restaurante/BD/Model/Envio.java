package uam.bd.restaurante.BD.Model;

import java.sql.Timestamp;

public class Envio 
{
	private int numero;
	
	private Timestamp fecha;
	
	private Domiciliario domiciliario;

	public Envio(int numero, Timestamp fecha, Domiciliario domiciliario) 
	{
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.domiciliario = domiciliario;
	}

	public int getNumero() 
	{
		return numero;
	}

	public void setNumero(int numero) 
	{
		this.numero = numero;
	}

	public Timestamp getFecha() 
	{
		return fecha;
	}

	public void setFecha(Timestamp fecha) 
	{
		this.fecha = fecha;
	}

	public Domiciliario getDomiciliario() 
	{
		return domiciliario;
	}

	public void setDomiciliario(Domiciliario domiciliario) 
	{
		this.domiciliario = domiciliario;
	}	
}
