package BrickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameController extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	
	private int totalBlocks = 21;
	
	private Timer time;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballPosX = 120;
	private int ballPosY = 350;
	
	private int ballDirX = -1;
	private int ballDirY = -2;
	
	private BlockController blocks;
	
	public GameController() {
		blocks = new BlockController(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay,this);
		time.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1,1,800,800);
		
		blocks.create((Graphics2D)g);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Score: "+score, 600, 40);
		
		//borders
		g.setColor(Color.green);
		g.fillRect(0, 0, 3, 792);
		g.fillRect(0, 0, 792, 3);
		g.fillRect(784, 0, 3, 792);
		
		//the paddle
		g.setColor(Color.blue);
		g.fillRect(playerX, 650, 100, 8);
		
		//the ball
		g.setColor(Color.yellow);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		if(totalBlocks <= 0) {
			play = false;
			ballDirX = 0;
			ballDirY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("You Won Score : "+score, 190, 300);
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Press Enter To Restart.", 230, 350);
		}
		if(ballPosY > 670) {
			play = false;
			ballDirX = 0;
			ballDirY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Game Over Score : "+score, 190, 300);
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Press Enter To Restart.", 230, 350);
		}
		g.dispose();
	}
	
	public void moveRight() {
		play = true;
		playerX += 30;
	}
	
	public void moveLeft() {
		play = true;
		playerX -= 30;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		
		if(play) {
			
			//detecting panel
			//creating rect for ball
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 650, 100, 8))){
				ballDirY = -ballDirY;
			}
			
			A: for(int i = 0; i < blocks.block.length; i++) {
				for(int j = 0; j < blocks.block[0].length; j++) {
					if(blocks.block[i][j] > 0) {
						int blockX =j*blocks.blockW + 120;
						int blockY =i*blocks.blockW + 80;
						int blockW =blocks.blockW;
						int blockH =blocks.blockW;
						
						Rectangle rect = new Rectangle(blockX,blockY,blockW,blockH-60);
						Rectangle ballRect = new Rectangle(ballPosX,ballPosY,20,20);
						Rectangle blockRect = rect;
						
						if(ballRect.intersects(blockRect)) {
							blocks.setBlockValue(0, i, j);
							totalBlocks--;
							score += 5;
							
							if(ballPosX + 19 <= blockRect.x || ballPosX + 1 >= blockRect.x + blockRect.width) {
								ballDirX = -ballDirX;
							}
							else {
								ballDirY = -ballDirY;
							}
							break A;
						}
					}
				}
			}
			
			ballPosX += ballDirX;
			ballPosY += ballDirY;
			
			if(ballPosX < 0) {
				ballDirX = -ballDirX;
			}
			if(ballPosY < 0) {
				ballDirY = -ballDirY;
			}
			if(ballPosX > 770) {
				ballDirX = -ballDirX;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 700) {
				playerX = 700;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			}
			else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballDirX = -1;
				ballDirY = -2;
				playerX = 310;
				score = 0;
				totalBlocks = 21;
				blocks = new BlockController(3,7);
				
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	
}
