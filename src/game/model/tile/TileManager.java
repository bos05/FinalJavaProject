package game.model.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.model.UtilityTool;
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
		
		tile = new Tile[100];//50  types of tiles
		mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];
		
			
		getTileImage();
		loadMap("/maps/overworld_map_1.txt");
	}
	/**
	 * In this method is am loading the tiles onto the tile ArrayList
	 */
	public void getTileImage()
	{
		//WATER GRASS EDDGES
		setup(0, "water_grass_01", true);
		setup(1, "water_grass_02", true);
		setup(2, "water_grass_03", true);
		setup(8, "water_grass_04", true);
		setup(9, "water_grass_05", true);
		setup(10, "water_grass_06", true);
		setup(22, "water_grass_07", true);
		setup(23, "water_grass_08", true);
		
		//WATER
		setup(3, "water_01", true);
		setup(4, "water_02", true);
		
		//DIRT GRASS EDGES
		setup(5, "dirt_grass_01", false);
		setup(6, "dirt_grass_02", false);
		setup(7, "dirt_grass_03", false);
		setup(13, "dirt_grass_04", false);
		setup(14, "dirt_grass_05", false);
		setup(15, "dirt_grass_06", false);
		setup(30, "dirt_grass_07", false);
		setup(31, "dirt_grass_08", false);
		
		
		//DIRT
		setup(11, "dirt_01", false);
		setup(12, "dirt_02", false);
		
		//bridge
		setup(28, "bridge_01", false);
		setup(29, "bridge_02", false);
		setup(36, "bridge_03", false);
		setup(38, "bridge_04", false);
		setup(44, "bridge_05", false);
		setup(45, "bridge_06", false);
		setup(46, "bridge_07", false);
		setup(47, "bridge_08", false);
		setup(52, "bridge_09", false);
		setup(53, "bridge_10", false);
		setup(60, "bridge_11", false);
		setup(61, "bridge_12", false);
		setup(64, "bridge_13", false);
		setup(65, "bridge_14", false);
		setup(66, "bridge_15", false);
		setup(67, "bridge_16", false);
		setup(69, "bridge_17", false);
		setup(71, "bridge_18", false);
		setup(72, "bridge_19", false);
		setup(73, "bridge_20", false);
		setup(76, "bridge_21", false);
		setup(77, "bridge_22", false);
		setup(78, "bridge_23", false);
		setup(79, "bridge_24", false);
		setup(80, "bridge_25", false);
		setup(81, "bridge_26", false);
		setup(88, "bridge_27", false);
		setup(89, "bridge_28", false);
		
		//TREE	
		setup(16, "tree_01", true);
		setup(17, "tree_02", true);
		setup(18, "tree_03", true);
		setup(19, "tree_04", true);
		setup(24, "tree_05", true);
		setup(25, "tree_06", true);
		setup(26, "tree_07", true);
		setup(27, "tree_08", true);
		setup(32, "tree_09", true);
		setup(33, "tree_10", true);
		setup(34, "tree_11", true);
		setup(35, "tree_12", true);
		setup(40, "tree_13", true);
		setup(41, "tree_14", true);
		setup(42, "tree_15", true);
		setup(43, "tree_16", true);
		setup(48, "tree_17", true);
		setup(49, "tree_18" , true);
		setup(50, "tree_19" , true);
		setup(51, "tree_20" , true);
		setup(54, "tree_21" , true);
		setup(55, "tree_22" , true);
		setup(56, "tree_23" , true);
		setup(57, "tree_24" , true);
		setup(58, "tree_25" , true);
		setup(59, "tree_26" , true);
		setup(62, "tree_27" , true);
		setup(63, "tree_28" , true);
		
		//GRASS
		setup(20, "grass_01", false);
		setup(21, "grass_02", false);
			
			
			
			

	}
	/**
	 * this method goes through the 2D array and 
	 * figures out what tile is in what spot
	 * @param filePath
	 */
	
	public void setup(int index, String imagePath, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
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
					String numbers[] = line.split(",");
					
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
