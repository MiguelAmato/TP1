package es.ucm.tp1.supercars.control;

import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {

	public int cost();
	 
	public default boolean buy(Game game){
		if(game.getCoinCounter() >= cost()) {
			game.purchase(cost());
			return true;
		}
		else {
			System.out.println("Not enough coins");
			return false;
		}
	}
}
