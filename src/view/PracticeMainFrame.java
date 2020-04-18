package view;

import javax.swing.*;

import model.Question;

import java.awt.*;
import java.awt.event.*;

public class PracticeMainFrame extends JFrame {
	
	JPanel subjectSelectionPane;
	JPanel contentPane;
	QuestionPanel qpanel;
	JLabel defaultText;
	
	public PracticeMainFrame() {
		setBounds(100, 100, 671, 441); // What are these numbers?

		subjectSelectionPane = new JPanel();
		subjectSelectionPane.setLayout(new BoxLayout(subjectSelectionPane, BoxLayout.Y_AXIS));
		JButton topic1 = new JButton("Basic Math");
		JButton topic2 = new JButton("Algebra");
		JButton topic3 = new JButton("Quantum Chemistry");
		JButton topic4 = new JButton("Language Arts");
		
		subjectSelectionPane.add(topic1);
		subjectSelectionPane.add(topic2);
		subjectSelectionPane.add(topic3);
		subjectSelectionPane.add(topic4);
		
		topic1.addActionListener(event -> replaceSubject(topic1.getText()));
		topic2.addActionListener(event -> replaceSubject(topic2.getText()));
		topic3.addActionListener(event -> replaceSubject(topic3.getText()));
		topic4.addActionListener(event -> replaceSubject(topic4.getText()));

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		defaultText = new JLabel("Select a subject to get started!");
		defaultText.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(defaultText, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(contentPane, BorderLayout.CENTER);
		add(subjectSelectionPane, BorderLayout.WEST);
	
	}
	
	private void replaceSubject(String subject) {
		try {
			for (Component c: contentPane.getComponents()) {
				if (c instanceof JLabel) {
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
		qpanel = new QuestionPanel(new Question("Basic Math", "1 + 2","3", "5", "4", "2", 1, "N/A"));
		contentPane.add(qpanel, BorderLayout.CENTER);
	}
}
