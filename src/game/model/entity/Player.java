package game.model.entity;

import game.view.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.model.KeyHandler;
import game.model.object.OBJ_Sakura_Katana;
import game.model.object.OBJ_Basic_Armor_Piece;

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
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	/**
	 * sets default variables for the player class
	 */
	public void setDefaultValues()
	{
		//not where player is drawn on screen 
		//this is where they are in terms of the map
		worldX = (gp.tileSize * 8);
		worldY = (gp.tileSize * 8);
		speed = 4;
		direction = "down";
		
		//PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		strength = 1;//damage affecting
		dexterity = 1;//defense affecting
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new OBJ_Sakura_Katana(gp);
		currentShield = new OBJ_Basic_Armor_Piece(gp);
		attack = getAttack();
		defense = getDefense();
	}
	public int getAttack() {
		return attack = strength * currentWeapon.attackValue;
		
	}
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	/**
	 * sets the players assets
	 */
	public void getPlayerImage()
	{
		
		up1 = setup("/player/player_up_1", gp.tileSize, 18 * gp.scale);
		up2 = setup("/player/player_up_2", gp.tileSize, 18 * gp.scale);
		down1 = setup("/player/player_down_1", gp.tileSize, 16 * gp.scale);
		down2 = setup("/player/player_down_2", gp.tileSize, 16 * gp.scale);
		left1 = setup("/player/player_left_1", gp.tileSize, 18 * gp.scale);
		left2 = setup("/player/player_left_2", gp.tileSize, 18 * gp.scale);
		right1 = setup("/player/player_right_1", gp.tileSize, 18 * gp.scale);
		right2 = setup("/player/player_right_2", gp.tileSize, 18 * gp.scale);
		
		
	}
	/**
	 * sets player attack assets
	 */
	public void getPlayerAttackImage()
	{
		attackUp1 = setup("/player/player_attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/player/player_attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/player/player_attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/player/player_attack_down_2", gp.tileSize, 38 * gp.scale);
		attackLeft1 = setup("/player/player_attack_left_1", gp.tileSize * 2, gp.tileSize * 2);
		attackLeft2 = setup("/player/player_attack_left_2", gp.tileSize * 2, gp.tileSize * 2);
		attackRight1 = setup("/player/player_attack_right_1", gp.tileSize, gp.tileSize * 2);
		attackRight2 = setup("/player/player_attack_right_2", gp.tileSize * 2, (gp.tileSize * 2 - 2)); 
		
	}
	/**
	 * updates the players position collision state etc
	 */
	public void update()
	{
		if(attacking == true)
		{
			attacking();
		}
		//only does thins inside when up, down, left, or right are pressed
		if(!attacking) {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true)
		{
			if(keyH.upPressed == true){direction = "up";}
			if(keyH.downPressed == true){direction= "down";}
			if(keyH.leftPressed == true){direction = "left";}
			if(keyH.rightPressed == true){direction = "right";}
			if(keyH.rightPressed == true && keyH.upPressed == true){direction = "upRight";}
			if(keyH.leftPressed == true && keyH.upPressed == true){direction = "upLeft";}
			if(keyH.leftPressed == true && keyH.downPressed == true){direction = "downLeft";}
			if(keyH.rightPressed == true && keyH.downPressed == true){direction = "downRight";}
			
			 
			
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			attack(monsterIndex);
			//CHECK FOR EVENT
			gp.eHandler.checkEvent();
			
			
			
			// IF COLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPressed == false && !attacking)
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
				case "upRight":
					worldX += speed * .714;
					worldY -= speed * .714;
					break;
				case "upLeft":
					worldX -= speed * .714;
					worldY -= speed * .714;
					break;
				case "downLeft":
					worldX -= speed * .714;
					worldY += speed * .714;
					break;
				case "downRight":
					worldX += speed * .714;
					worldY += speed * .714;
					break;
				
					
				}
			}
			
			gp.keyH.enterPressed = false;
			
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
			
	}
	
	public void attacking()
	{
		spriteCounter++;
		
		if(spriteCounter <= 5)
		{
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25)
		{
			spriteNum = 2;
			
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			switch (direction)
			{
			case "up":worldY -= attackArea.height;break;
			case "down": worldY += attackArea.height;break;
			case "left": worldX -= attackArea.width;break;
			case "right": worldX += attackArea.width;break;
			}
			//attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			//check monster collision with the update worldX, worldY, and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
		}
		if(spriteCounter > 25)
		{
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;

		}

	}
	
	public void attack(int i)
	{
		if(gp.keyH.enterPressed == true)
		{
			System.out.println("Enter");
			attacking = true;
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
	
	public void damageMonster(int i)
	{
		if(i != 999)
		{
			if(gp.monster[i].invincible == false)
			{
				System.out.println("hit");
				gp.monster[i].life -= 1;
				gp.monster[i].damageReaction();
				gp.monster[i].invincible = true;
				
				if(gp.monster[i].life <= 0)
				{
					gp.monster[i].dying = true;
				}
			}
		}
	}
	
	/**
	 * draws the frames that it got from update
	 */
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction)
		{
		case "up":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}

			}
			
			else if(attacking == true)
			{
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}
			
			break;
		case "down":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}

			}
			
			else if(attacking == true)
			{
				tempScreenY = screenY - (gp.scale * 7);
				if(spriteNum == 1) {image = attackDown1;}
				
				if(spriteNum == 2) {image = attackDown2;}
			}
			break;
		case "left":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}

			}
			
			else if(attacking == true)
			{
				tempScreenX = screenX - gp.tileSize;
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
			}
			break;
		case "right":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}

			}
			
			else if(attacking == true)
			{
				tempScreenY = screenY - (gp.scale * 14);
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
			}
			break;
		case "upRight":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
			}
			else if(attacking == true)
			{
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}
			break;
		case "upLeft":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
			}
			else if(attacking == true)
			{
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}
			break;
		case "downLeft":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}

			}
			
			else if(attacking == true)
			{
				tempScreenY = screenY - (gp.scale * 7);
				if(spriteNum == 1) {image = attackDown1;}
				
				if(spriteNum == 2) {image = attackDown2;}
			}
			break;
		case "downRight":
			if(attacking == false)
			{
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}

			}
			
			else if(attacking == true)
			{
				tempScreenY = screenY - (gp.scale * 7);
				if(spriteNum == 1) {image = attackDown1;}
				
				if(spriteNum == 2) {image = attackDown2;}
			}
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
	}

	
}