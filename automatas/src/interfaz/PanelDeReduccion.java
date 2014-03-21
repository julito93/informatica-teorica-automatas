package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

public class PanelDeReduccion extends JPanel implements ChangeListener
{
	private InterfazWB ventana;

	private JTable tablaEstados1;

	private JTable tablaEstados2;

	private JRadioButton rDBReconocedor;
	private JTextField textField_1;
	private JTextField textField;
	private JScrollPane scrollMatriz_1;
	public PanelDeReduccion(InterfazWB interfaz) {
		setPreferredSize(new Dimension(448, 339));
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
		scrollMatriz.setPreferredSize(new Dimension(176, 186));
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
		scrollMatriz_1=new JScrollPane();
		scrollMatriz_1.setPreferredSize(new Dimension(176, 186));
		bloqueMatriz2.add(scrollMatriz_1);
		tablaEstados2 = new JTable();
		data = new Object[10][3];
		tablaEstados2.setModel(new DefaultTableModel(data,new String[] {"Estado", "a", "b"}));
		scrollMatriz_1.setViewportView(tablaEstados2);
		panelTablas.add(bloqueMatriz2,BorderLayout.WEST);

		scrollMatriz = new JScrollPane(panelTablas);
		add(scrollMatriz, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Cadena Diferenciadora");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado2");
		panel_3.add(lblEstado);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		JLabel lblEstadoDelAutomata = new JLabel("Estado1");
		panel_4.add(lblEstadoDelAutomata);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Escoja si  son del automata2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_5, BorderLayout.EAST);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Automata2");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(rdbtnNewRadioButton);

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
