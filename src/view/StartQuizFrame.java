
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.Quiz;

/**
 * Frame for Students to enter Quiz (for exam/not pravtice mode) information 
 */
public class StartQuizFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField key;							// text field for taking quiz key
	private JComboBox<String> subjects;				// dropdown menu of Quiz subjects
	private ArrayList<Quiz> quizes = new ArrayList<>();

	/**
	 * Constructs a StartQuizFrame
	 * @param id 
	 */
	public StartQuizFrame(int id) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// window settings
		setBounds(100, 100, 450, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentFrame = new JLabel("StudiPlay - Student");	// JLabel with program name
		lblStudentFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentFrame.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblStudentFrame.setBounds(17, 16, 415, 25);
		contentPane.add(lblStudentFrame);
		
		key = new JTextField();									// JTextfield for entering key
		key.setBounds(146, 73, 270, 26);
		contentPane.add(key);
		key.setColumns(10);
		
		JLabel lblEnterKey = new JLabel("Enter Key:");			// JLabel for above textfield
		lblEnterKey.setBounds(27, 78, 96, 16);
		contentPane.add(lblEnterKey);
		
		JButton btnStartQuiz = new JButton("Start Quiz");		// button to start quiz
		btnStartQuiz.addActionListener(new ActionListener() {	// on action get quiz by key,
			public void actionPerformed(ActionEvent e) {		// start quiz, and dispose of 
																// this frame
				String keyT = key.getText();
				
				int quizId = Manager.getInstance().getQuizByKey(keyT);
				if(quizId != -1) {
				
					new QuizFrame(quizes.get(subjects.getSelectedIndex()).getId(), -1).setVisible(true);
					StartQuizFrame.this.dispose();
				
				}else {
					
					JOptionPane.showMessageDialog(null, "Error: no quiz exists with this key.", "Wrong key", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnStartQuiz.setBackground(Color.WHITE);
		btnStartQuiz.setBounds(17, 149, 117, 49);
		contentPane.add(btnStartQuiz);
		
		JButton btnExit = new JButton("Exit");			 // exit button
		btnExit.addActionListener(new ActionListener() { // on action, exit program
			public void actionPerformed(ActionEvent e) {
				exit();
				new LoginFrame().setVisible(true);
			//	System.exit(0);
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(146, 149, 117, 49);
		contentPane.add(btnExit);
		
		quizes = Manager.getInstance().getAllQuizesByPractice(0);	// fetched quizzes for dropdown menu
		subjects = new JComboBox<>();
		subjects.setBounds(144, 108, 272, 27);
		contentPane.add(subjects);
		for(Quiz quiz: quizes) {
			subjects.addItem(quiz.getCategory());
		}
		
		JLabel lblSelectSubject = new JLabel("Select Quiz:");		// JLabel for above menu
		lblSelectSubject.setBounds(30, 112, 126, 16);
		contentPane.add(lblSelectSubject);
	
	}
	
	/**
	 * Dispose this frame
	 */
	private void exit() {
		dispose();
	}
	
}