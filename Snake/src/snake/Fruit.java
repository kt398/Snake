package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Fruit {
	private int x, y;
	private int score;

	public Fruit() {
		x=(int)(Math.random()*50)*10; 
		y=(int)(Math.random()*50)*10;
		score=0;
	}
	public Fruit(int x,int y){
		this.x=x;
		this.y=y;
		score=0;
	}

	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 10, 10);
		g.setColor(Color.black);
		g.drawOval(x, y, 10, 10);
		g.setColor(Color.white);
		g.drawString(""+score, 550, 250);
	}
	public int getScore(){
		return score;
	}
	public void setScore(int x){
		score=0;
	}

	public void gotEaten(Snake s) {
		if(s.getBody().get(0).getCoordinate().equals(new Point(x,y))){
			score+=100;
			SnakeBody sb=s.getBody().get(s.getBody().size()-1);
			int dir=sb.getDirection();
			if(dir==1)
				s.addBody(sb.getX()+10, sb.getY(), 1);
			else if(dir==0)
				s.addBody(sb.getX(), sb.getY()+10, 0);
			else if(dir==2)
				s.addBody(sb.getX(), sb.getY()-10, 2);
			else if(dir==3)
				s.addBody(sb.getX()-10, sb.getY(), 3);
			randXY(s);
		}
	}
	public void randXY(Snake s){
		x=(int)(Math.random()*50)*10;
		y=(int)(Math.random()*50)*10;
		for(int i=0;i<s.getBody().size();i++){
			if(s.getBody().get(i).getCoordinate().equals(new Point(x,y)))
				randXY(s);
		}
	}
}