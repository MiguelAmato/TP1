package es.ucm.tp1.logic;

import java.util.*;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.gameObjects.*;
 
public class Game {
    
    private int cycle;
    private int length; 
    private int width;  
    private long seed;
    private Level level;
    private boolean test;
    private double time;
    private boolean exit;
    
    CoinList coinList;
    ObstacleList obstacleList;
    Player car;
    
    public Game(long seed, Level level) {
    	this.cycle = 0;
    	this.seed = seed;
        this.level = level;
        this.length = level.getLength();
        this.width = level.getWidth();
        this.car = new Player(0,(this.width/2),length);
        this.coinList = new CoinList();
        this.obstacleList = new ObstacleList();
    }
    
    public void reset() {
    	this.cycle = 0;
        this.length = level.getLength();
        this.width = level.getWidth();
        this.car = new Player(0,(this.width/2),length);
        this.coinList = new CoinList();
        this.obstacleList = new ObstacleList();
    }
    
    public void toggleTest() {
    	this.test = true;
    } 
    
    public int getVisibility() {
        return level.getVisibility();  
    }
    
    public int getRoadWidth() {
        return level.getWidth();
    }
    
    public int getPosX() {
        return car.getX();  
    }
    
    public int getDistance() {
    	
    	return car.getDistance();
    	
    }
   
    public int getCoinCounter() {
    	
    	return car.getCoinCounter();
    	
    }
    
    public int getCycle() {
    	
    	return this.cycle;
    }
    
    public void setCycle(int cycle) {
		
		this.cycle = cycle;
		
	}
 
    public int getObsCounter() {
    	
    	return obstacleList.getObsCounter();
    	
    }
    
    public int getTotalCoins() {
    	
    	return coinList.getCoinCounter();
    	
    }
    
    public boolean getTest() {
    	return this.test;
    }
    
    public void setTest(boolean test) {
    	this.test = test;
    }
    
    public void setTime(double time) {
    	this.time = time;
    }
    
    public double getTime() {
    	return time;
    }
    
    public boolean hasWon() {
    	return !obstacleList.isThereObstacle(car.getX(), car.getY());
    }
    
    public boolean getExit() {
    	return this.exit;
    }
    
    public void setExit(boolean exit) {
    	this.exit = exit;
    }
   
    public void showInfo() {
        
    	System.out.print("\n");
        System.out.println(Player.INFO);
        System.out.println(Coin.INFO);
        System.out.println(Obstacle.INFO);
        System.out.print("\n");
    
    }
    
    public void tryToAddObstacle(Obstacle obstacle, double frequency, double random) {
        if (random < frequency) {
            obstacleList.addObstacle(obstacle);
        }
    }
    
    public void tryToAddCoin(Coin coin, double frequency, double random) {
        if (!obstacleList.isThereObstacle(coin.getX(), coin.getY())) {
            if (random < frequency) {
                coinList.addCoin(coin);
            }
        }
    }
    
    public void objectPlacement() {
        Random random = new Random(seed);
        for (int x = level.getVisibility() / 2; x < level.getLength(); x++) {
            tryToAddObstacle(new Obstacle(this, x, (int)(random.nextDouble() * level.getWidth())), level.getObstacleFrequency(), random.nextDouble());
            tryToAddCoin(new Coin(this, x, (int)(random.nextDouble() * level.getWidth())), level.getCoinFrequency(), random.nextDouble());
            }
    }
    
    public void takeCoin() {  
    		 
		boolean weGotIt = false;
		int i = 0;
		
		while (!weGotIt && i < coinList.getCoinCounter()) {
			if (coinList.getCoinList()[i].getX() == car.getX() && coinList.getCoinList()[i].getY() == car.getY()) {
				car.setCoinCounter(car.getCoinCounter()+1);
				coinList.getCoinList()[i].setAlive(false);
				coinList.deleteCoin(coinList.getCoinList());
				weGotIt = true;
			}
			 
			i++;
		}
    }
      
    public String positionToString(int x, int y) { 
        String ret;
        if(x == car.getX() && y == car.getY())   	
        	if(!obstacleList.isThereObstacle(x, y))
        		ret =  ">";
        	else
        		ret = "@";
        	
        
        else if (obstacleList.isThereObstacle(x, y))  
            ret =  "░";
        
        
        else if (coinList.isThereCoin(x, y)) 
            ret =  "¢";
        
        
        else if (x == length) 
            ret = "¦";
        else 
            ret =  "";

        return ret;
    }
    
    public boolean end() {
        	
        if(this.car.getX() == length + 1) 
            return true;
        else if(obstacleList.isThereObstacle(car.getX(), car.getY())) {
        	return true;
        	}
        else
            return false;
            
    }
    
    public boolean update(String option) {
    	
    	boolean update = false;
        
        if(option.equalsIgnoreCase("q")) { 
            if(this.car.getY() > 0) {   
                car.goUp();
                update = true;
            }
        }
        
        else if(option.equalsIgnoreCase("a")) {
            if( this.car.getY() < getRoadWidth() - 1) {
                car.goDown();
                update = true;
            }
        }
            
        
        else if(option.equalsIgnoreCase("n") || option.equalsIgnoreCase("none") || option.equals("")) {
            car.moveForward();
            update = true;
        }
       
        
        else if(option.equalsIgnoreCase("h") || option.equalsIgnoreCase("help")) {
            System.out.println();
            update = true;
        }
        
        takeCoin();
        
        return update;
    }
        
    
}
	