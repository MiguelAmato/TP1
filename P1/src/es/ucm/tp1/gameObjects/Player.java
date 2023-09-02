package es.ucm.tp1.gameObjects;

public class Player {

	
	private int posX;
	private int posY;
	private int coinCounter;
	public final String skin = ">";
	private int distance;
	public final static String INFO = "[Car] the racing car"; // las constantes da igual hacerlas publicas porque no se van a poder modificar de todas formas
 	
	 	
	public Player(int posX,int posY, int distance) {
		
		this.posX = posX;
		this.posY = posY;
		this.coinCounter = 5;
		this.distance = distance;
	}
	
	public int getX() {
		return  posX;
	}
	
	public int getY() {
		return posY;
	}

	
	public int getDistance() {
		return distance;
	}
	
	public int getCoinCounter() {
		
		return coinCounter;
		
	}

	public void setCoinCounter(int coinCounter) {
		
		this.coinCounter = coinCounter;
		
	}
	
	public void moveForward() {
		posX = posX + 1;
		distance--;
	}
	
	public void goUp() {
		posY = posY - 1;
		posX = posX + 1;
		distance--;
	}
	
	public void goDown() {
		
		posY = posY + 1;
		posX = posX + 1;
		distance--;

	}
	
	public String toString() {
		
		return "";
		
	}
	
 	
	
	
	
}
