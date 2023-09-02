package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public abstract class GameObjects implements Collider{
	
	protected int x, y;
	protected boolean alive;
	protected Game game;
	protected String symbol;
	protected String deadSymbol;
	
	public GameObjects (Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
		alive = true;
	}
	
	protected String getSymbol() {
		return symbol;
	}
	
	protected String getDeadSymbol() {
		return deadSymbol;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public void MoveBack() {
		 x += 1;
	}
	
	public String toString() {
		if (isAlive()) {
			return getSymbol();
		}
		else {
			return getDeadSymbol();
		}
	}
	
	public abstract void onEnter();
	public abstract void update();
	public abstract void onDelete();
	public abstract boolean isAlive();
	public abstract boolean receiveThunder();
}
