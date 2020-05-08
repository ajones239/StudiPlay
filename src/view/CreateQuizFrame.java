package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.Manager;
import model.IQuizUpdater;
import model.Question;
import javax.swing.JCheckBox;

public class CreateQuizFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField quizTitle;
	private JTextField timeLimit;
	private JTextField question;
	private JTextField option1;
	private JTextField option2;
	private JTextField option3;
	private JTextField option4;
	private JTextField hint;
	private JComboBox<String> correct;
	private JButton add;
	private JTable table;
	private JButton btnCreateQuiz;
	private JScrollPane pane;
	private ArrayList<Question> quizes;
	private JLabel lblTotalQuestions;
	private IQuizUpdater updater;
	private Manager manager;
	private int teacherId;
	private JTextField subject;
	private JLabel lblSubjects;
	private JButton btnExit;
	private JCheckBox practiceQuiz;

	public CreateQuizFrame(IQuizUpdater updater, int id) {

		setTitle("Create Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//
		this.teacherId = id;
		manager = Manager.getInstance();
		this.updater = updater;
		this.quizes = new ArrayList<>();

		addComponents();
		listeners();
		updateTable();

	}

	private void listeners() {

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String questionT = question.getText().toString();
				String option1T = option1.getText().toString();
				String option2T = option2.getText().toString();
				String option3T = option3.getText().toString();
				String option4T = option4.getText().toString();
				int correctT = correct.getSelectedIndex();
				String hintT = hint.getText().toString();

				if (questionT.isEmpty() || option1T.isEmpty() || option2T.isEmpty() || option3T.isEmpty()
						|| option4T.isEmpty() || hintT.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No any field should be empty.");
				} else {

					quizes.add(new Question(questionT, option1T, option2T, option3T, option4T, correctT, hintT));
					updateTable();
					JOptionPane.showMessageDialog(null, "Question is added in quiz");

				}

			}
		});

		btnCreateQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String quizT = quizTitle.getText().toString();
				String subjectT = subject.getText().toString();
				int minutesT = 0;
				try {
					minutesT = Integer.parseInt(timeLimit.getText());
				} 
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Time should be in number.");
					return;
				}
				if (quizT.isEmpty() || subjectT.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No any field should be empty.");
				} 
				else {
					
					String key = getKey();
					
					// adding quiz. 
					manager.addQuiz(quizT, subjectT, minutesT, key, quizes.size(), teacherId, quizes,
							practiceQuiz.isSelected());
					updater.notifyQuizUpdated();
					JOptionPane.showMessageDialog(null, "Quiz is added.");
					dispose();
					
				}

			}
		});

	}

	public String getKey() {

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(10);

		for (int i = 0; i < 10; i++) {

			int index = (int) (characters.length() * Math.random());

			sb.append(characters.charAt(index));
		}

		return sb.toString();
	}

	private void addComponents() {

		quizTitle = new JTextField();
		quizTitle.setBounds(107, 19, 160, 26);
		contentPane.add(quizTitle);
		quizTitle.setColumns(10);

		timeLimit = new JTextField();
		timeLimit.setColumns(10);
		timeLimit.setBounds(601, 19, 87, 26);
		contentPane.add(timeLimit);

		JLabel lblQuizTitle = new JLabel("Quiz Title");
		lblQuizTitle.setBounds(20, 24, 75, 16);
		contentPane.add(lblQuizTitle);

		JLabel lblTimeLimit = new JLabel("Time (minutes):");
		lblTimeLimit.setBounds(478, 24, 111, 16);
		contentPane.add(lblTimeLimit);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add Question", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 57, 668, 242);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setBounds(20, 33, 75, 16);
		panel.add(lblQuestion);

		question = new JTextField();
		question.setBounds(107, 28, 535, 26);
		panel.add(question);
		question.setColumns(10);

		option1 = new JTextField();
		option1.setBounds(107, 66, 204, 26);
		panel.add(option1);
		option1.setColumns(10);

		option2 = new JTextField();
		option2.setColumns(10);
		option2.setBounds(438, 66, 204, 26);
		panel.add(option2);

		option3 = new JTextField();
		option3.setColumns(10);
		option3.setBounds(107, 104, 204, 26);
		panel.add(option3);

		option4 = new JTextField();
		option4.setColumns(10);
		option4.setBounds(438, 104, 204, 26);
		panel.add(option4);

		hint = new JTextField();
		hint.setBounds(107, 143, 204, 26);
		panel.add(hint);
		hint.setColumns(10);

		correct = new JComboBox<>();
		correct.addItem("Option 1");
		correct.addItem("Option 2");
		correct.addItem("Option 3");
		correct.addItem("Option 4");
		correct.setBounds(438, 142, 204, 27);
		panel.add(correct);

		add = new JButton("Add");
		add.setBackground(Color.WHITE);
		add.setBounds(20, 181, 132, 43);
		panel.add(add);

		JLabel lblOption = new JLabel("Option 1:");
		lblOption.setBounds(20, 71, 75, 16);
		panel.add(lblOption);

		JLabel lblOption_1 = new JLabel("Option 3:");
		lblOption_1.setBounds(20, 109, 75, 16);
		panel.add(lblOption_1);

		JLabel lblOption_2 = new JLabel("Option 2:");
		lblOption_2.setBounds(337, 71, 75, 16);
		panel.add(lblOption_2);

		JLabel lblOption_3 = new JLabel("Option 4:");
		lblOption_3.setBounds(337, 109, 75, 16);
		panel.add(lblOption_3);

		JLabel lblCorrect = new JLabel("Correct:");
		lblCorrect.setBounds(337, 148, 75, 16);
		panel.add(lblCorrect);

		JLabel lblHint = new JLabel("Hint:");
		lblHint.setBounds(20, 148, 75, 16);
		panel.add(lblHint);

		btnCreateQuiz = new JButton("Create Quiz");
		btnCreateQuiz.setBackground(Color.WHITE);
		btnCreateQuiz.setBounds(174, 494, 180, 29);
		contentPane.add(btnCreateQuiz);

		lblTotalQuestions = new JLabel("Total Questions:");
		lblTotalQuestions.setBounds(489, 499, 194, 16);
		contentPane.add(lblTotalQuestions);
		
		subject = new JTextField();
		subject.setBounds(366, 19, 100, 26);
		contentPane.add(subject);
		subject.setColumns(10);
		
		lblSubjects = new JLabel("Subjects:");
		lblSubjects.setBounds(279, 24, 75, 16);
		contentPane.add(lblSubjects);
		
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.WHITE);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(360, 494, 117, 29);
		contentPane.add(btnExit);

		practiceQuiz = new JCheckBox("Practice Quiz");
		practiceQuiz.setBounds(20, 495, 161, 23);
		contentPane.add(practiceQuiz);

	}

	private void updateTable() {

		try {
			contentPane.remove(pane);
		} 
		catch (Exception e) {
		}

		ArrayList<String[]> data = new ArrayList<>();
		for (Question quiz : quizes) {
			data.add(quiz.getDataLine());
		}
		String[][] stringData = data.toArray(new String[][] {});
		String[] columns = new String[] { "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Correct",
				"Hint" };

		table = new JTable(stringData, columns);
		pane = new JScrollPane(table);
		table.setBounds(20, 311, 668, 171);
		pane.setBounds(20, 311, 668, 171);
		contentPane.add(pane);

		lblTotalQuestions.setText("Total Questions: " + quizes.size());

	}
}
