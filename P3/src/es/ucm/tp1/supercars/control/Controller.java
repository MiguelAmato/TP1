package es.ucm.tp1.supercars.control;

import java.util.Scanner;
import es.ucm.tp1.supercars.control.commands.Command;
import es.ucm.tp1.supercars.control.exceptions.GameException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GamePrinter;

public class Controller {
	
	private static final String PROMPT = "Command > "; 
 		
	private Game game;
	private Scanner scanner;
	private GamePrinter printer;
	
	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
	}
	
	public void run() {
		boolean refreshDisplay = false;
		game.initializeGame();
		printGame();

		while (!game.isFinished()){
			if (refreshDisplay ) {
				printGame();
			}
			refreshDisplay = false;
			System.out.print(PROMPT);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println("\n[DEBUG] Executing: " + s);
			Command command;
			try {
				command = Command.getCommand(parameters); 
				refreshDisplay = command.execute(game);
			} 
			catch (GameException ex) {
				System.out.println(ex.getMessage()); 
			}
		}
		if(!game.getExit()) {
			printGame();
		}
 		printer.endMessage(); 
	}

	public void printGame() {
		System.out.println(printer);
	}
 }
