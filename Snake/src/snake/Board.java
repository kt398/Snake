package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Board {
	public Board(){

//		Image img=new Image("E:\\Workspace\\Snake\\bin")
	}
	public void draw(Graphics g) {
		g.setColor(Color.darkGray); 
		for (int i = 10; i < 510; i += 10) {
			g.drawLine(i, 0, i, 500);
			g.drawLine(0, i, 500, i);
		}
	}
}
