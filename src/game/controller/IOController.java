package game.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;



public class IOController 
{
	public static void saveTextToFile(Controller app, String path, String text)
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
	
	public static String loadTextFromFile(Controller app, String pathAndFilename)
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
