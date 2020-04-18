package view;

import javax.swing.*;
import java.awt.*;
import model.Question;

public class QuestionPanel extends JPanel {

	Question currentQuestion;
	
	QuestionPanel(Question q) {
		currentQuestion = q;
		setLayout(new BorderLayout());
		JLabel qTitle = new JLabel(q.getQuestion());
		qTitle.setHorizontalAlignment(JLabel.CENTER);
		this.add(qTitle, BorderLayout.NORTH);
		JRadioButton option1 = new JRadioButton("a)");
		JRadioButton option2 = new JRadioButton("b)");
		JRadioButton option3 = new JRadioButton("c)");
		JRadioButton option4 = new JRadioButton("d)");
		ButtonGroup group = new ButtonGroup();
		group.add(option1);
		group.add(option2);
		group.add(option3);
		group.add(option4);
		add(option1, BorderLayout.SOUTH);
		add(option2, BorderLayout.SOUTH);
		add(option3, BorderLayout.SOUTH);
		add(option4, BorderLayout.SOUTH);
		JButton submitButton = new JButton("submit");
		add(submitButton, BorderLayout.SOUTH);
	}
}
