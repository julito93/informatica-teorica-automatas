package interfaz;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;


public class PanelSumaDirecta extends JPanel {

	private InterfazWB ventana;
	/**
	 * Create the panel.
	 */
	public PanelSumaDirecta(InterfazWB principal) {
		
		ventana=principal;
		setBorder(new TitledBorder(null, "Suma Directa y Equivalencia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Suma directa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 21, 231, 226);
		add(panel);
		
		JLabel lblEquivalentes = new JLabel("Equivalentes:");
		lblEquivalentes.setBounds(251, 21, 68, 14);
		add(lblEquivalentes);
		
		JLabel lblEquivalencia = new JLabel("----------");
		lblEquivalencia.setLabelFor(lblEquivalentes);
		lblEquivalencia.setBounds(329, 21, 46, 14);
		add(lblEquivalencia);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{211, 0};
		gbl_panel.rowHeights = new int[]{184, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollTabla = new JScrollPane();
		GridBagConstraints gbc_scrollTabla = new GridBagConstraints();
		gbc_scrollTabla.fill = GridBagConstraints.BOTH;
		gbc_scrollTabla.gridx = 0;
		gbc_scrollTabla.gridy = 0;
		panel.add(scrollTabla, gbc_scrollTabla);
		
		JTextPane textPane = new JTextPane();
		scrollTabla.setViewportView(textPane);
		
		
	}
	
	public void mostrarAutomatasEquivalentes(String automata)
	{
	 
	}
	
	
}
