package mundo;

public class Estado
{
	private String id;
	private Transicion transicionA;
	private Transicion transicionB;

	public Estado( String id,Transicion transicionA, Transicion transicionB)
	{
		super( );
		this.id = id;
		this.transicionA = transicionA;
		this.transicionB = transicionB;
	}
	
	public Transicion getTransicionA( )
	{
		return transicionA;
	}
	
	public Transicion getTransicionB()
	{
		return transicionB;
	}

	public String getId( )
	{
		return id;
	}
	
	public boolean equals(Object o)
	{
		Estado e = (Estado)o;
		if(e.id.equals(id))
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
//		return id +  "   " + transicionA + "     " + transicionB;
		return id ;
	}
}
