package snake;

import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	private ArrayList<SnakeBody> lst;
	private boolean isHead;
	private int direction;

	public Snake(int startingSize) {
		lst = new ArrayList<SnakeBody>();  
		lst.add(new SnakeBody(240, 250, true));
		for (int i = 250; i < startingSize * 10 + 250 - 10; i += 10) {
			lst.add(new SnakeBody(i, 250, false));
		}
		direction = -1;
	}

	public void resetDirection() {
		direction = -1;
	}

	public void addBody(int x, int y, int dir) {
		lst.set(lst.size() - 1, new SnakeBody(lst.get(lst.size() - 1).getX(), lst.get(lst.size() - 1).getY(), false,
				lst.get(lst.size() - 1).getDirection(), false));
		lst.add(new SnakeBody(x, y, false, dir, true));
	}

	public void draw(Graphics g) {
		for (int i = 0; i < lst.size(); i++)
			lst.get(i).draw(g, this);
	}

	public void move() {
		switch (direction) {
		case 0:// up
			lst.get(0).setDirection(direction);
			for (int i = lst.size() - 1; i > 0; i--) {
				lst.get(i).setXY(lst.get(i - 1).getX(), lst.get(i - 1).getY());
				lst.get(i).setDirection(lst.get(i - 1).getDirection());
			}
			lst.get(0).setY(lst.get(0).getY() - 10);
			lst.get(0).setDirection(direction);
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case 1:// left
			lst.get(0).setDirection(direction);
			for (int i = lst.size() - 1; i > 0; i--) {
				lst.get(i).setXY(lst.get(i - 1).getX(), lst.get(i - 1).getY());
				lst.get(i).setDirection(lst.get(i - 1).getDirection());
			}
			lst.get(0).setX(lst.get(0).getX() - 10);
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case 2:// down
			lst.get(0).setDirection(direction);
			for (int i = lst.size() - 1; i > 0; i--) {
				lst.get(i).setXY(lst.get(i - 1).getX(), lst.get(i - 1).getY());
				lst.get(i).setDirection(lst.get(i - 1).getDirection());
			}
			lst.get(0).setY(lst.get(0).getY() + 10);
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case 3:// right
			lst.get(0).setDirection(direction);
			for (int i = lst.size() - 1; i > 0; i--) {
				lst.get(i).setXY(lst.get(i - 1).getX(), lst.get(i - 1).getY());
				lst.get(i).setDirection(lst.get(i - 1).getDirection());
			}
			lst.get(0).setX(lst.get(0).getX() + 10);
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void isUp() {
		if (direction != 2)
			direction = 0;
	}

	public void isDown() {
		if (direction != 0)
			direction = 2;
	}

	public void isRight() {
		if (direction != 1)
			direction = 3;
	}

	public void isLeft() {
		if (direction != 3)
			direction = 1;
	}

	public boolean checkIfTouched() {
		for (int i = 1; i < lst.size(); i++) {
			if (lst.get(i).getCoordinate().equals(lst.get(0).getCoordinate()))
				return true;
		}
		if (lst.get(0).getX() < 0 || lst.get(0).getY() < 0)
			return true;
		if (lst.get(0).getX() > 490 || lst.get(0).getY() > 490)
			return true;
		return false;
	}

	public ArrayList<SnakeBody> getBody() {
		return lst;
	}

	public int getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}
}