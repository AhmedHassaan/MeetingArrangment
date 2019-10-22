import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class MainForm {

	private JFrame frame;
	public static DBConnect dbconnect;
	JComboBox<String> cboxClientName;
	JComboBox<String> cboxTime;
	JComboBox<String> cboxDate;
	ArrayList<Client> clients;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		dbconnect = new DBConnect();
		clients = new ArrayList<>();
		initialize();
		fillClientsName();
		fillFreeDate();
		cboxDate.setSelectedIndex(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 614, 458);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelMain = new JPanel();
		tabbedPane.addTab("Main", null, panelMain, null);
		panelMain.setLayout(null);
		
		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddClientForm clientform = new AddClientForm();
				clientform.setVisible(true);
			}
		});
		btnAddClient.setBounds(10, 396, 89, 23);
		panelMain.add(btnAddClient);
		
		cboxClientName = new JComboBox<String>();
		cboxClientName.setBounds(79, 16, 172, 23);
		panelMain.add(cboxClientName);
		
		JLabel lblClients = new JLabel("Clients");
		lblClients.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClients.setBounds(10, 11, 59, 29);
		panelMain.add(lblClients);
		
		cboxDate = new JComboBox<String>();
		cboxDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillFreeTime(cboxDate.getSelectedItem().toString());
			}
		});
		cboxDate.setBounds(376, 21, 186, 23);
		panelMain.add(cboxDate);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(307, 16, 59, 29);
		panelMain.add(lblDate);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(307, 66, 59, 29);
		panelMain.add(lblTime);
		
		cboxTime = new JComboBox<String>();
		cboxTime.setBounds(376, 71, 186, 23);
		panelMain.add(cboxTime);
		
		JPanel panelReport = new JPanel();
		tabbedPane.addTab("Report", null, panelReport, null);
	}
	
	public void fillClientsName() {
		clients.clear();
		clients = dbconnect.getClients();
		for(Client cc:clients) {
//			System.out.println(cc.getId() + "  " + cc.getName() + "  " + cc.getEmail());
			cboxClientName.addItem(cc.getName());
		}
	}
	
	public void fillFreeDate() {
		ArrayList<String> arr = dbconnect.getFreeDate();
		cboxDate.setModel(new DefaultComboBoxModel(arr.toArray()));
//		ArrayList<String> date = new ArrayList<>();
//		for(DateTime d:freeDate) {
//			if(!date.contains(d.getDate())) {
//				cboxDate.addItem(d.getDate());
//				date.add(d.getDate());
//			}
//			cboxTime.addItem(d.getTime());
//		}
	}
	
	public void fillFreeTime(String date) {
		ArrayList<String> arr = dbconnect.getFreeTime(date);
		cboxTime.setModel(new DefaultComboBoxModel(arr.toArray()));
	}
	
	public void refresh() {
		fillClientsName();
		fillFreeDate();
	}
	
}
