package game.view;

import game.controller.Controller;
import game.model.AssetSetter;
import game.model.CollisionChecker;
import game.model.EventHandler;
import game.model.KeyHandler;
import game.model.entity.Entity;
import game.model.entity.Player;
import game.model.monster.Slime;
import game.model.tile.TileManager;
import game.model.ui.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import game.controller.IOController;
/**
 *This is the main class that stores a lot of the methods that
 * will do things like update and draw the things on screen
 * @author blin2710
 *
 */
public class GamePanel extends JPanel implements Runnable
{
	// screen settings 
	final int originalTileSize = 16;// 16x16 titles
	public int scale = 3;
	
	public final int tileSize = originalTileSize * scale;// actual tile size
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;//768 pixels
	public final int screenHeight = tileSize * maxScreenRow;// 576 pixels

	//WORLD SETTINGS
	public final int maxWorldCol = 125;
	public final int maxWorldRow = 75;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int FPS = 60;
	
	
	public TileManager tileM;
	public KeyHandler keyH;
	Thread gameThread;//Thread is useful for when you update something very frequently
	public CollisionChecker cChecker;
	public AssetSetter aSetter;
	public UI ui;
	public EventHandler eHandler;
	public Player player;
	public Entity landmark[];
	public Entity monster[];
	public IOController fileIO;
	
	ArrayList<Entity> entityList;
	
	
	// set player's default position
	int playerX = 100;
	int playerY = 100;
	double playerSpeed = 4;
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	
	/**
	 * Constructor for the GamePanel class
	 * 
	 * @param app
	 */
	public GamePanel(Controller app)
	{
		super();
		
		//initialing these in the constructor is much better that before it
		//this way we are able to know if they are actually initialized
		tileM = new TileManager(this);
		keyH = new KeyHandler(this);
		cChecker = new CollisionChecker(this);
		aSetter = new AssetSetter(this);
		ui = new UI(this);
		eHandler = new EventHandler(this);
		player = new Player(this,keyH);
		landmark = new Entity[10];
		monster = new Entity[20];
		entityList = new ArrayList<>();
		fileIO = new IOController();
		
		
		
		
		
		
		
		//this.setSize(screenHeight, screenWidth);
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //can improve game performance?
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	/**
	 * called at the beginning of the game so
	 * everything in here are the first things to be compiled
	 */
	public void setupGame()
	{
		aSetter.setObject();
		aSetter.setMonster();
		gameState = titleState;
	}
	
	/**
	 * Starts the main thread the game will use
	 */
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();//calls run method
	}
	
	

	/**
	 * Run() is called every 1/60 second to call other 
	 * methods that will update and draw
	 */
	public void run() 
	{
	    double drawInterval = 1000000000/FPS;
	    double delta = 0;
	    long lastTime = System.nanoTime();
	    long currentTime;
	    long timer = 0;
	    long drawCount = 0;
	    long lag = 0;

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
	        else
	        {
	            try {
	                Thread.sleep(1);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        lag = currentTime - lastTime;
	        if(lag > drawInterval)
	        {
	            lastTime = currentTime;
	        }

	        if(timer >= 1000000000)
	        {
	            System.out.println("FPS:" + drawCount);
	            drawCount = 0;
	            timer = 0;
	        }
	    }
	}

	/**
	 * will update all the entity's in the game to decide
	 *  were they're going if the have collision etc
	 */
	public void update()
	{
		if (gameState == playState) 
		{
			player.update();
			
			for (int i = 0; i < monster.length; i++) 
			{
				if (monster[i] != null) 
				{
					if (monster[i].alive == true)
					{
						monster[i].update(30, 4);
					} else
					{
						monster[i] = null;
					}
				}
			}
		}
		if (gameState == pauseState) {
			// Handle pause state
		}
	}

	
	/**
	 * this method is in charge of repainting all of the things 
	 * that are on screen like monsters landmarks and tiles 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		if (gameState == titleState)
		{
			ui.draw(g2);
		}
		else 
		{
			//order is important on wich is drawn first
			tileM.draw(g2);
			
			//BOTTOM RENDER
			for(int i = 0; i < landmark.length; i++)
			{
				if(landmark[i] != null)
				{
					entityList.add(landmark[i]);
				}
			}
			
			entityList.add(player);
			
			for(int i = 0; i < monster.length; i++)
			{
				if(monster[i] != null)
				{
					entityList.add(monster[i]);
				}
			}
			
			
			//TOP RENDER
			
			
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
			ui.draw(g2);
			
			//empty list
			entityList.clear();
			
			g2.dispose();
		}
		
		
	}
	/**
	 * calls saveTextToFile
	 * @param path
	 * @param contents
	 */
	public void save(String path, String contents)
	{
		fileIO.saveTextToFile(this, path, contents);
	}
	/**
	 * loads previous files
	 * @param path
	 * @return
	 */
	public String load(String path)
	{
		String results = fileIO.loadTextFromFile(this, path);
		
		return results;
	}

}

	
	


