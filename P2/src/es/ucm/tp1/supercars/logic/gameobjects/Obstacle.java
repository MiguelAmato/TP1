package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObjects {

	public final static String INFO = "[Obstacle] hits car";
	
	protected static int counter = 0;
  	
	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		symbol = "░";
		deadSymbol = "";
	}
	
	public boolean receiveCollision(Player player) {
		player.kill();  		
		return true;
	}
	
	public boolean doCollision() {
		return false;
	}
	
	public boolean receiveShoot() {
		alive = false;
		return true;
	}
	
	public void receiveExplosion() {
		alive = false;
	}
	
	public boolean receiveThunder() {
		alive = false;
		return true;
	}
	
	public void onEnter() {
		counter += 1;
	}
 
	public void onDelete() {
		counter -= 1;
	}
	
	public static void reset() {
		counter = 0;
	}
	
	public static int getObsCounter() {
		return counter;
	}	
	
	public boolean isAlive() {
		return alive;
	}
	
	public void update() {}
}
