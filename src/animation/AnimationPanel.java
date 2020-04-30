package animation;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimationPanel extends JPanel {
	private Avatar avatar;
	private int stepSize;
	private int count;
	private int stepCount;
	
	public AnimationPanel(int stepCount, int width) {
		stepSize = width/stepCount;
		this.stepCount = stepCount;
		this.setPreferredSize(new Dimension(10, 200));
		avatar = new Avatar(0, 30, "src/animation/alien2.png"); // numbers shouldn't be hardcoded
		setLayout(new BorderLayout());
		//setBackground(Color.DARK_GRAY);
		add(avatar);
	}
	
	public void step() {
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
