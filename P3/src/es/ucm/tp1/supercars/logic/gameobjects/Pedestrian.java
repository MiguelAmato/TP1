package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends Obstacle {

	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	 
	private boolean sentido = false; 
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		symbol = "☺";
	}
	
 	public boolean receiveCollision(Player player) {
 		player.kill();   
		alive = false;
		return true;
	}
 	
	public boolean receiveShoot() {
		alive = false;
		game.loseAllCoins();
		return true;
	}
	
	public void receiveExplosion() {
		game.loseAllCoins();
		alive = false;
	}

	public void update() {
		if(!sentido) {
			if (y == game.getRoadWidth() - 1) {
				sentido = true;
				moveUp();
			}
			else {
				moveDown();
			}
		}
		else {
			if(y == 0) {
				sentido = false;
				moveDown();
			}
			else {
				moveUp();
			}
		}
	}
	
	public void moveUp() {
		y -= 1;
	}
	
	public void moveDown() {
		y += 1;
	}
	
	public String serialize() {
		String ret = (toString() + " (" + x + ", " + y + ") "); 
		if (sentido) {
			ret += "up";
		}
		else {
			ret += "down";
		}
		return ret;
	}
}
