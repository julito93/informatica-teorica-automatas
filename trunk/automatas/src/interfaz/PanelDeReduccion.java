package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class PanelDeReduccion extends JPanel implements ChangeListener
{
	private InterfazWB ventana;

	private JTable tablaEstados1;

	private JTable tablaEstados2;

	private JRadioButton rDBReconocedor;
	public PanelDeReduccion(InterfazWB interfaz) {
		setLayout(new BorderLayout());
		ventana = interfaz;

		JPanel panelTipo = new JPanel();
		panelTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Escoja si es un Automata de Mealy \u00F3 de Moore", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelTipo, BorderLayout.NORTH);

		rDBReconocedor = new JRadioButton("Reconocedor");
		rDBReconocedor.addChangeListener(this);
		panelTipo.setLayout(new BorderLayout());
		panelTipo.add(rDBReconocedor);

		JPanel panelTablas = new JPanel();
		panelTablas.setLayout(new BorderLayout());


		// matriz del automata 1
		JPanel bloqueMatriz1 = new JPanel();
		bloqueMatriz1.setBorder(new TitledBorder(null, "Tabla de Estados del automata 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bloqueMatriz1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrollMatriz = new JScrollPane();
		bloqueMatriz1.add(scrollMatriz);

		tablaEstados1 = new JTable();
		Object[][] data = new Object[10][3];
		tablaEstados1.setModel(new DefaultTableModel(data,new String[] {"Estado", "a", "b"}));
		scrollMatriz.setViewportView(tablaEstados1);
		panelTablas.add(bloqueMatriz1,BorderLayout.CENTER);

		//matriz del automata 2

		JPanel bloqueMatriz2 = new JPanel();
		bloqueMatriz2.setBorder(new TitledBorder(null, "Tabla de Estados del automata 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bloqueMatriz2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		scrollMatriz=new JScrollPane();
		bloqueMatriz2.add(scrollMatriz);
		tablaEstados2 = new JTable();
		data = new Object[10][3];
		tablaEstados2.setModel(new DefaultTableModel(data,new String[] {"Estado", "a", "b"}));
		scrollMatriz.setViewportView(tablaEstados2);
		panelTablas.add(bloqueMatriz2,BorderLayout.WEST);

		scrollMatriz = new JScrollPane(panelTablas);
		add(panelTablas, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton("Empezar");
		panel.add(btnNewButton);

	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if(rDBReconocedor.isSelected()==true)
		{
			Object[][] data = ventana.automata1ReducidoMoore();
			tablaEstados1.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b","salida"
			}
					));
			

			data = ventana.automata2ReducidoMoore();
			tablaEstados2.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b","salida"
			}
					));

		}
		else
		{
			Object[][] data = ventana.automata1ReducidoMealy();
			tablaEstados1.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b"
			}
					));

			data = ventana.automata2ReducidoMealy();
			tablaEstados2.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b"
			}
					));
		}
	}
}
