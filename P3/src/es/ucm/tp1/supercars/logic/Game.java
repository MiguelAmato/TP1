package es.ucm.tp1.supercars.logic;

import java.util.*;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
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
        try {
			@SuppressWarnings("unused")
			double record = Record.findRecord(level.toString());
		} 
        catch ( InputOutputRecordException iore) {
			iore.getMessage();
			exit = true;
		}
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
			if (car.getX() == length + 1) {
				record((getCurrentTime() * 1000), level);
			}
			isFinished = true;
		}
		return isFinished;
	}
	
	public void record(double time, Level level) {
		try {
			if (time < Record.findRecord(level.toString())) {
				Record.saveRecord(level.toString(), (long)time);
			}
		}
		catch(InputOutputRecordException iore) {
			System.out.println("[ERROR]: can't open file");
		}
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
	
	public void loseAllCoins() {
		 car.loseAllCoins();
	}
	
	public void cheat(int option) {
		GameObjectGenerator.forceAdvanceObject(this,option,car.getX() + getVisibility() - 1);	
	}
	
	public String positionToSerialize(int x, int y) {
		String ret = "";
		if(car.isInPosition(x, y)) {
			ret += car.serialize() + "\n";
		}
		if (container.isThereAnObject(x,y) != null) {
			ret += container.getSerialize(x, y) + "\n";
		}
		return ret;
	}
	
	public String positionToString(int x, int y) {   
	    String ret = "";
	    if(car.isInPosition(x, y)) { 
	    	ret = car.toString() + " ";  
	    }
	    if (x == length){
		   	ret += GOAL;
	     }
	    else {
	        ret += container.getSymbolfrom(x,y);
	    }
        return ret;  
 	}
	
	public boolean isValidVisiblePosition(int x, int y) {
		return (x < getVisibility() && y < getRoadWidth() && y >= 0 && x >= 0);
	}

	public String getInfoPlayer() {
		return  ("Distance: " + car.getDistance() + "\n"
				+ "Coins: " + car.getCoinCounter() + "\n");
	}
	
	public GameObjects getObjectInPosition(int x, int y) {  
		return container.isThereAnObject(x,y);
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
	
	public double getCurrentTime() {
		return (((double)System.currentTimeMillis() - initialTime) / 1000);
	}

	public Level getLevel() {
		return level;
	}
	
	public long getSeed() {
		return seed;
	}
}



	