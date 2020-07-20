 package snake;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JApplet;

public class SnakesGame extends JApplet implements KeyListener, Runnable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	Scanner scn;
	Board b1;
	Snake s;
	Fruit fr;
	Thread thread;
	Graphics gfx;
	Image img;
	File highScores;
	int[] scores;
	int counter;
	int snakeSpeed;
	boolean isPaused;
	boolean checkIfPaused;
	Clip clip;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	public void init() {
		clip = null;
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("E:\\Workspace\\Snake\\Res\\Pacman_Dubstep_Remix.wav")));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-25.0f); // Reduce volume by 10 decibels.
			//clip.start();
			//clip.setLoopPoints(0,clip.LOOP_CONTINUOUSLY);
			System.out.println(clip.getLevel());

		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
		System.out.println(clip.getLevel());
		checkIfPaused = true;
		isPaused = false;
		// scores=new int[10];
		this.setSize(900, 550);
		snakeSpeed = 50;
		// highScores=new File("E:\\Workspace\\Snake\\bin\\High Scores");
		// try {
		// highScores.createNewFile();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// try {
		// scn=new Scanner(highScores);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// highScores();
		// for(int i=0;i<scores.length;i++)
		// System.out.println(scores[i]);
		b1 = new Board();
		s = new Snake(5);
		fr = new Fruit();
		thread = new Thread(this);
		thread.start();
		this.addKeyListener(this);
		this.setBackground(Color.black);
		this.requestFocusInWindow();
		this.setFocusable(true);
		img = createImage(900, 550);
		gfx = img.getGraphics();
		fr.setScore(0);
	}

	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, 900, 550);
		if (!s.checkIfTouched()) {
			fr.draw(gfx);
			s.draw(gfx);
			b1.draw(gfx);
		} else {
			paint2(gfx);
			pause();
		}
		// }
		// else

		g.drawImage(img, 0, 0, this);
	}

	public void paint2(Graphics g) {
		gfx.setColor(Color.red);
		gfx.drawString("GAME OVER", 250, 250);
		gfx.drawString("Press R to restart", 237, 300);
	}

	public void run() {
		while (!s.checkIfTouched()) {
			repaint();
			if (!isPaused) {
				s.move();
			}
			fr.gotEaten(s);

			switch ((int) (fr.getScore() / 1000)) {
			case 0:
				snakeSpeed = 50;
				break;
			case 1:
				snakeSpeed = 45;
				break;
			case 2:
				snakeSpeed = 40;
				break;
			case 3:
				snakeSpeed = 35;
				break;
			case 4:
				snakeSpeed = 30;
				break;
			default:
				snakeSpeed = 25;
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (!isPaused) {
			if (s.getDirection() != 2 && e.getKeyCode() == KeyEvent.VK_UP) {
				s.isUp();
			} else if (s.getDirection() != 0 && e.getKeyCode() == KeyEvent.VK_DOWN)
				s.isDown();
			else if (s.getDirection() != 3 && e.getKeyCode() == KeyEvent.VK_LEFT) {
				s.isLeft();
			} else if (s.getDirection() != 1 && e.getKeyCode() == KeyEvent.VK_RIGHT && s.getDirection() != -1) {
				s.isRight();
			}
			if (s.checkIfTouched())
				if (e.getKeyCode() == KeyEvent.VK_R) {
					pause();
					init();
				}
		}
		if (e.getKeyCode() == KeyEvent.VK_P && checkIfPaused && isPaused) {
			checkIfPaused = false;
			isPaused = false;
			start();
		} else if (e.getKeyCode() == KeyEvent.VK_P && checkIfPaused && !isPaused) {
			checkIfPaused = false;
			isPaused = true;
			pause();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P)
			checkIfPaused = true;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void highScores() {
		for (int i = 0; i < 10; i++)
			scores[i] = scn.nextInt();
	}

	public void pause() {
		//clip.stop();
	}

	public void start() {
		//clip.start();
	}

}