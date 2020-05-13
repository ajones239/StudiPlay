package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.IQuizUpdater;
import model.Quiz;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This JFrame is the window for teacher operations.
 */
public class TeacherFrame extends JFrame implements IQuizUpdater {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane quizPane, studentPane;
	private JTable quizTable;
	private JTable studentData;
	private JButton btnCreateQuiz;
	private JButton btnShowStudentsData;
	private JButton btnDeleteQuiz;
	private Manager manager;
	private int teacherId;
	private ArrayList<Quiz> quizes;
	private JButton btnExit;

	/**
	 * Constructor 
	 * @param id user id for logged in teacher
	 */
	public TeacherFrame(int id) {
		
		setTitle("Teacher Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.teacherId = id;
		this.manager = Manager.getInstance();
		addComponents();
		listeners();
		updateQuizTable();
		updateStudentTable(-1);
		
	}
	
	/**
	 * Adds JLabels and buttons to contentPane, and contentPane to this frame
	 */
	private void addComponents() {
		
		JLabel lblTeacherFrame = new JLabel("StudiPlay - Teacher");
		lblTeacherFrame.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTeacherFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherFrame.setBounds(23, 6, 613, 34);
		contentPane.add(lblTeacherFrame);
		
		btnCreateQuiz = new JButton("Create Quiz");
		btnCreateQuiz.setBackground(Color.WHITE);
		btnCreateQuiz.setBounds(20, 52, 117, 29);
		contentPane.add(btnCreateQuiz);
		
		btnDeleteQuiz = new JButton("Delete Quiz");
		btnDeleteQuiz.setBackground(Color.white);
		btnDeleteQuiz.setBounds(530, 52, 117, 29);
		contentPane.add(btnDeleteQuiz);
		
		btnShowStudentsData = new JButton("Show Student Scores");
		btnShowStudentsData.setBackground(Color.WHITE);
		btnShowStudentsData.setBounds(20, 327, 201, 29);
		contentPane.add(btnShowStudentsData);
		
	}
	
	/**
	 * Adds listeners to JButtons that create quizzes, delete quizzes, and get scores
	 */
	private void listeners() {
		
		btnCreateQuiz.addActionListener(action -> {					// creates new CreateQuizFrame 
			
			new CreateQuizFrame(this, teacherId).setVisible(true);
			
		});
		
		btnDeleteQuiz.addActionListener(new ActionListener() {		// prompts for quiz key and deletes quiz
			public void actionPerformed(ActionEvent e) {
				String key = JOptionPane.showInputDialog("Enter the key of the to be deleted.");
				if (key != null) {
					int confirm = JOptionPane.showConfirmDialog(null, 
							"Are you sure you want to delete the quiz?", 
							"Warning", JOptionPane.YES_NO_OPTION, 
							JOptionPane.WARNING_MESSAGE);
					if (confirm == JOptionPane.YES_OPTION) {
						manager.deleteQuiz(key);
						updateQuizTable();
					}
				}
			}
		});
		
		btnShowStudentsData.addActionListener(new ActionListener() {  // Fills Student Table with quiz information
			public void actionPerformed(ActionEvent e) {
				
				int select = quizTable.getSelectedRowCount();
				if(select == 1) {
					
					select = quizTable.getSelectedRow();
					int quizId = quizes.get(select).getId();
					updateStudentTable(quizId);
					
				}else {
					JOptionPane.showMessageDialog(null, "Select 1 row from quiz table.");
				}
				
			}
		});
		
		
	}
	
	/**
	 * Ensures current quizzes are displayed on the quizTable 
	 */
	private void updateQuizTable() {

		try {
			contentPane.remove(quizPane);
		} catch (Exception e) {
		}

		ArrayList<String[]> data = new ArrayList<>();
		quizes = manager.getAllQuizes(teacherId);
		for(Quiz quiz: quizes) {
			data.add(quiz.getTableData());
		}
		String[][] stringData = data.toArray(new String[][] {});
		String[] columns = new String[] { "Quiz Title","Subject", "Key", "Questions", "Time(mins)" };

		quizTable = new JTable(stringData, columns);
		quizPane = new JScrollPane(quizTable);
		quizTable.setBounds(20, 93, 628, 222);
		quizPane.setBounds(20, 93, 628, 222);
		contentPane.add(quizPane);

	}
	
	/**
	 * Updates studentTable to display students' quiz scores 
	 * @param quizId id of quiz to get scores of
	 */
	private void updateStudentTable(int quizId) {

		try {
			contentPane.remove(studentPane);
		} catch (Exception e) {
		}

		String[][] stringData = manager.getTeacherStudentData(quizId);
		String[] columns = new String[] { "Student Name", "Total", "Correct", "Incorrect" };

		studentData = new JTable(stringData, columns);
		studentPane = new JScrollPane(studentData);
		studentData.setBounds(20, 368, 628, 192);
		studentPane.setBounds(20, 368, 628, 192);
		contentPane.add(studentPane);
		
		btnExit = new JButton("Exit");					// exit button that closes program
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
				new LoginFrame().setVisible(true);
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(531, 572, 117, 29);
		contentPane.add(btnExit);
		
	}

	/**
	 * Method for implementing IQuizUpdate. Updates quizTable
	 */
	@Override
	public void notifyQuizUpdated() {
		updateQuizTable();
	}
	
	
	private void exit() {
		dispose();
	}
}
