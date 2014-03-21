package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import mundo.Automata;
import mundo.Equivalencia;
import mundo.Estado;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

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
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
//					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazWB() {
		setPreferredSize(new Dimension(480, 339));

		equivalencia = new Equivalencia();

		setTitle("Automatas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		panelMatriz = new PanelMatriz(this);
		panelMatriz.setPreferredSize(new Dimension(110, 18));
		tabbedPane.addTab("Tablas de estados", null, panelMatriz, null);

		panelReduccion = new PanelDeReduccion(this);
		tabbedPane.addTab("Reducci\u00F3n de automatas", null, panelReduccion,
				null);

	}

	public Object[][] automata1ReducidoMealy() {
		Automata automata = equivalencia.conexoYreducido(equivalencia
				.getAutomata1());
		Object[][] data = new Object[automata.getEstados().size()][3];
		for (int i = 0; i < automata.getEstados().size(); i++) {
			Estado e = automata.getEstados().get(i);
			data[i][0] = e.getId();
			data[i][1] = e.getTransicionA().getEstadoLlegada() + ","
					+ e.getTransicionA().getSalida();
			data[i][2] = e.getTransicionB().getEstadoLlegada() + ","
					+ e.getTransicionA().getSalida();
		}

		return data;
	}

	public Object[][] automata2ReducidoMealy() {
		Automata automata = equivalencia.conexoYreducido(equivalencia
				.getAutomata2());
		Object[][] data = new Object[automata.getEstados().size()][3];
		for (int i = 0; i < automata.getEstados().size(); i++) {
			Estado e = automata.getEstados().get(i);
			data[i][0] = e.getId();
			data[i][1] = e.getTransicionA().getEstadoLlegada() + ","
					+ e.getTransicionA().getSalida();
			data[i][2] = e.getTransicionB().getEstadoLlegada() + ","
					+ e.getTransicionA().getSalida();
		}

		return data;
	}

	public Object[][] automata1ReducidoMoore() {
		Automata automata = equivalencia.conexoYreducido(equivalencia
				.getAutomata1());
		Object[][] data = new Object[automata.getEstados().size()][4];
		for (int i = 0; i < automata.getEstados().size(); i++) {
			Estado e = automata.getEstados().get(i);
			data[i][0] = e.getId();
			data[i][1] = e.getTransicionA().getEstadoLlegada();
			data[i][2] = e.getTransicionB().getEstadoLlegada();
			data[i][3] = e.getTransicionA().getSalida();
		}

		return data;
	}

	public Object[][] automata2ReducidoMoore() {
		Automata automata = equivalencia.conexoYreducido(equivalencia
				.getAutomata2());
		Object[][] data = new Object[automata.getEstados().size()][4];
		for (int i = 0; i < automata.getEstados().size(); i++) {
			Estado e = automata.getEstados().get(i);
			data[i][0] = e.getId();
			data[i][1] = e.getTransicionA().getEstadoLlegada();
			data[i][2] = e.getTransicionB().getEstadoLlegada();
			data[i][3] = e.getTransicionA().getSalida();
		}

		return data;
	}

	public void actualizarReconocedor1(String[][] automata1) {
		// TODO Auto-generated method stub
		if (equivalencia.getAutomata1().getEstados().isEmpty()) {
			equivalencia.getAutomata1().getEstados().clear();

			equivalencia.guardarReconocedor1(automata1);
			JOptionPane.showMessageDialog(this, "Guardado");

		} else {
			int respuesta = JOptionPane.showConfirmDialog(this,
					"esta seguro que desea sobreescribir el automata guardado",
					"Precaución", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				equivalencia.getAutomata1().getEstados().clear();

				equivalencia.guardarReconocedor1(automata1);
				JOptionPane.showMessageDialog(this, "Guardado");
			}
		}

	}

	public void actualizarMeley1(String[][] automata1) {
		// TODO Auto-generated method stub
		if (equivalencia.getAutomata1().getEstados().isEmpty()) {
			equivalencia.getAutomata1().getEstados().clear();

			equivalencia.guardarMealy1(automata1);
			JOptionPane.showMessageDialog(this, "Guardado");

		} else {
			int respuesta = JOptionPane.showConfirmDialog(this,
					"esta seguro que desea sobreescribir el automata guardado",
					"Precaución", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				equivalencia.getAutomata1().getEstados().clear();

				equivalencia.guardarMealy1(automata1);
				JOptionPane.showMessageDialog(this, "Guardado");

			}
		}
	}

	public void actualizarReconocedor2(String[][] automata2) {
		// TODO Auto-generated method stub
		if (equivalencia.getAutomata2().getEstados().isEmpty()) {
			equivalencia.getAutomata2().getEstados().clear();

			equivalencia.guardarReconocedor2(automata2);
			JOptionPane.showMessageDialog(this, "Guardado");

		} else {
			int respuesta = JOptionPane.showConfirmDialog(this,
					"esta seguro que desea sobreescribir el automata guardado",
					"Precaución", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				equivalencia.getAutomata2().getEstados().clear();

				equivalencia.guardarReconocedor2(automata2);
				JOptionPane.showMessageDialog(this, "Guardado");

			}
		}
	}

	public void actualizarMeley2(String[][] automata2) {
		// TODO Auto-generated method stub
		if (equivalencia.getAutomata2().getEstados().isEmpty()) {
			equivalencia.getAutomata2().getEstados().clear();

			equivalencia.guardarMealy2(automata2);
			JOptionPane.showMessageDialog(this, "Guardado");

		} else {
			int respuesta = JOptionPane.showConfirmDialog(this,
					"esta seguro que desea sobreescribir el automata guardado",
					"Precaución", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				equivalencia.getAutomata2().getEstados().clear();

				equivalencia.guardarMealy2(automata2);
				JOptionPane.showMessageDialog(this, "Guardado");
			}
		}
	}

	public String cadenaDiferenciadora(String estado1, String estado2,
			String automata) {
		// TODO Auto-generated method stub
		String cadena="";	
		Automata seleccionado = automata.equals("Automata2")? equivalencia.getAutomata2():equivalencia.getAutomata1();
		if(seleccionado.buscarEstado(estado1)!=null && seleccionado.buscarEstado(estado2)!=null)
		{
			cadena = equivalencia.cadenaDiferenciadora(seleccionado, seleccionado.buscarEstado(estado1), seleccionado.buscarEstado(estado2));
		}
		return cadena;
	}
	public void actualizarPanel() {
		panelReduccion.actualizarPanel();
		
	}

	public void equivalencia() {
		boolean eq = equivalencia.algoritmoDeEquivalencia(equivalencia.getAutomata1(), equivalencia.getAutomata2());
		if(eq == true)
			JOptionPane.showMessageDialog(null, "Los automatas no son equivalentes");
		else
			JOptionPane.showMessageDialog(null, "Los automatas son equivalentes");
		
	}
}
