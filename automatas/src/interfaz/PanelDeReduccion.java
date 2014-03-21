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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;

public class PanelDeReduccion extends JPanel implements ActionListener
{
	private InterfazWB ventana;

	private JTable tablaEstados1;

	private JTable tablaEstados2;

	private JRadioButton rDBReconocedor;

	private JTextField txtEstado2;

	private JTextField txtEstado1;

	private JScrollPane scrollMatriz_1;

	private JLabel lblCadena;

	private JComboBox<String> cbAutomatas;

	public PanelDeReduccion(InterfazWB interfaz) {
		setPreferredSize(new Dimension(477, 339));
		setLayout(new BorderLayout());
		ventana = interfaz;

		JPanel panelTipo = new JPanel();
		panelTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
				"Escoja si es un Automata de Mealy \u00F3 de Moore", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelTipo, BorderLayout.NORTH);

		rDBReconocedor = new JRadioButton("Reconocedor");
		rDBReconocedor.addActionListener(this);
		panelTipo.setLayout(new BorderLayout());
		panelTipo.add(rDBReconocedor);

		JPanel panelTablas = new JPanel();


		// matriz del automata 1
		JPanel bloqueMatriz1 = new JPanel();
		bloqueMatriz1.setBorder(new TitledBorder(null, "Tabla de Estados del automata 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bloqueMatriz1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrollMatriz = new JScrollPane();
		scrollMatriz.setPreferredSize(new Dimension(176, 186));
		bloqueMatriz1.add(scrollMatriz);

		tablaEstados1 = new JTable();
		Object[][] data = new Object[10][3];
		panelTablas.setLayout(new BoxLayout(panelTablas, BoxLayout.X_AXIS));
		tablaEstados1.setModel(new DefaultTableModel(data,new String[] {"Estado", "a", "b"}));
		scrollMatriz.setViewportView(tablaEstados1);
		panelTablas.add(bloqueMatriz1);

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
		panelTablas.add(bloqueMatriz2);

		scrollMatriz = new JScrollPane(panelTablas);
		add(scrollMatriz, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Cadena Diferenciadora");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCadenaDiferenciadora(txtEstado1.getText(),txtEstado2.getText());
			}
		});
		panel_1.add(btnNewButton, BorderLayout.NORTH);

		lblCadena = new JLabel("---------");
		lblCadena.setAutoscrolls(true);
		panel_1.add(lblCadena, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);

		txtEstado2 = new JTextField();
		panel_3.add(txtEstado2);
		txtEstado2.setColumns(10);

		JLabel lblEstado = new JLabel("Estado2");
		panel_3.add(lblEstado);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);

		txtEstado1 = new JTextField();
		panel_4.add(txtEstado1);
		txtEstado1.setColumns(10);

		JLabel lblEstadoDelAutomata = new JLabel("Estado1");
		panel_4.add(lblEstadoDelAutomata);

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(100, 50));
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Escoja Automata", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		cbAutomatas = new JComboBox<String>();
		cbAutomatas.setModel(new DefaultComboBoxModel<String>(new String[] {"Automata1", "Automata2"}));
		cbAutomatas.setEditable(false);
		panel_5.add(cbAutomatas);

	}


	public void mostrarCadenaDiferenciadora(String Estado1, String Estado2) {
		// TODO Auto-generated method stub
		String automata=(String) cbAutomatas.getSelectedItem();
		String cadena= ventana.cadenaDiferenciadora(Estado1,Estado2,automata);
		lblCadena.setText(cadena.equals("")?"ambos estados deben pertenecer al automata":cadena);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
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


	public void actualizarPanel() {
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
