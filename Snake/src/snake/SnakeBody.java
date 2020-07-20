 package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SnakeBody {
	private int x, y;
	private int direction;
	private boolean isHead;
	private boolean isTail;

	public SnakeBody(int xPos, int yPos, boolean isHead) {
		x = xPos;
		y = yPos;
		this.isHead = isHead;
	}

	public SnakeBody(int xPos, int yPos, boolean isHead, int dir, boolean isTail) {
		direction = dir;
		x = xPos;
		y = yPos;
		this.isHead = isHead;
		this.isTail = isTail;
	}

	public void draw(Graphics g, Snake s) {
		if (isHead) {
			g.setColor(Color.red);
			g.fillOval(x, y, 10, 10);
		} else if (isTail) {
			int dir = s.getBody().get(s.getBody().size() - 1).getDirection();
			g.setColor(new Color(57, 171, 34));
			switch (dir) {
			case 1:
				g.fillPolygon(new int[] { x, x, x + 10 }, new int[] { y, y + 10, y + 5 }, 3);
				//System.out.println("Dir 1");
				break;
			case 0:
				g.fillPolygon(new int[]{x,x+10,x+5},new int[]{y,y,y+10}, 3);
				//System.out.println("Dir 2");
				break;
			case 2:
				g.fillPolygon(new int[]{x,x+10,x+5}, new int[]{y+10,y+10,y},3);
				//System.out.println("Dir 3");
				break;
			case 3:
				g.fillPolygon(new int[]{x+10,x+10,x},  new int[]{y,y+10,y+5}, 3);
				//System.println("Dir 4");
				break;
			}
	}else
	{
		g.setColor(new Color(57, 171, 34));
		g.fillRect(x, y, 10, 10);
	}
	// g.setColor(Color.BLACK);
	// g.drawOval(x, y, 10, 10);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getCoordinate() {
		return new Point(x, y);
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public int getDirection() {
		return direction;
	}
}