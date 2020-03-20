package view;

import java.awt.Color;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import database.Manager;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginUsername;
	private JPasswordField loginPassword;
	private JComboBox<String> loginType;
	private JTextField regName;
	private JTextField regUsername;
	private JPasswordField regPassword;
	private JButton btnRegister;
	private JComboBox<String> regType;
	private JButton btnLogin;
	private Manager manager;
	
	public LoginFrame() {
		
		setTitle("Quiz Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.manager = Manager.getInstance();
		
		// /...
		addComponents();
		listeners();

	}
	
	private void addComponents() {
		
		JLabel lblQuizProgram = new JLabel("Quiz Program");
		lblQuizProgram.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblQuizProgram.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizProgram.setBounds(28, 6, 485, 34);
		contentPane.add(lblQuizProgram);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		loginPanel.setBounds(17, 53, 248, 223);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		loginType = new JComboBox<>();
		loginType.addItem("STUDENT");
		loginType.addItem("TEACHER");
		loginType.setBounds(105, 41, 125, 27);
		loginPanel.add(loginType);
		
		loginUsername = new JTextField();
		loginUsername.setBounds(100, 80, 130, 26);
		loginPanel.add(loginUsername);
		loginUsername.setColumns(10);
		
		loginPassword = new JPasswordField();
		loginPassword.setBounds(100, 118, 130, 26);
		loginPanel.add(loginPassword);
		loginPassword.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setBounds(6, 45, 87, 16);
		loginPanel.add(lblType);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(6, 85, 87, 16);
		loginPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(6, 123, 87, 16);
		loginPanel.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(16, 156, 117, 47);
		loginPanel.add(btnLogin);
		
		JPanel regPanel = new JPanel();
		regPanel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		regPanel.setBounds(277, 52, 248, 261);
		contentPane.add(regPanel);
		regPanel.setLayout(null);
		
		regType = new JComboBox<>();
		regType.addItem("STUDENT");
		regType.addItem("TEACHER");
		regType.setBounds(106, 37, 125, 27);
		regPanel.add(regType);
		
		regName = new JTextField();
		regName.setColumns(10);
		regName.setBounds(101, 76, 130, 26);
		regPanel.add(regName);
		
		regUsername = new JTextField();
		regUsername.setColumns(10);
		regUsername.setBounds(101, 114, 130, 26);
		regPanel.add(regUsername);
		
		regPassword = new JPasswordField();
		regPassword.setColumns(10);
		regPassword.setBounds(101, 152, 130, 26);
		regPanel.add(regPassword);
		
		btnRegister = new JButton("Register");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setBounds(16, 198, 117, 47);
		regPanel.add(btnRegister);
		
		JLabel lblRegType = new JLabel("Type:");
		lblRegType.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegType.setBounds(7, 41, 87, 16);
		regPanel.add(lblRegType);
		
		JLabel lblRegUsername = new JLabel("Username:");
		lblRegUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegUsername.setBounds(7, 119, 87, 16);
		regPanel.add(lblRegUsername);
		
		JLabel lblRegPassword = new JLabel("Password:");
		lblRegPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegPassword.setBounds(7, 157, 87, 16);
		regPanel.add(lblRegPassword);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(7, 81, 87, 16);
		regPanel.add(lblName);
		
	}
	
	private void listeners() {
		
		btnLogin.addActionListener(action -> {
			
			String userType = loginType.getSelectedItem().toString();
			String username = loginUsername.getText().toString();
			String password = String.valueOf(loginPassword.getPassword());
			
			if(username.isEmpty() || password.isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "No any field should be empty.");
				
			} else {
				
				int id = manager.login(userType, username, password);
				if(id != -1) {

					/// move ahead.
					if(userType.equals("TEACHER")) {
						new TeacherFrame(id).setVisible(true);
					}else {
						new StudentFrame(id).setVisible(true);
					}
					dispose();
					
				} else {

					JOptionPane.showMessageDialog(null, "Username or Password is incorrect.");
					
				}
				
			}
			
		});
		
		btnRegister.addActionListener(action -> {
			
			String userType = regType.getSelectedItem().toString();
			String name = regName.getText().toString();
			String username = regUsername.getText().toString();
			String password = String.valueOf(regPassword.getPassword());
			
			if(username.isEmpty() || password.isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "No any field should be empty.");
				
			} 
			else {
				
				if(manager.isExists(userType, username)) {
					JOptionPane.showMessageDialog(null, "Username "+username+" is already exists.");
				}else {
					manager.register(userType, name, username, password);
					JOptionPane.showMessageDialog(null, "User registered successfully.");
					clear();
				}
				
			}
			
		});
		
	}
	
	private void clear() {

		loginType.setSelectedIndex(0);		
		regType.setSelectedIndex(0);
		regName.setText("");
		regUsername.setText("");
		regPassword.setText("");
		loginUsername.setText("");
		loginPassword.setText("");
		
	}
	
	
	
}
