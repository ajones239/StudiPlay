package animation;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimationPanel extends JPanel {
	private static final String CORRECT_MSG = "Correct!";
	private static final String INCORRECT_MSG = "Sorry, that was incorrect.";
	private Avatar avatar;
	private int stepSize;
	private int count;
	private int stepCount;
	private Timer timer;
	private JDialog dialog;
	private JLabel text;
	private int score;
	
	public AnimationPanel(int stepCount, int width) {
		text = new JLabel();
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setForeground(Color.red);
		timer = new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		score = 0;
		timer.setRepeats(false);
		stepSize = width/stepCount;
		this.stepCount = stepCount;
		this.setPreferredSize(new Dimension(10, 200));
		avatar = new Avatar(0, 30, "src/animation/alien2.png"); // numbers shouldn't be hardcoded
		setLayout(new BorderLayout());
		add(avatar);
		add(text, BorderLayout.SOUTH);
	}
	
	public void correctAction() {
		score++;
		text.setText(CORRECT_MSG+" Current score: "+score);
		text.repaint();
		step();
	}
	
	public void incorrectAction() {
		text.setText(INCORRECT_MSG+" Current score: "+score);
		text.repaint();
	}
	
	private void step() {
		count++;
		avatar.translate(stepSize, 0);
		avatar.repaint();
	}
	
	public int getCount() {
		return count;
	}
	
	public int getStepCount() {
		return stepCount;
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setSize(800, 200);
		j.setVisible(true);
		AnimationPanel a = new AnimationPanel(10, 800);
		j.add(a);
		
		Timer t = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (a.getCount() <= a.getStepCount())
					a.step();
			}
		});
		t.start();
	}

}
