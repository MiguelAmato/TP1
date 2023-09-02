package es.ucm.tp1.control;

import java.util.Scanner;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;



public class Controller {
	
	private String option;
	
 	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	
	private static final String ERROR = "[ERROR]: ";
	
	private static final String DEBUG = "[DEBUG]: Executing";
	
	private static final String INVALID = "Invalid command!";
	
	private boolean exit = false;

	/* @formatter:off */
	private static final String[] HELP = new String[] {
		"\nAvailable commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobjet info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode\n",	
	};
	/* @formatter:off */

	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
	}

	public void printGame() {
		System.out.println(printer);
	}
	
	public void showExecution() {
		
		System.out.println(DEBUG + " " +  this.option);
		
	}

	public void run() {
		long ini = 0;
		long end = 0;
		double time;
		
		game.objectPlacement();
		printGame();
		
		while(!this.game.end() && !exit) {
			
			System.out.print(PROMPT);
			
			option = scanner.nextLine();
			
			if (game.getCycle() > 0) {
				end = System.currentTimeMillis();
				time = (double)(end - ini) / 1000;
				game.setTime(time);
			}

			showExecution();
			
			if(option.equalsIgnoreCase("q") || option.equalsIgnoreCase("a") || option.equalsIgnoreCase("n") || option.equalsIgnoreCase("none") || option.equalsIgnoreCase("")) {
				while(!this.game.update(option)) {
					System.out.println("\n" + INVALID + "\n" + PROMPT);
					option = scanner.nextLine();
				}
				
				game.setCycle(game.getCycle() + 1);
				
				if (game.getCycle() == 1) {
					ini = System.currentTimeMillis();
				}
				printGame();
			}
			
			else if(option.equalsIgnoreCase("h") || option.equalsIgnoreCase("help"))
				for(int i = 0; i < 9; i++) {
					System.out.println(HELP[i]);
				}
			
			else if (option.equalsIgnoreCase("i") || option.equalsIgnoreCase("info")) {
				this.game.showInfo();
			}
			
			else if(option.equalsIgnoreCase("e") || option.equalsIgnoreCase("exit")) {
				exit = true;
				game.setExit(exit);
			}
			
			else if(option.equalsIgnoreCase("r") || option.equalsIgnoreCase("reset")) {
				game.reset();
				game.objectPlacement();
				printGame();
			}
			
			else if(option.equalsIgnoreCase("t") || option.equalsIgnoreCase("test")) {
				game.setTest(true);
				printGame();
			}
			
			else {				
				System.out.println(ERROR + UNKNOWN_COMMAND_MSG);
			}
		}
		System.out.print("\n");
		printer.endMessage(); 
	}
 }
