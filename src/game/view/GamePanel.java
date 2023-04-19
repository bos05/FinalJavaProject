package game.view;

import game.controller.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	// screen settings 
	final int originalTileSize = 16;// 16x16 titles
	int scale = 3;
	
	final int tileSize = originalTileSize * scale;// actual tile size
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;//768 pixels
	final int screenHeight = tileSize * maxScreenRow;// 576 pixels
	 
	Thread gameThread;//Thread is useful for when you update something very frequently
	
	public GamePanel(Controller app)
	{
		super();
		
		//this.setSize(screenHeight, screenWidth);
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //can improve game performance?
		
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();//calls run mehod
	}
	
	
	@Override
	public void run()//where we will create game loop
	{
		while(gameThread != null)
		{
			System.out.println("The game loop is running");
			
			//1 update: update information such as character position
			update();
			//2 draw: draw the screens with the updated information
			repaint();//this is how you call paintComponent
		}
	}
	
	public void update()
	{
		
	}
	//built in java method
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(100, 100, tileSize, tileSize);
		
		g2.dispose();//saves memeory
	}

}
