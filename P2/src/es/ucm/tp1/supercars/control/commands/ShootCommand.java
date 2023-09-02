package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.Shoot;

public class ShootCommand extends Command implements Buyable {

	private final int COST = 1;
	
	private static final String NAME = "shoot";
	private static final String DETAILS = "[s]hoot";
	private static final String SHORTCUT = "s";
	private static final String HELP = "shoot bullet";
	
	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
 
	public int cost() {
		return COST;
	}
	
	public boolean execute(Game game) {
		if(buy(game)) {
			game.execute(new Shoot());
			game.updateGame();
		}
		else {
			System.out.println("[ERROR]: Failed to shoot\n");
			return false;
		}
		return true;
	}
}
