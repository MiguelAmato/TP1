package es.ucm.tp1.supercars.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;

public class Record {
	
	private final static String RECORD_FILE = "record.txt";
	
	public static void saveRecord(String level, Long time) throws InputOutputRecordException {
		try {
			StringBuilder str = conserveRecord(level);
			File file = new File(RECORD_FILE);
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write(level + ":" + time + "\n");
			buffer.write(str.toString());
			buffer.close();
		}
		catch(IOException ioe) {
			throw new InputOutputRecordException("[ERROR]: can't open file");
		}
	}
	
	private static StringBuilder conserveRecord(String level) throws IOException {
		StringBuilder str = new StringBuilder();
		File file = new File(RECORD_FILE);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			if (scanner.findInLine(level + ":") == null) {
 				str.append(scanner.nextLine() + "\n");
			}
			else {
				scanner.nextLine();
			}
				
		}
		scanner.close();
		return str;
	}
	
	public static void showRecord(String level) throws InputOutputRecordException {
		double record = findRecord(level);
		System.out.format(level + " record is %.2f s\n", (record / 1000));
	}
	
	public static double findRecord(String level) throws InputOutputRecordException {
		Long currentRecord = Long.MAX_VALUE;
		try {
			boolean found = false;
			File file = new File(RECORD_FILE);
			Scanner scanner = new Scanner(file);
			while (!found && scanner.hasNextLine()) {
				if (scanner.findInLine(level + ":") != null) {
					currentRecord = scanner.nextLong();
					found = true;
				}
				else {
					scanner.nextLine();
				}
			}
			if (!found) {
				System.out.println("Creating default record for level '" + level + "'");
				saveRecord(level, currentRecord);
			}
			scanner.close();
		}
		catch(IOException ioe) {
			throw new InputOutputRecordException("[ERROR]: can't open file, finishing game");
		}
		return currentRecord;
	}
}



 