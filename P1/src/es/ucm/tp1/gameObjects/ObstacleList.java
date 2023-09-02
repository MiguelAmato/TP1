package es.ucm.tp1.gameObjects;

import es.ucm.tp1.logic.Game;

public class ObstacleList {
	
	private Game game;

	private Obstacle obstacleList[] = new Obstacle[100];
	private int obstacleCounter;
	
	public Obstacle[] getObstacleList() {
		return this.obstacleList;
	}
	
	public int getObsCounter() {
		return obstacleCounter;
	}
	
	public void addObstacle(Obstacle obstacle) {
		obstacleList[obstacleCounter] = obstacle;
		obstacleCounter++;
	}
	
	public boolean isThereObstacle(int posX, int posY) {
		boolean weGotIt = false;
		int i = 0;
		
		while (!weGotIt && i < obstacleCounter) {
			if (obstacleList[i].getX() == posX && obstacleList[i].getY() == posY) {
				weGotIt = true;
			}
			else {
				i++;
			}
		}
		
		return weGotIt;
	}
}
