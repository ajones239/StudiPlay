package view;

import javax.swing.*;
import java.awt.*;
import model.Question;

public class QuestionPanel extends JPanel {

	private Question currentQuestion;
	
	public QuestionPanel(Question q)  {
		currentQuestion = q;
		setLayout(new BorderLayout());
		JLabel qTitle = new JLabel(currentQuestion.getQuestion());
		qTitle.setHorizontalAlignment(JLabel.CENTER);
		add(qTitle, BorderLayout.NORTH);
		JRadioButton option1 = new JRadioButton(currentQuestion.getOption1());
		JRadioButton option2 = new JRadioButton(currentQuestion.getOption2());
		JRadioButton option3 = new JRadioButton(currentQuestion.getOption3());
		JRadioButton option4 = new JRadioButton(currentQuestion.getOption4());
		ButtonGroup group = new ButtonGroup();
		group.add(option1);
		group.add(option2);
		group.add(option3);
		group.add(option4);
		option1.setBounds(50, 50, 50, 25);
		option2.setBounds(50, 75, 50, 25);
		option3.setBounds(50, 100, 50, 25);
		option4.setBounds(50, 125, 50, 25);
		add(option1, BorderLayout.SOUTH);
		add(option2, BorderLayout.SOUTH);
		add(option3, BorderLayout.SOUTH);
		add(option4, BorderLayout.SOUTH);
		JButton submitButton = new JButton("submit");
		add(submitButton, BorderLayout.SOUTH);
	}
	
	public Question getCurrentQuestion() {
		return currentQuestion;
	}
}
