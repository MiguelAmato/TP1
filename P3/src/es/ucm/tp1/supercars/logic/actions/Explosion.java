package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;

public class Explosion implements InstantAction{

	private int x;
	private int y;
	
	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void execute(Game game) {
		GameObjects object; 
		for(int i = x - 1; i < x + 2; i++) {
			for(int j = y - 1; j < y + 2; j++) {
				object = game.getObjectInPosition(i, j);
				if(object != null) {
					object.receiveExplosion();
				}		
			}
		}
	}
}
