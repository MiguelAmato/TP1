package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;

public class Shoot implements InstantAction{
	
	public void execute(Game game) {
		int i = game.getCarPosX();
		boolean end = false;
		GameObjects object = null;
		
		while(i < game.getVisibility() + game.getCarPosX() && !end) {
			object = game.getObjectInPosition(i, game.getCarPosY());
			if(object != null) {
				 end = object.receiveShoot();	
			}
			i += 1;
		}
	}
}

