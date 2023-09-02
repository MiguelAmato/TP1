package es.ucm.tp1.supercars.logic;

import java.util.*;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;
import es.ucm.tp1.supercars.logic.gameobjects.Player; 
import es.ucm.tp1.supercars.logic.actions.InstantAction;  

public class Game {
	
	public final String GOAL = "¦";
	
	private long seed;
	private long initialTime;
	private int cycles;
	private int length;
	private int width;
	private boolean exit = false;
	private boolean test = false;
	private boolean infoReset = false;
	
	Level level;
	Player car;
	GameObjectContainer container;
	Random random;
	
	public Game (long seed, Level level) {
		this.seed = seed;
		this.level = level;
	}
	
	public void initializeGame() {
		testMode();
		cycles = 0;
		length = level.getLength();
	    width = level.getWidth();
		car = new Player(this,0, width/2); 
        container = new GameObjectContainer();
        random = new Random(seed);
        objectsPlacement();
        initialTime = System.currentTimeMillis();
	}
	
	public void objectsPlacement() {
		GameObjectGenerator.generateGameObjects(this, level);
	}
	
	public void tryToAddObject(GameObjects gameObject , double frequency) {
		if (random.nextDouble() < frequency) {
			container.addObject(gameObject);
        }
	}
	
	public void forceAddObject(GameObjects o) {  
		container.addObject(o);
	}
	
	public void reset (long seed, String inputLevel, boolean bigReset) {
		infoReset = bigReset;
		if (bigReset) {
			this.seed = seed;
			this.level = Level.valueOfIgnoreCase(inputLevel);
		}
		GameObjectGenerator.reset(level);  
		initializeGame();
	}
	
	public void updateGame() {  
		container.updateObjects();
		GameObjectGenerator.generateRuntimeObjects(this);
		container.deleteObject();
		cycles += 1;
	}
	
	public void updatePlayer() {
		if(car.isAlive()) {
			car.update();
		}
	}
	
	public void execute(InstantAction action){
		action.execute(this);
	}
	
	public void moveForward() {
		 car.advance();
		 updateGame();
	}
	
	public void moveUp() {	
		  car.MoveUp();
		  updateGame();
 	}
	
	public void moveDown() {
		car.MoveDown();
		updateGame();
 	}
	
	public void testMode() {
		if (level == Level.TEST) {
			toggleTest();
		}
	}
	
	public void toggleTest() { 
		test = true;
	}
	
	public void exit() {
		exit = true;
	}
	
	public void takeCoin() {
		car.takeCoin();
	}
	
	public void takeSuperCoin() {
		car.takeSuperCoin();
	}

	public boolean isFinished() {   
		boolean isFinished = false;
		if (exit || !car.isAlive() || car.getX() == length + 1) {
			isFinished = true;
		}
		return isFinished;
	}
	
	public void clearLastLane() {
		container.clearLastLane(car.getX() + getVisibility() - 1);
	}
	
	public void clear() {
		 container.clear();
	}
	
	public void purchase(int cost) {
		car.purchase(cost);
	}
	
	public void punishment() {
		 car.punishment();
	}
	
	public void cheat(int option) {
		GameObjectGenerator.forceAdvanceObject(this,option,car.getX() + getVisibility() - 1);	
	}
	
	public String positionToString(int x, int y) {   
	    String ret = "";
	    if(car.isInPosition(x, y)) { 
	    	ret = car.toString() + " ";  
	    }
	    if (x == length){
		   	ret += GOAL;
	     }
	    if(x != length) {
	        ret += container.getSymbolfrom(x,y);
	    }
        return ret;  
 	}

	public GameObjects getObjectInPosition(int x, int y) {  
		return container.isThereAnObject(x,y);
	}
	
	public String getInfoPlayer() {
		return  "Distance: " + car.getDistance() + "\n"
				+ "Coins: " + car.getCoinCounter() + "\n";
	}
	
	public int getRandomLane() {    
  		return (int)(random.nextDouble() * level.getWidth());
  	}
	
	public int getRandomX() {
		return (int)(random.nextDouble() * level.getVisibility());
	}
	
	public int getVisibility() {
		return level.getVisibility();  
    }
    	
	public int getRoadWidth() {
        return level.getWidth();
    }
  	
  	public int getRoadLength() {
 		return level.getLength();
	}
  	
 	public int getCarPosX() {
		return car.getX();
	}
 	
 	public int getCarPosY() {
 		return car.getY(); 
 	}
	
	public boolean hasWon() {
		return car.isAlive();
	}
	
	public int getCoinCounter() {
		return car.getCoinCounter();
	}
	
	public int getCycles() {
		return cycles;
	}
			
	public boolean getExit() {
		return exit;
	}

	public boolean getTest() {
		return test;
	}

	public double getInitialTime() {
		return initialTime;
	}

	public Level getLevel() {
		return level;
	}
	
	public long getSeed() {
		return seed;
	}
	
	public boolean showInfoReset() {
		return infoReset;
	}
 
	public void notInfoReset() {
		infoReset = false;
	}
}



	