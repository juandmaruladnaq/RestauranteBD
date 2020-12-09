package uam.bd.restaurante.BD.Model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Envio 
{
	private int numero;
	
	private Timestamp fecha;
	
	private Domiciliario domiciliario;
	
	private ArrayList<Pedido> pedidos;   

	public Envio(int numero, Timestamp fecha, Domiciliario domiciliario) 
	{		
		this.numero = numero;
		this.fecha = fecha;
		this.domiciliario = domiciliario;
		this.pedidos = new ArrayList<Pedido>();
	}
	
	public Envio(Domiciliario domiciliario)
	{		
		this.domiciliario = domiciliario;
	}
	
	public Envio()
	{
		
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

	public ArrayList<Pedido> getPedidos() 
	{
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) 
	{
		this.pedidos = pedidos;
	}	
	
	public void addPedido(int numero)
	{
		this.pedidos.add(new Pedido(numero));
	}
	
	public void addPedido(Pedido p)
	{
		this.pedidos.add(p);
	}
	
	
}
