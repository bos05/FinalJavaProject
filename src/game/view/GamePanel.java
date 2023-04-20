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
	
	
	@Override
	public void run() {
	    long lastTime = System.nanoTime();
	    int frameCount = 0;
	    int fps = 0;
	    
	    while (gameThread != null) {
	        double drawInterval = 1000000000 / FPS; // 0.01666666 seconds
	        double nextDrawTime = System.nanoTime() + drawInterval;
	        
	        // 1. update: update information such as character position
	        update();
	        
	        // 2. draw: draw the screens with the updated information
	        repaint(); // this is how you call paintComponent
	        
	        try 
	        {
	            double remainingTime = nextDrawTime - System.nanoTime();
	            remainingTime = remainingTime / 1000000;
	            
	            if (remainingTime < 0)
	            {
	                remainingTime = 0;
	            }
	            
	            Thread.sleep((long) remainingTime);
	            nextDrawTime += drawInterval;
	        }
	        catch (InterruptedException e) 
	        {
	            e.printStackTrace();
	        }
	        
	        // Calculate FPS
	        frameCount++;
	        if (System.nanoTime() - lastTime >= 1000000000) {
	            fps = frameCount;
	            frameCount = 0;
	            lastTime = System.nanoTime();
	            System.out.println("FPS: " + fps);
	        }
	    }
	}

	
	public void update()
	{
		player.update();
	}
	//built in java method
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();//saves memory
	}

}
