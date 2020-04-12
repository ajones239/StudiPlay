package view;

import javax.swing.*;
import java.awt.*;

public class PracticeMainFrame extends JFrame {
	
	JPanel subjectSelectionPane;
	JPanel contentPane;
	
	public PracticeMainFrame() {
		setBounds(100, 100, 671, 441); // What are these numbers?

		subjectSelectionPane = new JPanel();
		subjectSelectionPane.setLayout(new BoxLayout(subjectSelectionPane, BoxLayout.Y_AXIS));
		subjectSelectionPane.add(new JButton("cat1"));
		subjectSelectionPane.add(new JButton("cat2"));
		subjectSelectionPane.add(new JButton("cat3"));
		subjectSelectionPane.add(new JButton("cat4"));

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		JLabel defaultText = new JLabel("Select a subject to get started!");
		defaultText.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(defaultText, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(contentPane, BorderLayout.CENTER);
		add(subjectSelectionPane, BorderLayout.WEST);
	
	}
}
