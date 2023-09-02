package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.supercars.logic.gameobjects.GameObjects;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.logic.actions.ThunderAction;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;
import es.ucm.tp1.supercars.control.*;


public class GameObjectGenerator {

	public static void generateGameObjects(Game game, Level level) {
		for (int x = game.getVisibility() / 2; x < game.getRoadLength(); x++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.getCoinFrequency());
			if (level.hasAdvancedObjects()) {
					game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.getAdvObjFreq());
					game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.getAdvObjFreq());
					if (!SuperCoin.hasSuperCoin()) {
						game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level.getAdvObjFreq());
					}
					game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.getAdvObjFreq());
					game.tryToAddObject(new Pedestrian(game, x, 0), level.getAdvObjFreq());
			}
		}
	}
	
	public static void forceAdvanceObject(Game game, int id, int x) {
		GameObjects o = null;
		switch (id) {
			case 1:
				o = new Wall(game, x, game.getRandomLane());
				break;
			case 2:
				o = new Turbo(game, x, game.getRandomLane());
				break;
			case 3:
				o = new SuperCoin(game, x, game.getRandomLane());
				break;
			case 4:
				o = new Truck(game, x, game.getRandomLane());
				break;
			case 5: 
				o = new Pedestrian(game, x, 0);
				break;
		}
		game.forceAddObject(o);
		
	}
	
	public static void generateRuntimeObjects(Game game) { 
		if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction());
		}
	}

	public static void reset(Level level) { 
		Obstacle.reset();
		Coin.reset(); 
		if (Level.ADVANCED.equals(level)) {
			Wall.reset();
			Turbo.reset();
			Truck.reset();
			Pedestrian.reset();
		}
		SuperCoin.reset();
	}
	
	public static StringBuilder getInfo() {
		StringBuilder info = new StringBuilder();
		info.append(Player.INFO + "\n");
		info.append(Coin.INFO + "\n");
		info.append(Obstacle.INFO +"\n");
		info.append(Grenade.INFO +"\n");
		info.append(Wall.INFO +"\n");
		info.append(Turbo.INFO +"\n");
		info.append(SuperCoin.INFO +"\n");
		info.append(Truck.INFO +"\n");
		info.append(Pedestrian.INFO);
		return info;
	}
}
