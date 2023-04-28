package game.model.entity;

import game.view.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.model.KeyHandler;


public class Player extends Entity
{

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (35);
		screenY = gp.screenHeight/2 - (55);
		
		solidArea = new Rectangle(8, 16, 32, 36);
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues()
	{
		//not where player is drawn on screen 
		//this is where they are in terms of the map
		worldX = (gp.maxWorldRow * gp.tileSize)/2;
		worldY = (gp.maxWorldCol * gp.tileSize)/2;
		speed = 4;
		direction = "down";
		
	}
	public void getPlayerImage()
	{
		
		up1 = setup("/player/player_up_1");
		up2 = setup("/player/player_up_2");
		down1 = setup("/player/player_down_1");
		down2 = setup("/player/player_down_2");
		left1 = setup("/player/player_left_1");
		left2 = setup("/player/player_left_2");
		right1 = setup("/player/player_right_1");
		right2 = setup("/player/player_right_2");
		
		
	}
	
	public void update()
	{
		//only does thins inside when up, down, left, or right are pressed
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
		
		if(keyH.upPressed == true)
		{
			direction = "up";		
		}
		else if(keyH.downPressed == true)
		{
			direction= "down";
		}
		else if(keyH.leftPressed == true)
		{
			direction = "left";
		}
		else if(keyH.rightPressed == true)
		{
			direction = "right";
		}
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);;
		
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
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		
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
 