package animation;

import java.awt.*;

public class Avatar extends Canvas implements MoveableShape {
	Image img;
	private int x;
	private int y;

	public Avatar(int x, int y, String path) {
		Toolkit t = Toolkit.getDefaultToolkit();
		img = t.getImage(path);
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, x, y, this);
	}
	
	public void draw(Graphics2D g) {
		paint(g);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void translate(int dx, int dy) {
		x+=dx;
		y+=dy;
	}
	
}
