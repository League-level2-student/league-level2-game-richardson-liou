package sumo;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Player player;
	List<Platform> platforms = new ArrayList();
	int score = 0;
	int highScore = 0;
	float maxY = 800;
	int size = 100;
	
	ObjectManager(Player p){
		player = p;
	}
	
	public int getScore() {
		return score;
	}
	
	
	public void addPlatform(Platform p) {
		platforms.add(p);
	}
	
	public void update() {
		for(Platform p : platforms){
			p.update();
		}
		player.update();
		checkCollision();
		addScore();
		isDead();
		
	}
	public void draw(Graphics g) {
		player.draw(g);
		for(int i = 0; i <platforms.size(); i++) {
			Platform p = platforms.get(i);
			p.draw(g);
		}
		
	}
	
	public void platformSpawn() {

		Random rand = new Random();
		int randomx = rand.nextInt(400);
		int randomy = 0;
		size = rand.nextInt(100)+20;
		int lastPlat = platforms.size()-1;

				if(lastPlat-1>=0) {
					Platform p = platforms.get(lastPlat);
					randomy =(int) p.y -(rand.nextInt(170)+30);

				}
				
				else {
					Platform p = platforms.get(lastPlat);
					randomy = 600;
				}
				
				
				 
			Platform plat = new Platform(randomx,randomy,size,20);
			platforms.add(plat);
		
		
		}
		
		
		//}
		

	
	public void checkCollision() {
		boolean isOnGround = false;
		for(int i = 0; i<platforms.size(); i++) {
			Platform p = platforms.get(i);	
			if(player.collisionBox.intersects(p.collisionBox)&&player.yVelocity >=0){
				player.canJump = true;

				if(player.y < p.y -20) {
					player.yVelocity = 0; 

				isOnGround = true;

				}
	}	
		player.isOnGround = isOnGround;
		if(player.x > FoodleHop.WIDTH) {
			player.x = 0;
		}
		else if (player.x<0) {
			player.x = FoodleHop.WIDTH;
		}
		
		else if (player.y<150) {
			player.y +=400;
			maxY = player.getY();
			for (int k = 0; k< platforms.size();k++) {
				Platform plat = platforms.get(k);
				plat.y+=400;
			}
			for(int o = 0; o<3; o++) {
				platformSpawn();
			}
			
		}
		
		if(player.y>800) {
		
		}
		
}
	}
	
	public void addScore() {
		if(player.getY() <maxY) {
			maxY = player.getY();
			//System.out.println(maxY+ "this is"+ player.getY());
		}
		if(player.yVelocity <0&& player.getY() <=maxY) {
			score+=1;
		}
		else if(player.getY()>maxY) {
			//System.out.println("no score");
		}
	}
	
	public boolean isDead() {
		if(player.getY()>800) {
			
			platforms.clear();
			if(score >highScore) {
				highScore = score;
			}
			score = 0;
			return true;
		}
		else {
			return false;
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//platformSpawn();
		
	}
}
