package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObjects{
	
	public final static String INFO = "[Car] the racing car";
 	public final int INITIAL_COINS = 5;
	
	private int coinCounter;
	private int distance;
	
	private String deadSymbol = "@";
 	 
	public Player(Game game, int x, int y) {
		super(game, x, y);
		symbol = ">";
		coinCounter = INITIAL_COINS;
		distance = game.getRoadLength();
	}
		
	public boolean doCollision() {
		GameObjects other = game.getObjectInPosition(x, y);
		if (other != null && other.isAlive()) {
			return other.receiveCollision(this);
		}
		return false;
	}
	
	public void MoveUp() {
 		doCollision();
 		if(alive) {
			if (y > 0) {
				y -= 1;
			}
			update();
			doCollision();
 		}
	}
	
	public void MoveDown() {
		doCollision();
		if(alive) {
			if(y < game.getRoadWidth() - 1) {
				y += 1;
			}
			update();
			doCollision();	
		}
	}

	public void advance() {
		doCollision();
		if(alive) {
			update();
			doCollision();
		}
	}
	
	public void update() {
		moveForward();
		reduceDistance();
	}

	public void moveForward() {  
		x = x + 1;
	}
	
	public void reduceDistance() {
		distance -= 1;
	}
	
	public void takeTurbo() {
		x = x + 3;
		distance -= 3;
	}
	
	public void takeCoin() {
		coinCounter += 1;	
	}
	
	public void takeSuperCoin() {
		coinCounter += 1000;
	}
	
	public void purchase(int cost) {
		 coinCounter -= cost;	
	}
	
	public void loseAllCoins() {
		 coinCounter = 0;
	}
	
	public void kill() {
		 alive = false; 
	}	
	
	public boolean isAlive() {
		return alive;
	}
	
	public int getCoinCounter() {
		return coinCounter;
	} 
	
	public int getDistance() {
		return distance;
	}
 
	public boolean receiveThunder() {
 		return false;
	}
	
	public boolean receiveShoot() {
 		return false;
	}
	
	public boolean receiveCollision(Player player) {
		return false;
	}
	
	public String toString() {
		if (isAlive()) {
			return symbol;
		}
		else {
			return deadSymbol;
		}
	}
	
	public void onEnter() {}
	public void onDelete() {}
	public void receiveExplosion() {}
}
