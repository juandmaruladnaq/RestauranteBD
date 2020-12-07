package uam.bd.restaurante.BD.Model;

public class Producto
{
	private String codigo;
	
	private String nombre;
	
	private String descripcion;
	
	private boolean isActive;
	
	private float valor;
	
	public Producto()
	{
		
	}
	
	public Producto(String codigo, String nombre, String descripcion, float valor, boolean isActive)
	{
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.isActive= isActive;
	}
	
	public Producto(String codigo)
	{
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) 
	{
		this.codigo = codigo;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	
	public float getValor() 
	{
		return valor;
	}
	
	public void setValor(float valor) 
	{
		this.valor = valor;
	}
	
	public boolean isActive() 
	{
		return isActive;
	}
	
	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
	}	
	
	@Override
	public String toString() 
	{
		return "\nCodigo: "+this.getCodigo()+"\nNombre : "+this.getNombre()+"\nValor: "+this.getValor();
	}
}
