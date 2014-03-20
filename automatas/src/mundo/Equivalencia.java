package mundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
public class Equivalencia
{
	private Automata automata1;
	private Automata automata2;
	private boolean reconocedor;

	public Equivalencia( )
	{
		automata1 = new Automata( );
		automata2 = new Automata( );
		reconocedor = false;
	}

	public void inicializarReonocedor(String[][] matriz1, String[][] matriz2 )
	{
		guardarReconocedor1(matriz1);
		guardarReconocedor2(matriz2);
		reconocedor = true;
	}

	public void guardarReconocedor1(String[][] matriz1)
	{
		
		for ( int i = 1; i < matriz1.length; i++ )
		{
			Transicion a = new Transicion( matriz1[0][1], matriz1[i][3],  matriz1[i][1]);
			Transicion b = new Transicion( matriz1[0][2], matriz1[i][3],  matriz1[i][2]);
			automata1.addEstado( matriz1[i][0], a, b );
		}
	}
	
	public void guardarReconocedor2(String[][] matriz2)
	{
		for ( int i = 1; i < matriz2.length; i++ )
		{
			Transicion a = new Transicion( matriz2[0][1], matriz2[i][3],  matriz2[i][1]);
			Transicion b = new Transicion( matriz2[0][2], matriz2[i][3],  matriz2[i][2]);
			automata2.addEstado( matriz2[i][0], a, b );
		}
	}
	
	public void inicializarMealy(String[][] matriz1, String[][] matriz2 )
	{
		
		guardarMealy1(matriz1);
		guardarMealy2(matriz2);
				
	}

	public void guardarMealy1(String[][] matriz1)
	{
		for ( int i = 1; i < matriz1.length; i++ )
		{
			String[] array = matriz1[i][1].split( "," );
			Transicion a = new Transicion( matriz1[0][1], array[1],  array[0]);
			String[] array2 = matriz1[i][2].split( "," );
			Transicion b = new Transicion( matriz1[0][2],array2[1],  array2[0]);
			automata1.addEstado( matriz1[i][0], a, b );
		}
	}
	
	public void guardarMealy2(String[][] matriz2)
	{
		for ( int i = 1; i < matriz2.length; i++ )
		{
			String[] array = matriz2[i][1].split( "," );
			Transicion a = new Transicion( matriz2[0][1], array[1],  array[0]);
			String[] array2 = matriz2[i][2].split( "," );
			Transicion b = new Transicion( matriz2[0][2],array2[1],  array2[0]);
			automata2.addEstado( matriz2[i][0], a, b );
		}
	}
	
	
	public boolean algoritmoDeEquivalencia (Automata automata1, Automata automata2)
	{
		Automata aut1 = conexoYreducido(automata1);
		System.out.println(aut1.getEstados());
		Automata aut2 = conexoYreducido(automata2);
		System.out.println(aut2.getEstados());

		if(aut1.getEstados().size() == aut2.getEstados().size())
		{
			if(IsEquivalente(aut1, aut2) == null)
				return false;
			else
				return true;
		}
		else
			return false;
	}

	public Automata IsEquivalente(Automata automata1, Automata automata2)
	{
		ArrayList<Estado> estados1 = automata1.getEstados();
		ArrayList<Estado> estados2 = automata2.getEstados();

		if(estados1.size() == estados2.size())
		{
			Automata automata = new Automata();
			estados1.addAll(estados2);
			automata.setEstados(estados1);

			HashMap<Integer, ArrayList<ArrayList<Estado>>> iter = iteracciones(automata);		
			ArrayList<ArrayList<Estado>> valor = iter.get(iter.size()-1);
			for (int i = 0; i < valor.size(); i++) 
			{
				ArrayList<Estado> array = valor.get(i);
				if(array.size() == 2)
				{
					Estado e1 = array.get(0);
					Estado e2 = array.get(1);

					if(!(automata.getEstados().contains(e1) && automata2.getEstados().contains(e2)) || (automata.getEstados().contains(e2) && automata2.getEstados().contains(e1)))
					{
						return null;
					}
					else
					{
						if(e1.equals(automata1.getEstados().get(0)))
						{
							if(!e2.equals(automata2.getEstados().get(0)))
								return null;
						}
						if(e2.equals(automata2.getEstados().get(0)))
						{
							if(!e1.equals(automata1.getEstados().get(0)))
								return null;
						}

						if(e1.equals(automata2.getEstados().get(0)))
						{
							if(!e2.equals(automata1.getEstados().get(0)))
								return null;
						}
						if(e2.equals(automata1.getEstados().get(0)))
						{
							if(!e1.equals(automata2.getEstados().get(0)))
								return null;
						}

					}

				}
				else
				{
					return null;
				}
			}
			return automata;
		}
		return null;
	}

