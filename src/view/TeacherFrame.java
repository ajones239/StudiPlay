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

import database.Manager;
import model.IQuizUpdater;
import model.Quiz;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherFrame extends JFrame implements IQuizUpdater{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane quizPane, studentPane;
	private JTable quizTable;
	private JTable studentData;
	private JButton btnCreateQuiz;
	private JButton btnShowStudentsData;
	private Manager manager;
	private int teacherId;
	private ArrayList<Quiz> quizes;
	private JButton btnExit;

	public TeacherFrame(int id) {
		
		setTitle("Teacher Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//
		this.teacherId = id;
		this.manager = Manager.getInstance();
		addComponents();
		listeners();
		updateQuizTable();
		updateStudentTable(-1);
		
	}
	
	private void addComponents() {
		
		JLabel lblTeacherFrame = new JLabel("Teacher Frame");
		lblTeacherFrame.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTeacherFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherFrame.setBounds(23, 6, 613, 34);
		contentPane.add(lblTeacherFrame);
		
		btnCreateQuiz = new JButton("Create Quiz");
		btnCreateQuiz.setBackground(Color.WHITE);
		btnCreateQuiz.setBounds(20, 52, 117, 29);
		contentPane.add(btnCreateQuiz);
		
		btnShowStudentsData = new JButton("Show Students Data");
		btnShowStudentsData.setBackground(Color.WHITE);
		btnShowStudentsData.setBounds(20, 327, 151, 29);
		contentPane.add(btnShowStudentsData);
		
	}
	
	private void listeners() {
		
		btnCreateQuiz.addActionListener(action -> {
			
			new CreateQuizFrame(this, teacherId).setVisible(true);
			
		});
		
		btnShowStudentsData.addActionListener(new ActionListener() {
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
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(531, 572, 117, 29);
		contentPane.add(btnExit);
		
	}

	@Override
	public void notifyQuizUpdated() {
		updateQuizTable();
	}
}
