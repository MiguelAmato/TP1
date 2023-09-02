package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.Explosion;

public class Grenade extends GameObjects{

	public static final String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	
	public int cycles = 4;
	 
	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		deadSymbol = "";
	}

	public void update() {
		cycles -= 1;
		if(cycles == 0) {
			alive = false;
		}
	}

	public void onDelete() {
		game.execute(new Explosion(x,y));
	}
	
	public String getSymbol() {
		return  "ð[" + cycles + "]"; 
	}
 
	public boolean isAlive() {
		return alive;
	}
	
	public boolean doCollision() {
 		return false;
	}

 	public boolean receiveCollision(Player player) {
 		return false;
	}

 	public boolean receiveShoot() {
 		return false;
	}
	
	public boolean receiveThunder() { 
		return false;
	}
	
	public void onEnter() {}
	public void receiveExplosion() {}
}
