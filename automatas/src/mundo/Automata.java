package mundo;

import java.util.ArrayList;

public class Automata
{
	private ArrayList< Estado > estados;

	public Automata( )
	{
		estados = new ArrayList< Estado >( 10 );
	}

	public void addEstado( String id, Transicion a, Transicion b )
	{
		Estado e = new Estado( id, a, b );
		estados.add( e );
	}

	public Estado buscarEstado (String id)
	{
		Estado estadoEncontrado = null;
		boolean encontrado = false;
		for ( int i = 0; i < estados.size( ) && !encontrado; i++ )
		{
			Estado actual = estados.get( i );
			if(actual.getId( ).equals( id ))
			{
				estadoEncontrado = actual;
				encontrado = true;
			}
		}
		return estadoEncontrado;
	}
	
	public void eliminarEstado(String id)
	{
		Estado estadoEncontrado = buscarEstado(id);
		estados.remove(estadoEncontrado);
	}

	public ArrayList< Estado > getEstados( )
	{
		return estados;
	}

	public void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}
	
	
}