	public Automata conexoYreducido(Automata automata)
	{
		HashMap<Integer, ArrayList<ArrayList<Estado>>> hash = iteracciones(automata);
		return conexo(Automatareducido(hash.get(hash.size()-1), automata));

	}

	public Automata Automatareducido( ArrayList<ArrayList<Estado>> p, Automata automata)
	{

		HashMap<String, String> estadosMinimos = new HashMap<String, String>();
		ArrayList<Estado> estadosMinimosArray = new ArrayList<Estado>();
		for (int i = 0; i < p.size(); i++)
		{
			ArrayList<Estado> conjunto = p.get(i);
			Estado cabeza = conjunto.get(0);
			String estadosEliminados = "";
			for (int j = 1; j < conjunto.size(); j++) 
			{
				estadosEliminados += conjunto.get(j).getId() + " ";
			}	
			estadosMinimos.put(cabeza.getId(), estadosEliminados);
			estadosMinimosArray.add(cabeza);

		}
		Iterator iterator = estadosMinimos.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry map = (Entry) iterator.next();
			String key = (String) map.getKey();
			String valor =(String) map.getValue();
			for (int i = 0; i < estadosMinimosArray.size(); i++) 
			{
				Estado actual = estadosMinimosArray.get(i);
				Estado estadoAlcanzableA2 = automata.buscarEstado( actual.getTransicionA().getEstadoLlegada());
				Estado estadoAlcanzableB2 = automata.buscarEstado( actual.getTransicionB().getEstadoLlegada());

				if(valor.contains(estadoAlcanzableA2.getId()))
				{	
					actual.getTransicionA().setEstadoLlegada(key);
				}
				else if(valor.contains(estadoAlcanzableB2.getId()))
				{
					actual.getTransicionB().setEstadoLlegada(key);
				}
			}
		}
		Automata a = new Automata();
		a.setEstados(estadosMinimosArray);
		return a;

	}

	public ArrayList< ArrayList< Estado > > conjuntoInicial(Automata automata)
	{
		ArrayList< ArrayList< Estado > > p = new ArrayList< ArrayList<Estado> >();
		if(reconocedor)
		{
			ArrayList< Estado > c0 = new ArrayList< Estado >();
			ArrayList< Estado > c1 = new ArrayList< Estado >();
			for ( int i = 0; i < automata.getEstados( ).size( ); i++ )
			{
				Estado estadoActual = automata.getEstados( ).get( i );
				if(estadoActual.getTransicionA( ).getSalida( ).equals( "0" ))
				{
					c0.add( estadoActual );
				}
				else
				{
					c1.add( estadoActual );
				}
			}
			p.add( c0 );
			p.add( c1 );
		}
		else
		{
			ArrayList< Estado > c00 = new ArrayList< Estado >();
			ArrayList< Estado > c11 = new ArrayList< Estado >();
			ArrayList< Estado > c01 = new ArrayList< Estado >();
			ArrayList< Estado > c10 = new ArrayList< Estado >();
			for ( int i = 0; i < automata.getEstados( ).size( ); i++ )
			{
				Estado estadoActual = automata.getEstados( ).get( i );
				if(estadoActual.getTransicionA( ).getSalida( ).equals( "0" ) &&
						estadoActual.getTransicionB( ).getSalida( ).equals( "0" ))
				{
					c00.add( estadoActual );
				}
				else if(estadoActual.getTransicionA( ).getSalida( ).equals( "0" ) &&
						estadoActual.getTransicionB( ).getSalida( ).equals( "1" ))
				{
					c01.add( estadoActual );
				}
				else if(estadoActual.getTransicionA( ).getSalida( ).equals( "1" ) &&
						estadoActual.getTransicionB( ).getSalida( ).equals( "0" ))
				{
					c10.add( estadoActual );
				}
				else if(estadoActual.getTransicionA( ).getSalida( ).equals( "1" ) &&
						estadoActual.getTransicionB( ).getSalida( ).equals( "1" ))
				{
					c11.add( estadoActual );
				}
			}

			if(!c00.isEmpty( ))
			{
				p.add( c00 );
			}
			if(!c01.isEmpty( ))
			{
				p.add( c01 );
			}
			if(!c10.isEmpty( ))
			{
				p.add( c10 );
			}
			if(!c11.isEmpty( ))
			{
				p.add( c11 );
			}
		}
		return p;
	}

	public HashMap<Integer, ArrayList<ArrayList<Estado>>> iteracciones ( Automata automata)
	{
		ArrayList<ArrayList<Estado>> pi= conjuntoInicial(automata);
		ArrayList<ArrayList<Estado>> pActual = new ArrayList<ArrayList<Estado>>();
		HashMap<Integer, ArrayList<ArrayList<Estado>>> p = new HashMap<Integer, ArrayList<ArrayList<Estado>>>();

		p.put(0, pi);
		int num = 1;
		while(!pi.equals(pActual))
		{
			ArrayList<ArrayList<Estado>> intermedio = new ArrayList<ArrayList<Estado>>();
			for (int i = 0; i < pi.size(); i++) 
			{
				ArrayList<Estado> separados = new ArrayList<Estado>();
				ArrayList<Estado> conjunto = pi.get(i);

				ArrayList<Estado>[] retorno = verificarSucesoresInmediatos(automata, conjunto);
				separados = retorno[1];

				intermedio.add(retorno[0]);
				if(!separados.isEmpty())
				{
					ArrayList<Estado>[] ret = verificarSucesoresInmediatos(automata, separados);
					separados = ret[1];
					intermedio.add(ret[0]);
				}

				retorno = verificarMismoGrupo(conjunto, automata, pi, p.size());
				ArrayList<Estado> separadosGrupo = new ArrayList<Estado>();
				separadosGrupo = retorno[1];				
				if(!separadosGrupo.isEmpty())
				{
					ArrayList<Estado>[] ret = verificarSucesoresInmediatos(automata, separadosGrupo);
					separadosGrupo = ret[1];

					intermedio.remove(intermedio.size()-1);
					intermedio.add(retorno[0]);
					intermedio.add(retorno[1]);
				}

			}

			pi=intermedio;
			pActual = p.get(num-1);
			p.put(num, pi);
			num++;
		}

		return p;
	}

	public ArrayList<Estado>[] verificarMismoGrupo(ArrayList<Estado> conjunto, Automata automata, ArrayList<ArrayList<Estado>> pi, int size)
	{
		ArrayList<Estado> separados = new ArrayList<Estado>();
		ArrayList<Estado> conjuntoThis = (ArrayList<Estado>) conjunto.clone();

		if(size>1)
		{
			Estado cabeza = conjunto.get(0);
			for (int j = 1; j < conjunto.size(); j++) 
			{
				Estado actual = conjunto.get(j);

				Estado estadoAlcanzableA1 = automata.buscarEstado(cabeza.getTransicionA().getEstadoLlegada());
				Estado estadoAlcanzableA2 = automata.buscarEstado( actual.getTransicionA().getEstadoLlegada());

				Estado estadoAlcanzableB1 = automata.buscarEstado(cabeza.getTransicionB().getEstadoLlegada());
				Estado estadoAlcanzableB2 = automata.buscarEstado( actual.getTransicionB().getEstadoLlegada());

				boolean mismoGrupo= false;
				boolean encontro= false;
				int mismoGrupoA =0;
				int mismoGrupoB =0;
				for (int i = 0; i < pi.size() && !mismoGrupo && !encontro; i++) 
				{
					ArrayList<Estado> conjuntoActual = pi.get(i);
					for (int k = 0; k < conjuntoActual.size() && !encontro; k++) 
					{
						Estado e = conjuntoActual.get(k);
						if(e.getId().equals(estadoAlcanzableA1.getId()))
						{
							mismoGrupoA++;
						}
						if(e.getId().equals(estadoAlcanzableA2.getId()))
						{
							mismoGrupoA++;
						}

						if(e.getId().equals(estadoAlcanzableB1.getId()))
						{
							mismoGrupoB++;
						}
						if(e.getId().equals(estadoAlcanzableB2.getId()))
						{
							mismoGrupoB++;
						}
					}
					if((mismoGrupoA == 2) && (mismoGrupoB == 2))
					{
						mismoGrupo = true;
					}
					else if(mismoGrupoA==1)
					{
						encontro = true;
					}
					else if(mismoGrupoB==1)
					{
						encontro=true;
					}

				}

				if(!mismoGrupo)
				{
					separados.add(actual);
					conjuntoThis.remove(actual);
				}
			}

		}

		ArrayList<Estado>[] retorno = new ArrayList[2];
		retorno[0] = conjuntoThis;
		retorno[1] = separados;
		return retorno;
	}

	public ArrayList<Estado>[] verificarSucesoresInmediatos(Automata automata, ArrayList<Estado> conjunto)
	{
		ArrayList<Estado> separados = new ArrayList<Estado>();
		ArrayList<Estado> conjuntoThis = (ArrayList<Estado>) conjunto.clone();
		Estado cabeza = conjuntoThis.get(0);
		for (int j = 1; j < conjunto.size(); j++) 
		{
			Estado actual = conjunto.get(j);

			Transicion taCabezaA = cabeza.getTransicionA();
			Transicion taActualA = actual.getTransicionA();

			Estado estadoAlcanzableA1 = automata.buscarEstado(taCabezaA.getEstadoLlegada());
			Estado estadoAlcanzableA2 = automata.buscarEstado(taActualA.getEstadoLlegada());

			if(!estadoAlcanzableA1.getTransicionA().getSalida().equals(estadoAlcanzableA2.getTransicionA().getSalida()))
			{
				separados.add(actual);
				conjuntoThis.remove(actual);
			}
			else
			{
				Transicion taCabezaB = cabeza.getTransicionB();
				Transicion taActualB = actual.getTransicionB();

				Estado estadoAlcanzableB1 = automata.buscarEstado(taCabezaB.getEstadoLlegada());
				Estado estadoAlcanzableB2 = automata.buscarEstado(taActualB.getEstadoLlegada());

				if(!estadoAlcanzableB1.getTransicionB().getSalida().equals(estadoAlcanzableB2.getTransicionB().getSalida()))
				{
					separados.add(actual);
					conjuntoThis.remove(actual);
				}
			}
		}
		ArrayList<Estado>[] retorno = new ArrayList[2];
		retorno[0] = conjuntoThis;
		retorno[1] = separados;
		return retorno;
	}

	/**
	 * retorna una cadena diferenciadora entre dos estados de un automata dado
	 * @param M automata del que se busca la cadena diferenciadora - !=null
	 * @param E1 Estado que se busca diferenciar - !=null
	 * @param E2 Estado que se busca diferenciar - !=null
	 * @return Si E1 y E2 son equivalentes, retorna una cadena vacia.
	 */
	public String cadenaDiferenciadora(Automata M, Estado E1, Estado E2)
	{
		String cadena ="";
		HashMap<Integer,ArrayList<ArrayList<Estado>>> particiones =iteracciones(M);
		int indice=particiones.size()-2;
		ArrayList<ArrayList<Estado>> pi = particiones.get(indice);
		boolean equivalentes = false;
		for (int i=0; i< pi.size() && !equivalentes;i++) {
			ArrayList<Estado> iEquivalentes = pi.get(i);
			if(iEquivalentes.contains(E1)&& iEquivalentes.contains(E2))
				equivalentes=true;
		}
		if(!equivalentes)
			cadena = cadenaDiferenciadoraRecursivo(particiones,E1,E2,cadena,indice);
		return cadena;
	}
	private String cadenaDiferenciadoraRecursivo(HashMap<Integer,ArrayList<ArrayList<Estado>>> particiones,
			Estado e1, Estado e2, String cadena,int longitud) {
		// TODO Auto-generated method stub
		if(longitud>0)
		{
			ArrayList<ArrayList<Estado>> Pi = particiones.get(longitud-1);
			String idSucesor1A = e1.getTransicionA().getEstadoLlegada();
			String idSucesor2A = e2.getTransicionA().getEstadoLlegada();
			Estado sucesor1 = new Estado(idSucesor1A, null, null);
			Estado sucesor2 = new Estado(idSucesor2A, null, null);
			boolean sucesoresIequivalentes=false;
			for (int i = 0; i < Pi.size() && !sucesoresIequivalentes; i++) {
				sucesoresIequivalentes = Pi.get(i).contains(sucesor1)&&Pi.get(i).contains(sucesor2);
			}
			if(!sucesoresIequivalentes)
			{
				boolean encontro=false;
				for (int i = 0; i < Pi.size()&& !encontro;i++)
				{
					ArrayList<Estado> equivalentes= Pi.get(i);
					for (int j = 0; j < equivalentes.size(); j++) {
						if(equivalentes.get(j).equals(sucesor1))
						{
							sucesor1=equivalentes.get(j);
							encontro =true;
							break;
						}
					}
				}
				encontro=false;
				for (int i = 0; i < Pi.size()&& !encontro;i++)
				{
					ArrayList<Estado> equivalentes= Pi.get(i);
					for (int j = 0; j < equivalentes.size(); j++) {
						if(equivalentes.get(j).equals(sucesor2))
						{
							sucesor2=equivalentes.get(j);
							encontro =true;
							break;
						}
					}
				} 
				cadena+=e1.getTransicionA().getEntrada();
				return cadenaDiferenciadoraRecursivo(particiones, sucesor1, sucesor2, cadena, --longitud);
			}
			else
			{
				cadena+=e1.getTransicionB().getEntrada();
				String idSucesor1B = e1.getTransicionB().getEstadoLlegada();
				String idSucesor2B = e2.getTransicionB().getEstadoLlegada();
				sucesor1 = new Estado(idSucesor1B, null, null);
				sucesor2 = new Estado(idSucesor2B, null, null);
				boolean encontro=false;
				for (int i = 0; i < Pi.size()&& !encontro;i++)
				{
					ArrayList<Estado> equivalentes= Pi.get(i);
					for (int j = 0; j < equivalentes.size(); j++) {
						if(equivalentes.get(j).equals(sucesor1))
						{
							sucesor1=equivalentes.get(j);
							encontro =true;
							break;
						}
					}
				}
				encontro=false;
				for (int i = 0; i < Pi.size()&& !encontro;i++)
				{
					ArrayList<Estado> equivalentes= Pi.get(i);
					for (int j = 0; j < equivalentes.size(); j++) {
						if(equivalentes.get(j).equals(sucesor2))
						{
							sucesor2=equivalentes.get(j);
							encontro =true;
							break;
						}
					}
				} 
				return cadenaDiferenciadoraRecursivo(particiones, sucesor1, sucesor2, cadena, --longitud);
			}
		}
		else if(!reconocedor&& longitud==0)
		{
			if(!( e1.getTransicionA().getSalida().equals(e2.getTransicionA().getSalida())))	
			{
				return cadena+=e1.getTransicionA().getEntrada();	
			}
			else
			{
				return cadena+=e1.getTransicionB().getEntrada();
			}
		}
		else
			return cadena;
	}
	/**
	 * retorna la parte conexa de un automata
	 * @param automata
	 * @return
	 */
	public Automata conexo(Automata automata)
	{
		ArrayList<Estado> estados = automata.getEstados();
		ArrayList<Estado> recorrido = recoridoProfundidad(automata);

		for(int i=0; i<estados.size();i++)
		{
			Estado estado = estados.get(i);
			if(!recorrido.contains(estado))
				estados.remove(estado);
		}
		return automata;
	}

	/**
	 * retorna el recorrido en profundidad desde el estado inicial del automata
	 * @param automata maquina de la que se busca el recorrido - !=null
	 * @return arreglo con los estados del automata en recorrido en profundidad
	 */
	public ArrayList<Estado> recoridoProfundidad(Automata automata)
	{
		ArrayList<Estado> estados = automata.getEstados();
		ArrayList<Estado> visitados = new ArrayList<Estado>();
		Stack<Estado> pila = new Stack<Estado>();
		pila.push(estados.get(0));

		while(!pila.empty())
		{
			Estado actual =pila.pop();

			if(!visitados.contains(actual))
			{
				visitados.add(actual);
				if(actual.getTransicionA()!=null)
				{
					Estado sucesorA= automata.buscarEstado(actual.getTransicionA().getEstadoLlegada());
					if(!visitados.contains(sucesorA))
						pila.push(sucesorA);
				}
				if(actual.getTransicionB()!=null)
				{
					Estado sucesorB= automata.buscarEstado(actual.getTransicionB().getEstadoLlegada());
					if(!visitados.contains(sucesorB))
						pila.push(sucesorB);
				}
			}
		}
		return visitados;
	}
	public Automata getAutomata1() {
		return automata1;
	}

	public Automata getAutomata2() {
		return automata2;
	}

	

}