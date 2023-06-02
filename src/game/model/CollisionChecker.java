package game.model;

import game.model.entity.Entity;
import game.view.GamePanel;
/**
 * this class makes collision possible for all things
 * @author blin2710
 *
 */
public class CollisionChecker 
{
	GamePanel gp;
	
	/**
	 * CollisionChecker constructor
	 * @param gp
	 */
	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
	}
	/**
	 * This methods check for collision in tiles
	 * it then makes it so that you can't run into it
	 * @param entity
	 */
	public void checkTile(Entity entity)
	{
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		switch(entity.direction)
		{
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "upRight":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "upLeft":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "downLeft":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "downRight":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
			
		}
		
		
		
	}
	public int checkEntity(Entity entity, Entity[] target)
	{
		int index = 999;
		for(int i = 0; i < target.length;i++)
		{
			if (target[i] != null)
			{
				//get entity's solid are position
			entity.solidArea.x = entity.worldX + entity.solidArea.x;
			entity.solidArea.y = entity.worldY + entity.solidArea.y;
			//gets the objects solid area position
			target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
			target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
			
			switch(entity.direction) 
			{
			case "up": 
				entity.solidArea.y -= entity.speed;
				break;
			case "down":
				entity.solidArea.y += entity.speed;
				break;
			case "left":
				entity.solidArea.x -= entity.speed;
				break;
			case"right":
				entity.solidArea.x += entity.speed;
				break;
			case"upRight":
				entity.solidArea.x += entity.speed;
				entity.solidArea.y -= entity.speed;
				break;
			case"upLeft":
				entity.solidArea.x -= entity.speed;
				entity.solidArea.y -= entity.speed;
				break;
			case"downLeft":
				entity.solidArea.x -= entity.speed;
				entity.solidArea.y += entity.speed;
				break;
			case"downRight":
				entity.solidArea.x += entity.speed;
				entity.solidArea.y += entity.speed;
				break;
			}

			if(entity.solidArea.intersects(target[i].solidArea))
			{
				if(target[i] != entity)
				{
					entity.collisionOn = true;
					index = i;  
				}
				
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			target[i].solidArea.x = target[i].solidAreaDefaultX;
			target[i].solidArea.y = target[i].solidAreaDefaultY;

			}
			
		}
		
		return index;
	}
	
	/**
	 * Checks for collision with the 
	 * player and adjust according
	 * @param entity
	 */
	public boolean checkPlayer(Entity entity)
	{
		boolean contactPlayer = false;
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		//gets the objects solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		
		switch(entity.direction) 
		{
		case "up": 
			entity.solidArea.y -= entity.speed;
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case"right":
			entity.solidArea.y += entity.speed;
			break;	
		}
		if(entity.solidArea.intersects(gp.player.solidArea))
		{
			if(gp.player != entity)
			{
				entity.collisionOn = true;
				contactPlayer = true;
			}
			
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;

		}	

}
 