package interfaz;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import mundo.Automata;
import mundo.Equivalencia;
import mundo.Estado;

import com.jgoodies.forms.factories.DefaultComponentFactory;


public class InterfazWB extends JFrame {

	private JPanel contentPane;
	private PanelMatriz panelMatriz;
	private PanelDeReduccion panelReduccion;
	private Equivalencia equivalencia;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazWB frame = new InterfazWB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazWB() {
		
		equivalencia = new Equivalencia();
		
		setTitle("Automatas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		panelMatriz = new PanelMatriz((InterfazWB) null);
		tabbedPane.addTab("New tab", null, panelMatriz, null);
		
		panelReduccion = new PanelDeReduccion(this);
		tabbedPane.addTab("Reducci\u00F3n de automatas", null, panelReduccion, null);
		
		
		
		equivalencia = new Equivalencia();
		System.out.println("-------------------------------");
		System.out.println("   reconocedores    ");
		System.out.println("-------------------------------");
		
		String[][] matriz3 = {
				{"0","0","1","s"},
				{"A","B","A","0"},
				{"B","C","D","0"},
				{"C","E","C","0"},
				{"D","F","B","0"},
				{"E","G","E","0"},
				{"F","H","F","0"},
				{"G","I","G","0"},
				{"H","J","H","0"},
				{"I","A","K","1"},
				{"J","K","J","0"},
				{"K","A","K","1"}
				};

		String[][] matriz4 = {
				{"0","0","1","s"},
				{"L","M","L","0"},
				{"M","N","M","0"},
				{"N","O","N","0"},
				{"O","P","O","0"},
				{"P","Q","P","0"},
				{"Q","M","Q","1"}
		};
		for (int i = 0; i < matriz4.length; i++) {
			String cadena = "";
			for (int j = 0; j < matriz4[i].length; j++) {
				cadena += matriz4[i][j]+"  ";
			}
		}
		equivalencia.inicializarReonocedor(matriz3, matriz4);	
	}
	
	public Object[][] automata1ReducidoMealy()
	{
		Automata automata = equivalencia.conexoYreducido(equivalencia.getAutomata1());
		Object[][] data = new Object[automata.getEstados().size()][3];
		for (int i = 0; i < automata.getEstados().size(); i++)
		{
			Estado e = automata.getEstados().get(i);
			data[i][0]= e.getId();
			data[i][1]= e.getTransicionA().getEstadoLlegada()+","+e.getTransicionA().getSalida();
			data[i][2]= e.getTransicionB().getEstadoLlegada()+","+e.getTransicionA().getSalida();
		}
		
		return data;
	}
	public Object[][] automata2ReducidoMealy()
	{
		Automata automata = equivalencia.conexoYreducido(equivalencia.getAutomata2());
		Object[][] data = new Object[automata.getEstados().size()][3];
		for (int i = 0; i < automata.getEstados().size(); i++)
		{
			Estado e = automata.getEstados().get(i);
			data[i][0]= e.getId();
			data[i][1]= e.getTransicionA().getEstadoLlegada()+","+e.getTransicionA().getSalida();
			data[i][2]= e.getTransicionB().getEstadoLlegada()+","+e.getTransicionA().getSalida();
		}
		
		return data;
	}
	public Object[][] automata1ReducidoMoore()
	{
		Automata automata = equivalencia.conexoYreducido(equivalencia.getAutomata1());
		Object[][] data = new Object[automata.getEstados().size()][4];
		for (int i = 0; i < automata.getEstados().size(); i++)
		{
			Estado e = automata.getEstados().get(i);
			data[i][0]= e.getId();
			data[i][1]= e.getTransicionA().getEstadoLlegada();
			data[i][2]= e.getTransicionB().getEstadoLlegada();
			data[i][3]= e.getTransicionA().getSalida();
		}
		
		return data;
	}
	public Object[][] automata2ReducidoMoore()
	{
		Automata automata = equivalencia.conexoYreducido(equivalencia.getAutomata2());
		Object[][] data = new Object[automata.getEstados().size()][4];
		for (int i = 0; i < automata.getEstados().size(); i++)
		{
			Estado e = automata.getEstados().get(i);
			data[i][0]= e.getId();
			data[i][1]= e.getTransicionA().getEstadoLlegada();
			data[i][2]= e.getTransicionB().getEstadoLlegada();
			data[i][3]= e.getTransicionA().getSalida();
		}
		
		return data;
	}

}
