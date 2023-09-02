package es.ucm.tp1.gameObjects;

import es.ucm.tp1.logic.Game;

public class Obstacle {
	private Game game;
	private int posX;
	private int posY;
	public final static String INFO = "[Obstacle] hits car";
	public final String skin = "░";
	
	public Obstacle(Game game, int posX, int posY) {
		this.game = game;
		this.posX = posX;
		this.posY = posY;
	}

	public int getX() {
		return this.posX;	
	}
	
	public int getY() {
		return this.posY;		
	}
}
