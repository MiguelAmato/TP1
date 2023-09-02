package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;

public class Wave implements InstantAction {

	GameObjects object = null;
	
	public void execute(Game game) {
		for(int i = game.getCarPosX() + game.getVisibility() - 1; i >= game.getCarPosX(); i--) {
			for(int j = 0; j < game.getRoadWidth(); j++) {
				object = game.getObjectInPosition(i, j);
				if(object != null && game.getObjectInPosition(i + 1 , j) == null) {
					object.MoveBack();
				}
			}
		}
	}
}
