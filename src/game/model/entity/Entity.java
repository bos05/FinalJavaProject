package game.model.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.view.GamePanel;

public class Entity
{
	GamePanel gp;
	public int worldX, worldY, speed;
	//BufferedImage describes an image with an accessible buffer of image data
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	
	
	public Entity(GamePanel gp)
	{
		this.gp = gp;
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
	
	
	public void setAction() {}
	public void update() 
	{
		setAction();
		
		collisionOn = false;
		
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
				spriteCounter++;
				if(spriteCounter > 12)
				{
					if(spriteNum == 1)
					{
						spriteNum = 2;
					}
					else if(spriteNum == 2)
					{
						spriteNum = 1;
					}
					spriteCounter = 0;
				}
	}
}
