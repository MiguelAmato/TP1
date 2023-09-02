package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends Obstacle{

	public static final String INFO = "[WALL] hard obstacle";
	public static final int REWARD = 5; 
	public static final int SKIN_NUMBER = 4;
	
	private String[] wallSymbols = new String[SKIN_NUMBER];	 
 	private int hp; 
 	private boolean explosion;
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		hp = 3;
		initializeSymbols();
		deadSymbol = wallSymbols[0];
		explosion = false;
	}
	
	private void initializeSymbols() {
		wallSymbols[0] = "";		
		wallSymbols[1] = "░";
		wallSymbols[2] = "▒";
		wallSymbols[3] = "█";		
	}
	
	public boolean receiveShoot() { 
		if(hp > 1) {
			hp -= 1;
		}
		else {
			alive = false;
			hp = 0;
		}
		return true;
	}
	
	public void receiveExplosion() {
		explosion = true;
		alive = false;
	}

	public void onDelete() {
		if (!explosion) {
			addWallCoins(); 
		}
		counter -= 1;
	}
	
	public void addWallCoins() {
		for(int i = 0; i < REWARD; i++) { 
			game.takeCoin();
		}
	}
	
	public String getSymbol() {
		return wallSymbols[hp];
	}
}

