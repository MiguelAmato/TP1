package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;

public class ThunderAction implements InstantAction {

	static int x;
	static int y;
	static String objSymbol;
	GameObjects object;
	
	public void execute(Game game) {
		String hit = "";
		x = game.getRandomX();
		y = game.getRandomLane();
		object = game.getObjectInPosition(game.getCarPosX() + x, y);
		if (object != null) {
			objSymbol = object.toString();
			if (object.receiveThunder()) {
				hit = " -> " + objSymbol;
			}
 		}
		System.out.println("Thunder hit position: (" + x + " , " + y + ")" + hit);
 	}
}
