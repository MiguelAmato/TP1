package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;

public class ThunderAction implements InstantAction {

	static int x;
	static int y;
	static boolean hit;
	static String objSymbol;
	GameObjects object;
	
	public void execute(Game game) {
		hit = false;
		x = game.getRandomX();
		y = game.getRandomLane();
		object = game.getObjectInPosition(game.getCarPosX() + x, y);
		if (object != null) {
			objSymbol = object.toString();
			hit = object.receiveThunder();
 		}
		else {
			hit = false;	
		}
		System.out.println(hit());
 	}
	
	public String hit() {
		String ret = "Thunder hit position: (" + x + " , " + y + ")";
		if(hit) {
			ret += " -> " + objSymbol;
		} 
		return ret;
	}
}
