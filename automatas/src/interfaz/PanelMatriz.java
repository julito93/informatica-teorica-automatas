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

public class PanelMatriz extends JPanel implements ChangeListener{
	
	
	
	
	private InterfazWB ventana;
	
	private JTable tablaEstados1;
	
	private JTable tablaEstados2;
	
	private JRadioButton rDBReconocedor;
	
	
	
	/**
	 * Create the panel.
	 */
	public PanelMatriz(InterfazWB ventana) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTipo = new JPanel();
		panelTipo.setBorder(new TitledBorder(null, "Escoja si es un Automata de Miley o de Moore", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelTipo, BorderLayout.NORTH);
		
		rDBReconocedor = new JRadioButton("Reconocedor");
		rDBReconocedor.addChangeListener(this);
		panelTipo.setLayout(new BorderLayout(0, 0));
		
		panelTipo.add(rDBReconocedor);
		
		// matriz del automata 1
		JPanel bloqueMatriz1 = new JPanel();
		bloqueMatriz1.setBorder(new TitledBorder(null, "Tabla de Estados del automata 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(bloqueMatriz1, BorderLayout.CENTER);
		bloqueMatriz1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollMatriz = new JScrollPane();
		bloqueMatriz1.add(scrollMatriz);
		
		tablaEstados1 = new JTable();
		Object[][] data = new Object[10][3];
		tablaEstados1.setModel(new DefaultTableModel(data,new String[] {"Estado", "0", "1"}));
		scrollMatriz.setViewportView(tablaEstados1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Empezar");
		panel.add(btnNewButton);
		
		//matriz del automata 2
//		
//		JPanel bloqueMatriz2 = new JPanel();
//		bloqueMatriz2.setBorder(new TitledBorder(null, "Tabla de Estados del automata 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		add(bloqueMatriz2, BorderLayout.SOUTH);
//		bloqueMatriz2.setLayout(new BorderLayout(0, 0));
//		
//		JScrollPane scrollMatriz2 = new JScrollPane();
//		bloqueMatriz2.add(scrollMatriz2, BorderLayout.CENTER);
//		
//		tablaEstados2 = new JTable();
//		Object[][] data2 = new Object[10][3];
//		tablaEstados2.setModel(new DefaultTableModel(data2,new String[] {"Estado", "0", "1"}));
//		scrollMatriz2.setViewportView(tablaEstados2);
		
	}
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			if(rDBReconocedor.isSelected()==true)
				{
				Object[][] data = new Object[10][4];
				tablaEstados1.setModel(new DefaultTableModel(data, new String[] {
						"Estado", "0", "1","salida"
					}
				));
				}
			else
			{
				Object[][] data = new Object[10][3];
				tablaEstados1.setModel(new DefaultTableModel(data, new String[] {
						"Estado", "0", "1"
					}
				));
			}
		}

}
