import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddClientForm extends JFrame {

	private static AddClientForm frame;
	private JPanel contentPane;
	private JTextField fieldName;
	private JTextField fieldEmail;
	private JTextField fieldNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddClientForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddClientForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fieldName = new JTextField();
		fieldName.setBounds(163, 25, 211, 28);
		contentPane.add(fieldName);
		fieldName.setColumns(10);
		
		JLabel lblClientName = new JLabel("Client Name : *");
		lblClientName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientName.setLabelFor(fieldName);
		lblClientName.setBounds(22, 36, 113, 14);
		contentPane.add(lblClientName);
		
		JLabel lblClientEmail = new JLabel("Client Email :");
		lblClientEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientEmail.setBounds(22, 167, 113, 14);
		contentPane.add(lblClientEmail);
		
		fieldEmail = new JTextField();
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(163, 156, 211, 28);
		contentPane.add(fieldEmail);
		
		JLabel lblClientNumber = new JLabel("Client Number : *");
		lblClientNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientNumber.setBounds(22, 100, 131, 14);
		contentPane.add(lblClientNumber);
		
		fieldNum = new JTextField();
		fieldNum.setColumns(10);
		fieldNum.setBounds(163, 89, 211, 28);
		contentPane.add(fieldNum);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldName.getText().isEmpty() || fieldNum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill Stared Field first","Warning",JOptionPane.WARNING_MESSAGE);
				}else {
					String name = fieldName.getText();
					String email = fieldEmail.getText();
					String num = fieldNum.getText();
					int n = JOptionPane.showConfirmDialog(null, "Add '"+name+"' to Clients database ?","Confirmation",JOptionPane.YES_NO_OPTION);
					if(n==JOptionPane.YES_OPTION)
						MainForm.connectionData.addClient(name, email, num);
				}
			}
		});
		btnAdd.setBounds(166, 239, 99, 48);
		contentPane.add(btnAdd);
	}
}
