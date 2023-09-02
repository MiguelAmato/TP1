package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObjects {
	
	public final static String INFO = "[TURBO] pushes the car: 3 columns";
	
	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		symbol = ">>>";
	}

	public boolean doCollision() {
		return false;
	}
 
	public boolean receiveCollision(Player player) {  
		player.takeTurbo();
		return true;
	}
	
	public boolean receiveShoot() {
 		return false;
	}
	
	public boolean receiveThunder() {
		return false;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void receiveExplosion() {}
	public void onEnter() {}
	public void onDelete() {}
	public void update() {}
	public static void reset() {}
}
