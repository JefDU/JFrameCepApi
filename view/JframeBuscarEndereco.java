package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.BuscarEnderecoController;
import controller.exception.BuscarEnderecoException;

public class JframeBuscarEndereco extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private BuscarEnderecoController controller;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeBuscarEndereco frame = new JframeBuscarEndereco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	public JframeBuscarEndereco(){
		controller = new BuscarEnderecoController();
		setResizable(false);
		setTitle("Buscar Endereço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 384);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFormattedTextField txtCep = new JFormattedTextField();
		txtCep.setFont(new Font("Arial Black", Font.PLAIN, 14));
		txtCep.setBounds(135, 69, 89, 20);
		contentPane.add(txtCep);
		
		
		JTextArea listarEnderecoCep = new JTextArea();
		listarEnderecoCep.setEditable(false);
		listarEnderecoCep.setToolTipText("Informações do CEP");
		listarEnderecoCep.setForeground(Color.BLACK);
		listarEnderecoCep.setBackground(Color.WHITE);
		listarEnderecoCep.setFont(new Font("Arial", Font.BOLD, 16));
		listarEnderecoCep.setBounds(54, 192, 216, 128);
		contentPane.add(listarEnderecoCep);
		
		JLabel lblCep = new JLabel("CEP: ");
		lblCep.setForeground(new Color(214, 3, 9));
		lblCep.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblCep.setToolTipText("Cep");
		lblCep.setBounds(92, 70, 48, 18);
		contentPane.add(lblCep);
		
		JLabel lblTitulo = new JLabel("API CEP ");
		lblTitulo.setEnabled(false);
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblTitulo.setBounds(116, 1, 96, 57);
		contentPane.add(lblTitulo);
		try {
			controller.mascaraCep(txtCep);
		} catch (BuscarEnderecoException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
		JButton btnBuscarCep = new JButton("Buscar Endereço");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listarEnderecoCep.setText(controller.buscarEndereco(txtCep.getText()));
					txtCep.setText("");
	                txtCep.requestFocus();
	                
				} catch (BuscarEnderecoException e1) {
					JOptionPane.showMessageDialog(btnBuscarCep, e1.getMessage());
					txtCep.setText("");
					txtCep.requestFocus();
				}
			}
		});
		btnBuscarCep.setBackground(new Color(255, 255, 255));
		btnBuscarCep.setForeground(new Color(255, 0, 0));
		btnBuscarCep.setFont(new Font("Arial", Font.BOLD, 14));
		btnBuscarCep.setBounds(54, 115, 216, 43);
		contentPane.add(btnBuscarCep);
		
	}
}
