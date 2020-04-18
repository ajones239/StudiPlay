package view;

import javax.swing.*;
import java.awt.*;

import model.*;

public class PracticeMainFrame extends JFrame {
	
	JPanel subjectSelectionPane;
	JPanel contentPane;
	QuestionPanel qpanel;
	JLabel defaultText;
	Subject[] subjects = new Subject[Subject.SUBJECTS.length];
	
	public PracticeMainFrame() {
		setBounds(100, 100, 671, 441); // What are these numbers?

		for (int i = 0; i < Subject.SUBJECTS.length; i++) 
			subjects[i] = new Subject(Subject.SUBJECTS[i]);
		
		subjectSelectionPane = new JPanel();
		subjectSelectionPane.setLayout(new BoxLayout(subjectSelectionPane, BoxLayout.Y_AXIS));
		subjectSelectionPane.setBackground(Color.darkGray);
		JButton topic1 = new JButton(subjects[0].getCategory());
		JButton topic2 = new JButton(subjects[1].getCategory());
		JButton topic3 = new JButton(subjects[2].getCategory());
		JButton topic4 = new JButton(subjects[3].getCategory());
		
		subjectSelectionPane.add(topic1);
		subjectSelectionPane.add(topic2);
		subjectSelectionPane.add(topic3);
		subjectSelectionPane.add(topic4);
		
		topic1.addActionListener(event -> replaceSubject(subjects[0]));
		topic2.addActionListener(event -> replaceSubject(subjects[1]));
		topic3.addActionListener(event -> replaceSubject(subjects[2]));
		topic4.addActionListener(event -> replaceSubject(subjects[3]));

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		defaultText = new JLabel("Select a subject to get started!");
		defaultText.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(defaultText, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(contentPane, BorderLayout.CENTER);
		add(subjectSelectionPane, BorderLayout.WEST);
	
	}
	
	private void replaceSubject(Subject s) {
		QuestionPanel temp = qpanel;
		try {
			for (Component c: contentPane.getComponents()) {
				if (c instanceof JLabel) {
					temp = new QuestionPanel(s.getFirstQuestion());
					contentPane.remove(c);
					contentPane.revalidate();
					contentPane.repaint();
					break;
				}
			}
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			// This will be Null every time this method is called except the first
		}
		try {
			for (Component c: contentPane.getComponents()) {
				if (c instanceof QuestionPanel) {
					contentPane.remove(c);
					contentPane.revalidate();
					contentPane.repaint();
					break;
				}
			}
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			// No action required, this will throw a NullPointerException the first time
		}
		if (s.hasNextQuestion(temp.getCurrentQuestion())) {
			qpanel = new QuestionPanel(s.getNextQuestion(temp.getCurrentQuestion()));
			contentPane.add(qpanel, BorderLayout.CENTER);
		}
		else {
			// finished subject message
		}
		contentPane.revalidate();
		contentPane.repaint();
	}
}
