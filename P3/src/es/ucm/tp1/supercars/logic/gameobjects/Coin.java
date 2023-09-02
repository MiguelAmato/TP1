package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObjects {

	public final static String INFO = "[Coin] gives 1 coin to the player";
	
	protected static int counter = 0;

	public Coin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "¢";
	}
	
	public boolean receiveCollision(Player player) {
		alive = false;
		game.takeCoin();
		return true;
	}
	
	public boolean doCollision() {
		return false;
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
	
	public static int getCoinCounter() {
		return counter;
	}
		
	public boolean isAlive() {
		return alive;
	}
	
	public boolean receiveThunder() {
		return false;
	}
	
	public boolean receiveShoot() {	 
		return false;
	}
	
	public void receiveExplosion() {}
	public void update() {}
}
