package es.ucm.tp1.supercars.logic;

import java.util.ArrayList;

import java.util.List;

import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;

public class GameObjectContainer {
	
	private List<GameObjects> gameObjects;
	private int objectCounter;
	
	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
		objectCounter = 0;
	}
	
	public void addObject(GameObjects gameObject) {
		if(isThereAnObject(gameObject.getX(),gameObject.getY()) == null) {	
			gameObjects.add(gameObject);
			gameObject.onEnter();
			objectCounter += 1;
		}	
	}
	
	public void updateObjects() {
		for(int i = 0; i < objectCounter; i++) {
			gameObjects.get(i).update();
		}
	}
	
	public void deleteObject () {
		List<GameObjects> list = new ArrayList<>() ;
		for(int i = 0; i < gameObjects.size(); i++) {  
			if(gameObjects.get(i).isAlive()) 
				list.add(gameObjects.get(i));	
			else {
				gameObjects.get(i).onDelete();
			}
		}
		objectCounter = list.size();
		gameObjects = list;
	}
	
	public GameObjects isThereAnObject(int x,int y) {
		GameObjects object = null;
		int i = 0;
		while(object == null && i < objectCounter) {
			if(gameObjects.get(i).getX() == x && gameObjects.get(i).getY() == y)
				object = gameObjects.get(i);
			else 
				i += 1;
		}
		return object;
	}
	
	public void clear() {
		for(int i = 0; i < objectCounter;i++) {
			gameObjects.get(i).onDelete();	 
		}
		gameObjects.clear();
		objectCounter = 0;
	}
	
	public void clearLastLane(int lane) {
		int i = 0;
		while(i < objectCounter) {
			if (gameObjects.get(i).getX() == lane) {
				gameObjects.get(i).onDelete();
				gameObjects.remove(i);
				objectCounter -= 1;
			}
			else
				i++;
		}
	}
 
	public String getSymbolfrom(int x, int y) {  
		String ret = "";
		for(int i  = 0; i < objectCounter;i++) {
			if(gameObjects.get(i).isInPosition(x, y)) {
				ret += gameObjects.get(i).toString() + " ";
			}	
		}
		return ret.trim();
	}
 }
