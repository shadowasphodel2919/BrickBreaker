package BrickBreaker;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		GameController gc = new GameController();
		jf.setTitle("Brick Breaker");
		jf.setBounds(30, 30, 800, 700);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.add(gc);
	}
}
