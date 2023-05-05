package game.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

import game.view.GamePanel;


/**
 * this class has the fileIO methods
 *  used throughout the game
 * @author Boss_
 *
 */
public class IOController 
{
	/**
	 * This method saves the given text to a file with a filename
	 *  generated based on the current date and time.
	 * @param gamePanel
	 * @param path
	 * @param text
	 */
	public static void saveTextToFile(GamePanel gamePanel, String path, String text)
	{
		String filename= buildDateBasedFilename(path);
		
		try(Scanner textScanner = new Scanner(text);
			PrintWriter textWriter = new PrintWriter(filename))
		{
			while (textScanner.hasNextLine())
			{
				String line = textScanner.nextLine();
				textWriter.println(line);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();

		}
		
	}
	/**
	 * This method creates a file name based
	 *  on current date and time and returns it.
	 * @param path
	 * @return
	 */
	public static String buildDateBasedFilename(String path)
	{
		String filename = path;
		
		LocalDateTime currentTime = LocalDateTime.now();
		
		filename += currentTime.getMonth() + " " + currentTime.getDayOfMonth();
		int minuteTime = currentTime.getMinute();
		String minutes = "0";
		
		if(minuteTime < 10)
		{
			minutes += minuteTime;
		}
		else
		{
			minutes = "" + minuteTime;
		}
		
		filename += " " + currentTime.getHour() + "_" + minutes + "/txt";
		
		return path;
	}
	/**
	 * This method loads the contents of a text file and returns 
	 * it as a string, with error handling in case the file is not found.
	 * @param gamePanel
	 * @param pathAndFilename
	 * @return
	 */
	public static String loadTextFromFile(GamePanel gamePanel, String pathAndFilename)
	{
		String fileContents = "";
		
		File source = new File(pathAndFilename);
		
		try (Scanner textScanner = new Scanner(source))
		{
			while(textScanner.hasNextLine())
			{
				fileContents += textScanner.nextLine() + "n";
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();

			fileContents = "No content loaded :(";
		}
		
		return fileContents;
	}

}
