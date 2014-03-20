package interfaz;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;

public class PanelMatriz extends JPanel implements ActionListener{




	private InterfazWB ventana;

	private JTable tablaEstados1;

	private JTable tablaEstados2;

	private JButton cambiarTipo;

	private JScrollPane scrollMatriz_1;

	private boolean reconocedor;


	/**
	 * Create the panel.
	 */
	public PanelMatriz(InterfazWB ventana) {
		this.ventana=ventana;
		reconocedor=false;
		setLayout(new BorderLayout(0, 0));

		JPanel panelTipo = new JPanel();
		panelTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Escoja si es un Automata de Mealy \u00F3 de Moore", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelTipo, BorderLayout.NORTH);

		cambiarTipo = new JButton("Cambiar tipo");
		cambiarTipo.addActionListener(this);
		cambiarTipo.setActionCommand("cambiar tipo");
		panelTipo.setLayout(new FlowLayout());
		panelTipo.add(cambiarTipo);



		JPanel panelTablas = new JPanel();
		panelTablas.setLayout(new BorderLayout(0,0));


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
		scrollMatriz_1=new JScrollPane();
		bloqueMatriz2.add(scrollMatriz_1);
		tablaEstados2 = new JTable();
		data = new Object[10][3];
		tablaEstados2.setModel(new DefaultTableModel(data,
				new String[] {
				"Estado", "a", "b"
		}
				));
		scrollMatriz_1.setViewportView(tablaEstados2);
		panelTablas.add(bloqueMatriz2,BorderLayout.WEST);

		scrollMatriz = new JScrollPane(panelTablas);
		add(scrollMatriz, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btGuardar2 = new JButton("guardar2");
		panel.add(btGuardar2);
		btGuardar2.addActionListener(this);
		btGuardar2.setActionCommand("guardar2");


		JButton btnGuardar1 = new JButton("guardar1");
		btnGuardar1.addActionListener(this);
		btnGuardar1.setActionCommand("guardar1");
		panel.add(btnGuardar1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("cambiar tipo"))
		{
			cambiartipoAutomata();
		}
		else if(cmd.equals("guardar1"))
		{
			guardarAutomata1();
		}
		else
		{
			guardarAutomata2();
		}
	}

	private void guardarAutomata2() {
		// TODO Auto-generated method stub
		String[][] automata2;
		if(reconocedor)
		{
			automata2= new String[10][4];

			for (int i = 0; i < automata2.length; i++) {
				for (int j = 0; j < automata2[0].length; j++) {
					automata2[i][j]=(String)tablaEstados2.getModel().getValueAt(i, j);
				}
			}
			ventana.actualizarReconocedor2(automata2);
		}
		else
		{
			automata2= new String[10][3];

			for (int i = 0; i < automata2.length; i++) {
				for (int j = 0; j < automata2[0].length; j++) {
					automata2[i][j]=(String)tablaEstados2.getModel().getValueAt(i, j);
				}
			}
			ventana.actualizarMeley2(automata2);
		}
	}

	private void guardarAutomata1() {
		// TODO Auto-generated method stub
		String[][] automata1;
		if(reconocedor)
		{
			automata1= new String[10][4];

			for (int i = 0; i < automata1.length; i++) {
				for (int j = 0; j < automata1[0].length; j++) {
					automata1[i][j]=(String)tablaEstados1.getModel().getValueAt(i, j);
				}
			}
			ventana.actualizarReconocedor1(automata1);
		}
		else
		{
			automata1= new String[10][3];

			for (int i = 0; i < automata1.length; i++) {
				for (int j = 0; j < automata1[0].length; j++) {
					automata1[i][j]=(String)tablaEstados1.getModel().getValueAt(i, j);
				}
			}
			ventana.actualizarMeley1(automata1);
		}
	}

	private void cambiartipoAutomata() {
		// TODO Auto-generated method stub
		if(!reconocedor)
		{
			reconocedor=true;
			Object[][] data = new Object[10][4];
			tablaEstados1.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b","salida"
			}));

			data = new Object[10][4];
			tablaEstados2.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b","salida"
			}
					));
		}
		else
		{
			reconocedor=false;
			Object[][] data = new Object[10][3];
			tablaEstados1.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b"
			}));

			data = new Object[10][3];
			tablaEstados2.setModel(new DefaultTableModel(data, new String[] {
					"Estado", "a", "b"
			}));
		}
	}

}
