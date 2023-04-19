package game.view;

import game.controller.Controller;
import game.model.KeyHandler;

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
	
	int FPS = 60;
	
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;//Thread is useful for when you update something very frequently
	
	
	// set player's defaut position
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel(Controller app)
	{
		super();
		
		//this.setSize(screenHeight, screenWidth);
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //can improve game performance?
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
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
			double drawInterval = 1000000000/FPS; //0.01666666 seconds
			double nextDrawTime = System.nanoTime() + drawInterval;
			
			
			//1 update: update information such as character position
			update();
			//2 draw: draw the screens with the updated information
			repaint();//this is how you call paintComponent
			
			
			try 
			{
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0)
				{
					remainingTime = 0;
				}
				
				Thread.sleep(((long) remainingTime));
				
				
				nextDrawTime += drawInterval;
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	public void update()
	{
		if(keyH.upPressed == true)
		{
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed == true)
		{
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true)
		{
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true)
		{
			playerX += playerSpeed;
		}
	}
	//built in java method
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();//saves memory
	}

}
