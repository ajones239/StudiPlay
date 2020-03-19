package frames;

import java.awt.Color;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.Manager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField key;

	public StudentFrame(int id) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentFrame = new JLabel("Student Frame");
		lblStudentFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentFrame.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblStudentFrame.setBounds(17, 16, 415, 25);
		contentPane.add(lblStudentFrame);
		
		key = new JTextField();
		key.setBounds(136, 95, 282, 26);
		contentPane.add(key);
		key.setColumns(10);
		
		JLabel lblEnterKey = new JLabel("Enter Key:");
		lblEnterKey.setBounds(28, 100, 96, 16);
		contentPane.add(lblEnterKey);
		
		JButton btnStartQuiz = new JButton("Start Quiz");
		btnStartQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String keyT = key.getText();
				int quizId = Manager.getInstance().getQuizByKey(keyT);
				if(quizId == -1) {
					JOptionPane.showMessageDialog(null, "Quiz Key is not correct.");
				}else {
					
					//
					new StartQuizFrame(quizId, id).setVisible(true);
					
				}
				
			}
		});
		btnStartQuiz.setBackground(Color.WHITE);
		btnStartQuiz.setBounds(17, 149, 117, 49);
		contentPane.add(btnStartQuiz);
	
	}

}
