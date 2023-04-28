package game.view;

import game.controller.Controller;
import game.model.AssetSetter;
import game.model.CollisionChecker;
import game.model.KeyHandler;
import game.model.entity.Entity;
import game.model.entity.Player;
import game.model.monster.Slime;
import game.model.object.SuperObject;
import game.model.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	// screen settings 
	final int originalTileSize = 16;// 16x16 titles
	int scale = 3;
	
	public final int tileSize = originalTileSize * scale;// actual tile size
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;//768 pixels
	public final int screenHeight = tileSize * maxScreenRow;// 576 pixels

	//WORLD SETTINGS
	public final int maxWorldCol = 100;
	public final int maxWorldRow = 75;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int FPS = 60;
	
	
	public TileManager tileM;
	KeyHandler keyH;
	Thread gameThread;//Thread is useful for when you update something very frequently
	public CollisionChecker cChecker;
	public AssetSetter aSetter;
	public Player player;
	public SuperObject obj[];
	public Entity monster[];
	
	
	ArrayList<Entity> entityList = new ArrayList<>();
	
	
	// set player's default position
	
	int playerX = 100;
	int playerY = 100;
	double playerSpeed = 4;
	
	
	
	public GamePanel(Controller app)
	{
		super();
		
		//initialing these in the constructor is much better that before it
		//this way we are able to know if they are actually initialized
		tileM = new TileManager(this);
		keyH = new KeyHandler();
		cChecker = new CollisionChecker(this);
		aSetter = new AssetSetter(this);
		player = new Player(this,keyH);
		obj = new SuperObject[10];
		monster = new Entity[20];
		
		
		
		
		
		
		
		//this.setSize(screenHeight, screenWidth);
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //can improve game performance?
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	

	public void setupGame()
	{
		aSetter.setObject();
		aSetter.setMonster();
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();//calls run method
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
		
		for(int i = 0; i < monster.length;i ++)
		{
			if(monster[i] != null)
			{
				monster[i].update(30, 4);
			}
		}
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		//order is important on wich is drawn first
		tileM.draw(g2);
		
		
		entityList.add(player);
		for(int i = 0; i < monster.length; i++)
		{
			if(monster[i] != null)
			{
				entityList.add(monster[i]);
			}
		}
		//sort
		Collections.sort(entityList, new Comparator<Entity>()
		{
			@Override
			public int compare(Entity e1, Entity e2)
			{
				int result = Integer.compare(e1.worldY, e2.worldY);
				return 0;
			}
		});

		//draw entities
		for(int i = 0 ; i < entityList.size(); i++)
		{
			entityList.get(i).draw(g2);
		}
		
		//empty list
		for(int i = 0 ; i < entityList.size(); i++)
		{
			entityList.remove(i);
		}
		
		g2.dispose();
	}

	
	

}
