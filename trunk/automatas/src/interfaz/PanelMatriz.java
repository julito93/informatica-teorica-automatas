package interfaz;

import javax.swing.JOptionPane;
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
import java.awt.Dimension;

public class PanelMatriz extends JPanel implements ActionListener{

	private InterfazWB ventana;

	private JTable tablaEstados1;

	private JTable tablaEstados2;

	private JRadioButton cambiarTipo;

	private JScrollPane scrollMatriz_1;

	private boolean reconocedor;

	private JButton btnGuardar1;

	private JButton btGuardar2;

	private JButton btGuardar3;

	private String[][] dataMealy1;

	private String[][] dataMealy2;

	private String[][] dataMoore1;

	private String[][] dataMoore2;


	/**
	 * Create the panel.
	 */
	public PanelMatriz(InterfazWB ventana) {
		setPreferredSize(new Dimension(448, 339));
		this.ventana=ventana;
		reconocedor=false;
		setLayout(new BorderLayout(0, 0));

		JPanel panelTipo = new JPanel();
		panelTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Escoja si es un Automata de Mealy \u00F3 de Moore", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelTipo, BorderLayout.NORTH);

		cambiarTipo = new JRadioButton("Es reconocedor");
		cambiarTipo.addActionListener(this);
		panelTipo.setLayout(new BorderLayout(0, 0));
		cambiarTipo.setActionCommand("cambiar tipo");
		panelTipo.add(cambiarTipo);
		JPanel panelTablas = new JPanel();
		// matriz del automata 1
		JPanel bloqueMatriz1 = new JPanel();
		bloqueMatriz1.setBorder(new TitledBorder(null, "Tabla de Estados del automata 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bloqueMatriz1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrollMatriz = new JScrollPane();
		scrollMatriz.setPreferredSize(new Dimension(176, 186));
		bloqueMatriz1.add(scrollMatriz);

		tablaEstados1 = new JTable();
		dataMealy1 = new String[10][3];

		dataMealy1[0][0] = "A";
		dataMealy1[1][0] = "B";
		dataMealy1[2][0] = "C";
		dataMealy1[3][0] = "D";
		dataMealy1[4][0] = "E";
		dataMealy1[5][0] = "F";
		dataMealy1[6][0] = "G";
		dataMealy1[7][0] = "H";
		dataMealy1[8][0] = "I";
		dataMealy1[9][0] = "J";
		panelTablas.setLayout(new BoxLayout(panelTablas, BoxLayout.X_AXIS));
		tablaEstados1.setModel(new DefaultTableModel(dataMealy1,new String[] {"Estado", "a", "b"}){
			boolean[] columnEditables = new boolean[] {
					false, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
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
		dataMealy2 = new String[10][3];
		dataMealy2[0][0] = "K";
		dataMealy2[1][0] = "L";
		dataMealy2[2][0] = "M";
		dataMealy2[3][0] = "N";
		dataMealy2[4][0] = "O";
		dataMealy2[5][0] = "P";
		dataMealy2[6][0] = "Q";
		dataMealy2[7][0] = "R";
		dataMealy2[8][0] = "S";
		dataMealy2[9][0] = "T";
		tablaEstados2.setModel(new DefaultTableModel(dataMealy2,
				new String[] {
						"Estado", "a", "b"
		}
				){
					boolean[] columnEditables = new boolean[] {
							false, true, true
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}});
		scrollMatriz_1.setViewportView(tablaEstados2);
		panelTablas.add(bloqueMatriz2);

		scrollMatriz = new JScrollPane(panelTablas);
		add(scrollMatriz, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


		btnGuardar1 = new JButton("guardar1");
		btnGuardar1.addActionListener(this);
		btnGuardar1.setActionCommand("guardar1");
		panel.add(btnGuardar1);

		btGuardar2 = new JButton("guardar2");
		panel.add(btGuardar2);
		btGuardar2.addActionListener(this);
		btGuardar2.setActionCommand("guardar2");

		btGuardar3 = new JButton("Equivalencia");
		panel.add(btGuardar3);
		btGuardar3.addActionListener(this);
		btGuardar3.setActionCommand("equivalencia");



		dataMoore1 =  new String[10][4];

		dataMoore1[0][0] = "A";
		dataMoore1[1][0] = "B";
		dataMoore1[2][0] = "C";
		dataMoore1[3][0] = "D";
		dataMoore1[4][0] = "E";
		dataMoore1[5][0] = "F";
		dataMoore1[6][0] = "G";
		dataMoore1[7][0] = "H";
		dataMoore1[8][0] = "I";
		dataMoore1[9][0] = "J";

		dataMoore2 = new String[10][4];
		dataMoore2[0][0] = "K";
		dataMoore2[1][0] = "L";
		dataMoore2[2][0] = "M";
		dataMoore2[3][0] = "N";
		dataMoore2[4][0] = "O";
		dataMoore2[5][0] = "P";
		dataMoore2[6][0] = "Q";
		dataMoore2[7][0] = "R";
		dataMoore2[8][0] = "S";
		dataMoore2[9][0] = "T";

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
		else if(cmd.equals("guardar2"))
			{
				guardarAutomata2();
			}
		else
			{
				ventana.equivalencia();
			}
	}

	private void guardarAutomata2() {
		// TODO Auto-generated method stub
		String[][] automata2;
		boolean continua= false;
		if(reconocedor)
			{
				automata2= new String[10][4];

				for (int i = 0; i < automata2.length && !continua; i++) {
					for (int j = 0; j < automata2[0].length && !continua; j++) {
											
						String value = (String)tablaEstados2.getModel().getValueAt(i, j);
						if(!value.equals(""))
							if(j==0)
								{
									String aux = (String)tablaEstados2.getModel().getValueAt(i, j+1);
									if(aux != null )
										if(!aux.equals(""))
											automata2[i][j]=value;
										else
											JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
									else
										continua = true;
								}
							else
								automata2[i][j]=value;
						else
							continua=true;
						
						//					dataMoore2[i][j]= (String) tablaEstados2.getModel().getValueAt(i, j);
					}
				}
				ventana.actualizarReconocedor2(automata2);
			}
		else
			{
				automata2= new String[10][3];
				for (int i = 0; i < automata2.length && !continua; i++) 
					{
						for (int j = 0; j < automata2[0].length && !continua; j++) 
							{
								
								
								String value = (String)tablaEstados2.getModel().getValueAt(i, j);
								if(!value.equals(""))
									if(j==0)
										{
											String aux = (String)tablaEstados2.getModel().getValueAt(i, j+1);
											if(aux != null )
												if(!aux.equals(""))
													automata2[i][j]=value;
												else
													JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
											else
												continua = true;
										}
									else
										automata2[i][j]=value;
								else
									continua=true;
								
								
								//					dataMealy2[i][j]= (String) tablaEstados2.getModel().getValueAt(i, j);
							}
					}
				ventana.actualizarMeley2(automata2);
			}
	}

	private void guardarAutomata1() {
		// TODO Auto-generated method stub
		String[][] automata1;
		boolean continua = false;
		if(reconocedor)
			{
				automata1= new String[10][4];

				for (int i = 0; i < automata1.length && !continua; i++) {
					for (int j = 0; j < automata1[0].length && !continua; j++) 
						{
						String value = (String)tablaEstados1.getModel().getValueAt(i, j);
						if(!value.equals(""))
							if(j==0)
								{
									String aux = (String)tablaEstados1.getModel().getValueAt(i, j+1);
									if(aux != null )
										if(!aux.equals(""))
											automata1[i][j]=value;
										else
											JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
									else
										continua = true;
								}
							else
								automata1[i][j]=value;
						else
							continua=true;

						//					dataMoore1[i][j]= (String) tablaEstados1.getModel().getValueAt(i, j);
					}
				}
				ventana.actualizarReconocedor1(automata1);
			}
		else
			{
				automata1= new String[10][3];

				for (int i = 0; i < automata1.length && !continua; i++) {
					for (int j = 0; j < automata1[0].length && !continua; j++) {
						
						String value = (String)tablaEstados1.getModel().getValueAt(i, j);
						if(!value.equals(""))
							if(j==0)
								{
									String aux = (String)tablaEstados1.getModel().getValueAt(i, j+1);
									if(aux != null )
										if(!aux.equals(""))
											automata1[i][j]=value;
										else
											JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
									else
										continua = true;
								}
							else
								automata1[i][j]=value;
						else
							continua=true;
						
						//					dataMealy1[i][j]= (String) tablaEstados1.getModel().getValueAt(i, j);
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
				tablaEstados1.setModel(new DefaultTableModel(dataMealy1, new String[] {
						"Estado", "a", "b","salida"
				}));

				tablaEstados2.setModel(new DefaultTableModel(dataMealy2, new String[] {
						"Estado", "a", "b","salida"
				}
						));
			}
		else
			{
				reconocedor=false;
				tablaEstados1.setModel(new DefaultTableModel(dataMoore1, new String[] {
						"Estado", "a", "b"
				}));

				tablaEstados2.setModel(new DefaultTableModel(dataMoore2, new String[] {
						"Estado", "a", "b"
				}));
			}
	}

}
