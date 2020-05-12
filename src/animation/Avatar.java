package animation;

import java.awt.*;

/**
 * This class represents the animated avatar that moves across the screen during practice mode.
 */
public class Avatar extends Canvas implements MoveableShape {
	Image img;			// picture of avatar
	private int x;		// x coordinate
	private int y;		// y coordinate

	/**
	 * Constructor
	 * @param x starting x position
	 * @param y starting y position
	 * @param path path to avatar image on filesystem
	 */
	public Avatar(int x, int y, String path) {
		Toolkit t = Toolkit.getDefaultToolkit();
		img = t.getImage(path);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Inherited from Canvas. This method draws the avatar on the screen.
	 * @param g graphics device. Passed from caller.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, x, y, this);
	}
	
	/**
	 * Dummy method that calls paint(). Needed to properly implement MoveableShape interface.
	 * @param g Graphics2D device. Passed from caller.
	 */
	public void draw(Graphics2D g) {
		paint(g);
	}
	
	/**
	 * @return x x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return y y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Translates avatar on the screen
	 * @param dx change in x position
	 * @param dy change in y position
	 */
	public void translate(int dx, int dy) {
		x+=dx;
		y+=dy;
	}
	
}
