package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.logic.Game;

public class GameSerializer {
	
	public static final String HEADER = "---- ROAD FIGHTER SERIALIZED ----";
	
	public static String serializer(Game game) {
		StringBuilder str = new StringBuilder();
		str.append(HEADER + "\n");
		str.append("Level: " + game.getLevel() + "\n");
		str.append("Cycles: " + game.getCycles() + "\n");
		str.append("Coins: " + game.getCoinCounter() + "\n");
		if (!game.getTest()) {
			str.append("EllapsedTime: 0\n" );
		}
 		str.append("Game Objects: " + "\n");
		for (int x = 0; x < game.getRoadLength(); x++) {
			for (int y = 0; y < game.getRoadWidth(); y++) {
				String positionToSerialize = game.positionToSerialize(x, y);
				if (!"".equals(positionToSerialize)) {  
					str.append(positionToSerialize);
				}
			}
		}
		return str.toString();
 	}
	
	/*public static void saveSerialize(Game game, String s_file) throws IOException {
		File file = new File(s_file);
		FileWriter writer = new FileWriter(file);
		BufferedWriter buffer = new BufferedWriter(writer);
		buffer.write("Super cars 3.0\n\n");
		buffer.write(serializer(game));
		buffer.close();
	}*/

	/*public static void readFile(String fileName) throws IOException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}*/
	
	public static void printGameSerialize(Game game) {
		System.out.println(serializer(game));
	}
}
