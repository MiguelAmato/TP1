package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable {
	
	private final int COST = 3;
	private static final String NAME = "grenade";
	private static final String DETAILS = "[g]renade <x> <y>";
	private static final String SHORTCUT = "g";
	private static final String HELP = "add a grenade in position x, y";
	
	private int x; 
	private int y;

	public GrenadeCommand( ) {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public int cost() {
 		return COST;
	}

	public boolean execute(Game game) {
		if(x < game.getVisibility() && y < game.getRoadWidth() && y >= 0 && x >= 0) {
			x += game.getCarPosX();
			if(game.getObjectInPosition(x, y) == null) {
				if(buy(game)) {
					game.forceAddObject(new Grenade(game, x, y));
					game.updateGame();
				}
			}
			else{
				System.out.println("Invalid position.");
				System.out.println("[ERROR]: Failed to add grenade\n");
				return false;
			}
		}
		else {
			System.out.println("Invalid position.");
			return false;
		}
		return true;
	}
	
	protected Command parse(String[] commandWords) {
		if (matchCommandName(commandWords[0])) {
			commandWords[0] = SHORTCUT;
			if(commandWords.length == 1) {
				System.out.println("[ERROR]: Must indicate a position");
				return null;
			}
			else if(commandWords.length == 3) {	
				try {
					x = Integer.parseInt(commandWords[1]);  // CAMBIAR DE LONGS A INTS
					y = Integer.parseInt(commandWords[2]);
				}
				catch(NumberFormatException nfe) {
					return null;
				}
				commandWords = new String[] {SHORTCUT};
			}	
		}
		return super.parse(commandWords);
	}
}
