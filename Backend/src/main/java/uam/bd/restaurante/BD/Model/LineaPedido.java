package uam.bd.restaurante.BD.Model;

public class LineaPedido 
{
	private int idPedido;
	
	private Producto producto;
	
	private int cantidad;
	
	private float valorUn;
	
	public LineaPedido()
	{
		
	}
	
	public LineaPedido(int idPedido, Producto producto, int cantidad, float valorUn)
	{
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.valorUn = valorUn;
	}
	
	public LineaPedido(Producto producto, int cantidad, float valorUn)
	{		
		this.producto = producto;
		this.cantidad = cantidad;
		this.valorUn = valorUn;
	}	
	
	public int getIdPedido() 
	{
		return idPedido;
	}
	
	public void setIdPedido(int idPedido) 
	{
		this.idPedido = idPedido;
	}
	
	public Producto getProducto() 
	{
		return producto;
	}
	
	public void setProducto(Producto producto) 
	{
		this.producto = producto;
	}
	
	public int getCantidad() 
	{
		return cantidad;
	}
	
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}
	
	public float getSubTotal() 
	{		
		return this.producto.getValor()*cantidad;		
	}	
		
	public float getValorUn()
	{
		return valorUn;
	}
	
	public void setValorUnit(float valorUn) 
	{
		this.valorUn = valorUn;
	}
	
	public String getIdProducto()
	{
		return producto.getCodigo();
	}   
	
	@Override
	public String toString() 
	{
		return "\nIdPedido: "+this.getIdPedido()+"\nProducto: "+this.getProducto()+"\nCantidad : "+this.getCantidad()+"\nValorUn: "+this.getValorUn();
	}
    
}
