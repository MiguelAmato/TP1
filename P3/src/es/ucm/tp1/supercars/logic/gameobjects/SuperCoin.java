package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends GameObjects {
	
	public final static String INFO = "[SUPERCOIN] gives 1000 coins";
	
	private static int counter = 0;
	private static boolean taken = false;  
	private static boolean generated = false;
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "$";
	}

	public boolean doCollision() {
 		return false;
	}

	public boolean receiveCollision(Player player) {
		alive = false;
		game.takeSuperCoin();  
        taken = true;			
		return true;
	}

	public void onEnter() {
		counter += 1;
		generated = true;
	}
	
	public void onDelete() {	
		counter -= 1;
		generated = false;
	}
	
	public static void reset() {
		counter = 0;
		taken = false;
		generated = false;
	}
	
	public static boolean hasSuperCoin() {
		return generated;
	}
 
	public boolean isAlive() {
		return alive;
	}

	public boolean receiveShoot() {
		return false;
	}
	
	public boolean receiveThunder() {
		return false;
	}
	
	public void receiveExplosion() {}
	public void update() {}
}
