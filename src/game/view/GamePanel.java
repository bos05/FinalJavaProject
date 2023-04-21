package game.view;

import game.controller.Controller;
import game.model.KeyHandler;


import game.model.entity.Player;


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
	
	public final int tileSize = originalTileSize * scale;// actual tile size
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;//768 pixels
	final int screenHeight = tileSize * maxScreenRow;// 576 pixels


	
	int FPS = 60;
	
	

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;//Thread is useful for when you update something very frequently
	Player player = new Player(this,keyH);
	
	
	
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
	
	


	public void run() 
	{
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000)
			{
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	
	}
	public void update()
	{
		player.update();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
	}

	
	

}