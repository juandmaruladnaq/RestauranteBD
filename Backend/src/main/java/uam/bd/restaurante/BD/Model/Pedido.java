package uam.bd.restaurante.BD.Model;

import java.util.ArrayList;
import java.sql.Timestamp;

public class Pedido 
{	
	private int numero;
     
    private Timestamp fecha;
     
    private Cliente cliente;    
     
    private Usuario usuario;
     
    private Estado estado;
    
    private Envio envio;
     
    private ArrayList<LineaPedido> productos;        
     
	public Pedido(int numero, Timestamp fecha, Cliente cliente, Usuario usuario, Estado estado)
	{
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		this.usuario = usuario;
		this.estado = estado;
		this.productos = new ArrayList<LineaPedido>();
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
	
	public Cliente getCliente() 
	{
		return cliente;
	}
	
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	
	public Estado getEstado() 
	{
		return estado;
	}
	
	public void setEstado(Estado estado) 
	{
		this.estado = estado;
	}
	
	public Usuario getUsuario() 
	{
		return usuario;
	}
	
	public void setEmpleado(Usuario empleado) 
	{
		this.usuario = empleado;
	}
	
	public void adicionarProducto(LineaPedido e)
	{		
		this.productos.add(e);	
	}
	
    public Envio getEnvio() 
    {
		return envio;
	}

	public void setEnvio(Envio envio) 
	{
		this.envio = envio;
	}

	public ArrayList<LineaPedido> getProductos() 
	{
		return productos;
	}		
    
    public float getTotal() 
    {  	
    	return 0;
    }
	
}
