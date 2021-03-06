package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.Quiz;

/**
 * JFrame for starting a practice quiz
 * @author user
 *
 */
public class QuizPracticeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> subjects;
	private ArrayList<Quiz> quizes = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public QuizPracticeFrame() {
		
		setTitle("StudiPlay");										// window config
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 396, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectSubject = new JLabel("Select Subject:");	// Select subject JLabel
		lblSelectSubject.setBounds(41, 45, 109, 16);
		contentPane.add(lblSelectSubject);
		
		JButton btnStartPracticeQuiz = new JButton("Start Practice Quiz");	// Start quiz button
		btnStartPracticeQuiz.addActionListener(new ActionListener() {		// Starts appropriate quiz with 
			public void actionPerformed(ActionEvent e) {					// appropriate difficulty
				int diffMultiplier;
				String[] difficulty = ((String) subjects.getSelectedItem()).split(" ");
				switch (difficulty[difficulty.length-1]) {	// uses difficulty for score multiplier
				case "Easy": 
					diffMultiplier = 1; 
					break;
				case "Medium": 
					diffMultiplier = 2; 
					break;
				case "Hard": 
					diffMultiplier = 3; 
					break;
				default: 
					diffMultiplier = 1;
				}
				new QuizFrame(quizes.get(subjects.getSelectedIndex()).getId(), 
						-1, 
						true, 
						diffMultiplier,
						quizes.get(subjects.getSelectedIndex()).getSize()
						).setVisible(true);
				QuizPracticeFrame.this.dispose();
				
			}
		});
		btnStartPracticeQuiz.setBounds(41, 101, 169, 52);
		contentPane.add(btnStartPracticeQuiz);
		
		quizes = Manager.getInstance().getAllQuizesByPractice(1);	// create quiz selection dropdown menu 
		subjects = new JComboBox<>();
		subjects.setBounds(162, 41, 169, 27);
		contentPane.add(subjects);
		for(Quiz quiz: quizes) {
			subjects.addItem(quiz.getCategory());
		}
		
	}
	
}
