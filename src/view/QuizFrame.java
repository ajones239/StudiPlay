package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import animation.AnimationPanel;
import controller.Manager;
import model.Question;
import model.Quiz;

/**
 * Main frame for taking quizzes, both practice and exam
 */
public class QuizFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentpane;						
	private AnimationPanel animationPanel;
	private JRadioButton option1;
	private JRadioButton option4;
	private JRadioButton option3;
	private JRadioButton option2;
	private Quiz quiz;
	private JButton btnNext;
	private int seconds = 0;
	private int index = 0;
	private int correctIndex = 0;
	private int scores = 0;
	private JLabel questionLbl;
	private String hint = "";
	private JButton btnHint;
	private JLabel lblTime;
	private int studentId;
	private volatile boolean execute = true;
	private JButton btnExit;
	private JButton btnPause;
	private boolean pause = false;
	private boolean practice;
	private ButtonGroup group;
	
	public QuizFrame(int id, int student) {
		this(id, student, false, 1, -1);
	}
	
	public QuizFrame(int id, int student, boolean practice, int diffMultiplier, int size) { // practice = true when in practice mode
																		// size = number of questions in quiz.
		super();
		this.practice = practice;
		this.studentId = student;
		quiz = Manager.getInstance().getQuiz(id); // connects to database, gets quiz
		seconds = quiz.getMinutes() * 60;
		setTitle(quiz.getQuizName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (practice) 						
			setBounds(100, 100, 671, 673); // need larger frame for animation panel
		else
			setBounds(100, 100, 671, 473);
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentpane.setLayout(null);
		
		JLabel lblQuiz = new JLabel("Quiz");
		lblQuiz.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuiz.setBounds(16, 6, 635, 26);
		contentpane.add(lblQuiz);
		
		JLabel lblTitle = new JLabel("Title: "+quiz.getQuizName());
		lblTitle.setBounds(26, 45, 281, 16);
		contentpane.add(lblTitle);
		
		lblTime = new JLabel("Time: "+seconds+" seconds");
		lblTime.setBounds(351, 45, 281, 16);
		contentpane.add(lblTime);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Questions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 73, 635, 319);
		contentpane.add(panel);
		panel.setLayout(null);
		
		questionLbl = new JLabel("Question:");
		questionLbl.setBounds(22, 32, 592, 64);
		panel.add(questionLbl);
		
		option1 = new JRadioButton("Option 1");
		option1.setBounds(22, 108, 592, 23);
		panel.add(option1);
		
		option2 = new JRadioButton("Option 1");
		option2.setBounds(22, 143, 592, 23);
		panel.add(option2);
		
		option3 = new JRadioButton("Option 1");
		option3.setBounds(22, 178, 592, 23);
		panel.add(option3);
		
		option4 = new JRadioButton("Option 1");
		option4.setBounds(22, 213, 592, 23);
		panel.add(option4);
		group = new ButtonGroup();
		group.add(option1);
		group.add(option2);
		group.add(option3);
		group.add(option4);
		
		btnNext = new JButton("Next");
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					// selectedIndex = selected radioButton
				int selectedIndex = option1.isSelected() ? 0 : option2.isSelected() ? 1 : option3.isSelected() ? 2 : 3;
				if(correctIndex == selectedIndex){
					if (practice) {										// if practice quiz, update animation panel
						animationPanel.correctAction(diffMultiplier);	// and use the difficulty score multiplier
						scores+= 1 * diffMultiplier;
					}
					else
						scores++; // difficulty only affects practice quiz scores
				}
				else
					if (practice)
						animationPanel.incorrectAction();		// incorrect action (wrong answer) for practice quizzes
				printNext(); // next question
				
			}
		});
		
		btnNext.setBackground(Color.WHITE);
		btnNext.setBounds(22, 254, 117, 45);
		panel.add(btnNext);
		
		btnHint = new JButton("Hint");
		
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				// show hint button
		
				JOptionPane.showMessageDialog(null, "Hint: "+hint);
				
			}
		});
		
		btnHint.setBackground(Color.WHITE);
		btnHint.setBounds(497, 259, 117, 35);
		panel.add(btnHint);
		
		btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {			// pause timer, only if not practice
			public void actionPerformed(ActionEvent e) {
				
				pause = !pause;
				if(pause && practice) {			// can only pause timer on practice mode
					execute = false;
					btnPause.setText("Continue");
					btnNext.setEnabled(false);
				} else {
					execute = true;
					btnPause.setText("Pause");
					btnNext.setEnabled(true);
					if(practice) // so multiple timers aren't started
						startTime();
				}
				
			}
		});
		btnPause.setBackground(Color.WHITE);
		btnPause.setBounds(367, 259, 117, 35);
		panel.add(btnPause);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(531, 404, 117, 29);
		contentpane.add(btnExit);
		
		setLayout(new BorderLayout());
		add(contentpane, BorderLayout.CENTER);
		if (practice) {														// adding animationpane
			animationPanel = new AnimationPanel(size, this.getWidth());
			add(animationPanel, BorderLayout.SOUTH);
		}
		printNext(); // print first question
		startTime(); // start timer
	
	}
	
	/**
	 * Starts thread for timer 
	 */
	private void startTime() {
		
		new Thread() {
			
			public void run() {
				
				while(execute) { // can be paused
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					seconds--;
					lblTime.setText("Time: "+seconds+" seconds");	// sets label
					if(seconds <= 0) {
						save();																		// save score, exit, and
						JOptionPane.showMessageDialog(null, "Sorry, looks like you ran out of time."); // have popup window
						System.exit(1);																// tell user if timer runs
						break;																		// out
					}
					
				}
				
			}
			
		}.start();
		
	}

	/**
	 * Prints next question on the screen
	 */
	private void printNext() {
	
		if(index == quiz.getSize()) {
			execute = false;
			if(studentId != -1) {
				save();
			}
			JOptionPane.showMessageDialog(null, "Quiz is Complete.\nYour Score: "+scores);	// exit when quiz is over
			dispose();
			new LoginFrame().setVisible(true);
		} else {
			
			Question question = quiz.getQuestions().get(index);
			index++;
			correctIndex = question.getCorrectIndex();
			hint = question.getHint();
			questionLbl.setText(question.getQuestion());
			option1.setText(question.getOption1());
			option2.setText(question.getOption2());
			option3.setText(question.getOption3());
			option4.setText(question.getOption4());
			group.clearSelection();
		}
		
	}
	
	/**
	 * saves score in database
	 */
	private void save() {
		
		Manager.getInstance().addScore(studentId, quiz.getId(), quiz.getSize(), scores);
		
	}
	
}