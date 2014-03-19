package prueba;

import java.util.ArrayList;

import mundo.Automata;
import mundo.Equivalencia;
import mundo.Estado;

public class ejecutable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Equivalencia eq = new Equivalencia();
		
//		String[][] matriz1 = {{"0","0","1"},{"A","B,0","C,0"},{"B","C,1","D,1"},{"C","D,0","E,0"},{"D","C,1","B,1"},{"E","F,1","E,1"},{"F","G,0","C,0"},{"G","F,1","G,1"},{"H","J,1","B,0"},{"J","H,1","D,0"}};
//		for (int i = 0; i < matriz1.length; i++) {
//			String cadena = "";
//			for (int j = 0; j < matriz1[i].length; j++) {
//				cadena += matriz1[i][j]+"  ";
//			}
//			System.out.println(cadena);
//		}
//		System.out.println("-------------------------------");		
//		System.out.println("   Mealy    ");
//		System.out.println("-------------------------------");
//		
//		String[][] matriz2 = matriz1;
//		eq.inicializarMealy(matriz1, matriz2);
//		
//		ArrayList<Estado> estados = eq.getAutomata1().getEstados();
//		for (int i = 0; i < estados.size(); i++) 
//		{
//			Estado es = estados.get(i);
//			System.out.println(es.getId() + "  " + es.getTransicionA() +"    "+ es.getTransicionB());
//		}
//		System.out.println("-------------------------------");
//		ArrayList<Estado> estados1 = eq.getAutomata2().getEstados();
//		for (int i = 0; i < estados1.size(); i++) 
//		{
//			Estado es = estados1.get(i);
//			System.out.println(es.getId() + "  " + es.getTransicionA() +"    "+ es.getTransicionB());
//		}
//		
//		eq.iteracciones(eq.getAutomata1());
		
		eq = new Equivalencia();
		System.out.println("-------------------------------");
		System.out.println("   reconocedores    ");
		System.out.println("-------------------------------");
		
		String[][] matriz3 = {{"0","0","1","s"},{"A","B","A","0"},{"B","C","D","0"},{"C","E","C","0"},{"D","F","B","0"},{"E","G","E","0"},{"F","H","F","0"},{"G","I","G","0"},{"H","J","H","0"},{"I","A","K","1"},{"J","K","J","0"},{"K","A","K","1"}};
		for (int i = 0; i < matriz3.length; i++) {
			String cadena = "";
			for (int j = 0; j < matriz3[i].length; j++) {
				cadena += matriz3[i][j]+"  ";
			}
			System.out.println(cadena);
		}
		String[][] matriz4 = matriz3;
		eq.inicializarReonocedor(matriz3, matriz4);
		
		ArrayList<Estado> estados2 = eq.getAutomata1().getEstados();
		for (int i = 0; i < estados2.size(); i++) 
		{
			Estado es = estados2.get(i);
			System.out.println(es.getId() + "  " + es.getTransicionA() +"    "+ es.getTransicionB());
		}
		System.out.println("-------------------------------");
		ArrayList<Estado> estados3 = eq.getAutomata2().getEstados();
		for (int i = 0; i < estados3.size(); i++) 
		{
			Estado es = estados3.get(i);
			System.out.println(es.getId() + "  " + es.getTransicionA() +"    "+ es.getTransicionB());
		}
		
		System.out.println(eq.conexoYreducido(eq.getAutomata1()).getEstados());
		System.out.println("-----------------------");
		System.out.println("cadena diferenciadora");
		ArrayList<Estado> estados = eq.getAutomata1().getEstados();
		Estado e1=null;
		Estado e2=null;
		for (Estado estado : estados) {
			if(estado.getId().equals("A"))
				e1=estado;
			if(estado.getId().equals("B"))
				e2=estado;
			if(e1!=null && e2!=null)
				break;
		}
		
		String diferenciadora = eq.cadenaDiferenciadora(eq.getAutomata1(), e1, e2);
		System.out.println(diferenciadora);
	}

}
