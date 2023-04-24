package game.model.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.view.GamePanel;

public class TileManager 
{
	GamePanel gp;
	Tile[] tile;
	int mapTileNum [][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[20];//10 types of tiles
		mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];
		
			
		getTileImage();
		loadMap("/maps/map_1.txt");
	}
	
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
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_water_1.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_dirt_1.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/temple_1.png"));

			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/temple_2.png"));

			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/temple_3.png"));

			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/temple_4.png"));

			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/temple_5.png"));

			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/temple_6.png"));
			
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/temple_8.png"));
			
			
			

			

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
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		       worldY + gp.tileSize * 2 > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY)
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
			/*
			while(col < gp.maxScreenCol && row < gp.maxScreenRow)
			{
				g2.drawImage(tile[3].image, x, y, gp.tileSize, gp.tileSize, null);
				col++;
				x += gp.tileSize;
				
				if(col == gp.maxScreenCol)
				{
					col = 0;
					x = 0;
					row++;
					y += gp.tileSize;
				}
		}*///testing out new tiles
	}
}
