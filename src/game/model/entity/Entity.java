package game.model.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.view.GamePanel;

public class Entity
{
	public GamePanel gp;
	public int worldX, worldY, speed;
	//BufferedImage describes an image with an accessible buffer of image data
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	
	public String direction = "down";
	
	public int spriteCounter ;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public BufferedImage image, image1, image3;
	public String name;
	public boolean collision  = false;
	int framesPerSprite = 0;
	int spriteFrameCounter = 0;

	public int maxLIde;
	public int life;
	
	public Entity(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setAction() {}
	public void update(int framesPerSprite, int totalFrames) 
	{
		setAction();
		
		collisionOn = false;
		
		gp.cChecker.checkTile(this);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkPlayer(this);
		
		// IF COLISION IS FALSE, PLAYER CAN MOVE
				if(collisionOn == false )
				{
					switch(direction) {
					case "up":
						worldY -= speed;
						break;
					case "down":
						worldY += speed;
						break;
					case "left":
						worldX-= speed;
						break;
					case "right":
						worldX += speed;
						break;
						
					}
				}
			
				//waits until spriteCounter gets to 12(12 frames) then it switches the spriteNum
				for (int i = 0; i < totalFrames; i++) {
				    // Display current sprite frame

				    spriteCounter++;
				    spriteFrameCounter++;
				    if (spriteFrameCounter > framesPerSprite) {
				        spriteNum = (spriteNum % totalFrames) + 1; // cycle through frames
				        spriteFrameCounter = 0;
				    }
				    if (spriteCounter > totalFrames) 
				    {
				        spriteCounter = 0;
				    }
				}
				
	}
	
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	       worldY + gp.tileSize * 2 + 2  > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY)
		{
			switch(direction)
			{
			case "up":
				if(spriteNum == 1)
				{
					image = up1;
				}
				if(spriteNum == 2)
				{
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1)
				{
					image = down1;
				}
				if(spriteNum == 2)
				{
					image = down2;
				}
				break;
			case "left":
				if(spriteNum == 1)
				{
					image = left1;
				}
				if(spriteNum == 2)
				{
					image = left2;
				}
				break;
			case "right":
				if(spriteNum == 1)
				{
					image = right1;
				}
				if(spriteNum == 2)
				{
					image = right2;
				}
				break;
			}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	
	public BufferedImage setup(String imagePath)
	{
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return image;
	}
	
	

}
