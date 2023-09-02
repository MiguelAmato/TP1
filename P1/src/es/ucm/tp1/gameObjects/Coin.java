package es.ucm.tp1.gameObjects;

import es.ucm.tp1.logic.Game;

public class Coin {
	private Game game; 
	private int posX;
	private int posY;
	public final String skin = "¢";
	public final static String INFO = "[Coin] gives 1 coin to the player";
 	private boolean alive; // = true; 

	public Coin(Game game, int posX, int posY){ 
		
		this.game = game;
		this.posX = posX;
		this.posY = posY;
		this.alive = true;
	}

	public int getX() {
		return posX;	
	}
	
	public int getY() {
		return posY;		
	}

	public void setAlive(boolean alive) {
		
		this.alive = alive;
	}
	
	public boolean getAlive() {
		
		return this.alive;
		
	}
	
}

