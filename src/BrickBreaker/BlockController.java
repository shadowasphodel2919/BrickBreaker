package BrickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockController {
	public int block[][];
	public int blockW,blockH;
	public BlockController(int row, int col) {
		block = new int[row][col];
		for(int i = 0; i<block.length; i++) {
			for(int j=0; j<block[0].length; j++) {
				block[i][j] = 1;
			}
		}
		blockW = 540/col;
		blockH =150/row;
	}
	
	public void create(Graphics2D g) {
		for(int i=0;i<block.length;i++) {
			for(int j=0;j<block[0].length;j++) {
				if(block[i][j]>0) {
					g.setColor(Color.white);
					g.fillRect(j*blockW + 120, i*blockH + 80, blockW, blockH);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*blockW + 120, i*blockH + 80, blockW, blockH);
				}
			}
		}
	}
	public void setBlockValue(int value, int row, int col) {
		block[row][col] = value;
	}
}
