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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;

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
		panelTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Escoja si es un Automata de Mealy \u00F3 de Moore", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelTipo, BorderLayout.NORTH);
		
		rDBReconocedor = new JRadioButton("Reconocedor");
		rDBReconocedor.addChangeListener(this);
		panelTipo.setLayout(new BorderLayout(0, 0));
		panelTipo.add(rDBReconocedor);
		
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
		tablaEstados1.setModel(new DefaultTableModel(data,new String[] {"Estado", "0", "1"}));
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
		tablaEstados2.setModel(new DefaultTableModel(data,new String[] {"Estado", "0", "1"}));
		scrollMatriz.setViewportView(tablaEstados2);
		panelTablas.add(bloqueMatriz2,BorderLayout.WEST);
		
		scrollMatriz = new JScrollPane(panelTablas);
		add(scrollMatriz, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Empezar");
		panel.add(btnNewButton);
		
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
				
				data = new Object[10][4];
				tablaEstados2.setModel(new DefaultTableModel(data, new String[] {
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
				
				data = new Object[10][3];
				tablaEstados2.setModel(new DefaultTableModel(data, new String[] {
						"Estado", "0", "1"
					}
				));
			}
		}

}
