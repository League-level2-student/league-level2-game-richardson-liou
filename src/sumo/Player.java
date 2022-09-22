package sumo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Rectangle collideBox = new Rectangle();



	
	public Player(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		collideBox.setBounds(x, y, width, height);
	}
	
	public void jump(){

	}
	
	public void update(){
		
		collideBox.setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
