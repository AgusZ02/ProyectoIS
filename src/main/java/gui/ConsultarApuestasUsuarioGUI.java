package gui;

import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Apuesta;
import domain.Usuario;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarApuestasUsuarioGUI extends JFrame {
	private BLFacade bl = LoginGUI.getBusinessLogic();
	private JPanel contentPane;
	private JTable table = new JTable();
	private DefaultTableModel tableModel = new DefaultTableModel();
	private String[] columnNames = new String[] {ResourceBundle.getBundle("Etiquetas").getString("strID"),
			ResourceBundle.getBundle("Etiquetas").getString("strEvento"),
			ResourceBundle.getBundle("Etiquetas").getString("strPregunta"),
			ResourceBundle.getBundle("Etiquetas").getString("strPronostico"),
			ResourceBundle.getBundle("Etiquetas").getString("strCantidad"),
			ResourceBundle.getBundle("Etiquetas").getString("strFinalizado"),
			ResourceBundle.getBundle("Etiquetas").getString("Multip"),
			ResourceBundle.getBundle("Etiquetas").getString("gananciaEsperada")
	};
	private Vector<Apuesta> vector;
	private JScrollPane scrollPane = new JScrollPane();
	private JButton btnVolver;

	public ConsultarApuestasUsuarioGUI(Usuario u) {
		vector = bl.getApuestasFrom(u);
		
		tableModel.setDataVector(null, columnNames);
		tableModel.setColumnCount(9); // another column added to allocate ev objects
		for (Apuesta a : vector) {
			Vector<Object> row = new Vector<Object>();
			//COL 0 = IDAPUESTA, COL1 = EVENTO, COL2 = PREGUNTA, COL3 = PRONOSTICO, COL4 = CANTIDAD, COL5 = FINALIZADO, COL6 = APUESTA
			row.add(a.getBetNumber());
			row.add(a.getPronostico().getPregunta().getEvent().getDescription());
			row.add(a.getPronostico().getPregunta().toString());
			row.add(a.getPronostico().toString());
			row.add(a.getBet());
			row.add(a.getPronostico().isFinalizado());
			row.add(a.getPronostico().getCuotaGanancia());
			double ganancia = a.getBet()*a.getPronostico().getCuotaGanancia(); //ganancia en caso de acierto
			row.add(String.format("%.2f", ganancia)); //redondea a 2 decimales
			row.add(a);
			tableModel.addRow(row);

		}
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(240);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setPreferredWidth(75);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(8)); // not
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 930, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		scrollPane.setBounds(20, 20, 875, 200);
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null);
		contentPane.setLayout(null);

		contentPane.add(scrollPane);
		
		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("lblSalir"));
		btnVolver.setBounds(388, 226, 137, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuarioGUI ventana = new MenuUsuarioGUI(u);
				ventana.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVolver);
	}
	
	public void setBussinessLogic(BLFacade b){
		this.setBussinessLogic(b);
	}
}
