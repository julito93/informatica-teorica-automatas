package mundo;

public class Transicion
{
	private String entrada;
	private String salida;
	private String estadoLlegada;
	
	
	public Transicion( String entrada, String salida, String estadoLlegada )
	{
		super( );
		this.entrada = entrada;
		this.salida = salida;
		this.estadoLlegada = estadoLlegada;
	}
	
	public String getEntrada( )
	{
		return entrada;
	}
	
	public String getSalida( )
	{
		return salida;
	}
	
	public String getEstadoLlegada( )
	{
		return estadoLlegada;
	}
	
	
	public void setEstadoLlegada(String estadoLlegada) {
		this.estadoLlegada = estadoLlegada;
	}

	public String toString()
	{
		return entrada + " " + salida + "  " + estadoLlegada;
	}
	
}
