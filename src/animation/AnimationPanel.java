package animation;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is a JPanel that implements some sort of animation to accompany practice quizzes.
 */
public class AnimationPanel extends JPanel {
	private static final String CORRECT_MSG = "Correct!";
	private static final String INCORRECT_MSG = "Sorry, that was incorrect.";
	private Avatar avatar;	
	private int stepSize;
	private JLabel text;		// current score and whether your last question was correct
	private int score;			// players current score
	
	/**
	 * Constructor
	 * @param stepCount the amount of "steps", or animations to perform (the number of questions in a quiz)
	 * @param width container width
	 */
	public AnimationPanel(int stepCount, int width) {
		text = new JLabel();
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setForeground(Color.red);
		score = 0;
		stepSize = width/stepCount;
		setPreferredSize(new Dimension(10, 200));
		avatar = new Avatar(0, 30, "src/animation/alien2.png");
		setLayout(new BorderLayout());
		add(avatar);
		add(text, BorderLayout.SOUTH);
	}
	
	/**
	 * Animation after the player is correct. Uses step() to advance the Avatar and updates text. 
	 * @param multiplier the score multiplier for the score
	 */
	public void correctAction(int multiplier) {
		score+= 1*multiplier;
		text.setText(CORRECT_MSG+" Current score: "+score);
		text.setForeground(Color.black);
		text.repaint();
		step();
	}
	
	/**
	 * Animation for after the player is incorrect. Updates text.
	 */
	public void incorrectAction() {
		text.setText(INCORRECT_MSG+" Current score: "+score);
		text.setForeground(Color.RED);
		text.repaint();
	}
	
	/**
	 * Advances the Avatar by a stepSize. Private method.
	 */
	private void step() {
		avatar.translate(stepSize, 0);
		avatar.repaint();
	}
}
