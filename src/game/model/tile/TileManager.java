package game.model.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.view.GamePanel;
/**
 * this class displays the tiles that make up the background
 * @author blin2710
 *
 */
public class TileManager 
{
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum [][];
	/**
	 * constructor for TileManager class
	 * @param gp
	 */
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[50];//50  types of tiles
		mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];
		
			
		getTileImage();
		loadMap("/maps/map_1.txt");
	}
	/**
	 * In this method is am loading the tiles onto the tile ArrayList
	 */
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_grass_1.png"));
			
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_grass_2.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water_grass_down.png"));

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_bricks_1.png"));
			tile[3].collision = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_water_1.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_dirt_1.png"));
			

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * this method goes through the 2D array and 
	 * figures out what tile is in what spot
	 * @param filePath
	 */
	public void loadMap(String filePath)
	{
		try
		{
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row  = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				String line = br.readLine();
				
				while(col < gp.maxWorldCol)
				{
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
			br.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
	/**
	 * this method draws the tiles that are in the boundaries of the screen
	 * @param g2
	 */
	public void draw(Graphics2D g2)
	{
		int worldCol =  0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
		{
			int tileNum = mapTileNum[worldCol][worldRow];
			//everything from text file is stored in this
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize - 20< gp.player.worldX + gp.player.screenX &&
		       worldY + gp.tileSize * 2  +10 > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize * 2 + 10 < gp.player.worldY + gp.player.screenY)
			{
			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
			if(worldCol == gp.maxWorldCol)
			{
				worldCol = 0;
				worldRow++;

			}
			
		}
	
	}
}
