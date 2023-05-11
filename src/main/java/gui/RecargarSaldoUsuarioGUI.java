package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecargarSaldoUsuarioGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSaldo;
	private JLabel lblSaldoActual, lblAnadir;
	private JButton btnAnadir, btnSalir;
	private double saldo;
	private BLFacade bl = LoginGUI.getBusinessLogic();

	public RecargarSaldoUsuarioGUI(Usuario u) {
		saldo = u.getSaldo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 234, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSaldoActual = new JLabel();
		lblSaldoActual.setText(String.format("Saldo actual: %f",saldo));
		lblSaldoActual.setBounds(10, 11, 198, 14);
		contentPane.add(lblSaldoActual);
		
		lblAnadir = new JLabel("Añadir saldo");
		lblAnadir.setBounds(10, 39, 68, 14);
		contentPane.add(lblAnadir);
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setBounds(77, 36, 131, 20);
		contentPane.add(textFieldSaldo);
		textFieldSaldo.setColumns(10);

		btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bl.addSaldo(u,Double.parseDouble(textFieldSaldo.getText()));
				saldo = u.getSaldo();
				lblSaldoActual.setText(String.format("Saldo actual: %f",saldo));
			}
		});
		btnAnadir.setBounds(10, 116, 89, 23);
		contentPane.add(btnAnadir);
		
		
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuarioGUI ventana = new MenuUsuarioGUI(u);
				ventana.setBussinessLogic(bl);
				ventana.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(119, 116, 89, 23);
		contentPane.add(btnSalir);
	}
}
