package es.ucm.tp1.supercars.control;

import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {

	public int cost();
	 
	public default boolean buy(Game game) throws NotEnoughCoinsException {
		if(game.getCoinCounter() < cost()) {
			throw new NotEnoughCoinsException("Not enough coins");
		}
		game.purchase(cost());
		return true;
	}
}
