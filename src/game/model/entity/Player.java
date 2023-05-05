package game.model.entity;

import game.view.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.model.KeyHandler;

/**
 * The Player class is does all the things 
 * related to the player such as draw update
 * it is also a subclass of Entity
 * @author blin2710
 *
 */
public class Player extends Entity
{

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	/**
	 * this is the constructor of Player class
	 * @param gp
	 * @param keyH
	 */
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
	/**
	 * sets default variables for the player class
	 */
	public void setDefaultValues()
	{
		//not where player is drawn on screen 
		//this is where they are in terms of the map
		worldX = (gp.maxWorldRow * gp.tileSize)/2;
		worldY = (gp.maxWorldCol * gp.tileSize)/2;
		speed = 4;
		direction = "down";
		
		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;
		
	}
	/**
	 * sets the players assets
	 */
	public void getPlayerImage()
	{
		
		up1 = setup("/player/player_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/player_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/player_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/player_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/player_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/player_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/player_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/player_right_2", gp.tileSize, gp.tileSize);
		
		
	}
	/**
	 * sets player attack assets
	 */
	public void getPlayerAttackImage()
	{
		attackUp1 = setup("player/boy_attack_up_1", gp.tileSize / 2, gp.tileSize / 2);
		attackUp2 = setup("player/boy_attack_up_2", gp.tileSize / 2, gp.tileSize / 2);
		attackDown1 = setup("player/boy_attack_down_1", gp.tileSize, gp.tileSize);
		attackDown2 = setup("player/boy_attack_down_2", gp.tileSize, gp.tileSize);
		attackLeft1 = setup("player/boy_attack_left_1", gp.tileSize, gp.tileSize);
		attackLeft1 = setup("player/boy_attack_left_2", gp.tileSize, gp.tileSize);
		attackRight1 = setup("player/boy_attack_right_1", gp.tileSize, gp.tileSize);
		attackRight2 = setup("player/boy_attack_right_2", gp.tileSize, gp.tileSize); 
		
	}
	/**
	 * updates the players position collision state etc
	 */
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
		
		//CHECK MONSTER COLLISION
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);
		
		//CHECK FOR EVENT
		gp.eHandler.checkEvent();
		
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
		
		//NEEDS TO BE OUT OF KEY IF
		if(invincible == true)
		{
			invincibleCounter++;
			if(invincibleCounter > 60)
			{
				invincible = false;
				invincibleCounter = 0;
			}
		}
			
	}
	/**
	 * does damage when monster collided with
	 * @param i
	 */
	public void contactMonster(int i)
	{
		if(i != 999)
		{
			if(invincible == false)
			{	
				life -= 1;
				invincible = true;
			}
		}
	}
	
	/**
	 * draws the frames that it got from update
	 */
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
 