package game.model.monster;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


import game.model.entity.Entity;
import game.view.GamePanel;
/**
 * this class makes the things needed to have a slime 
 * It is also a subclass of Entity
 * @author blin2710
 *
 */
public class Slime extends Entity
{
	public BufferedImage up3, up4, down3, down4, left3, left4, right3, right4;
	
	int slimeScale;
	

	/**
	 * constructor for Slime
	 * @param gp
	 */
	public Slime(GamePanel gp)
	{
		super(gp);
		
		type = type_monster;
		speed = 1;
		maxLife = 4;
		life = maxLife;
		attack = 3;
		defense = 0;
		exp = 4;
		
		solidArea.x = 3;
		solidArea.y = 3;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
		getImage();

	}
	/**
	 * gets the assets for the slime
	 */
	public void getImage()
	{
	
		
		
		up1 = setup("/monster/slime_front_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/slime_front_2", gp.tileSize, gp.tileSize);
		up3 = setup("/monster/slime_front_3", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/slime_front_4", gp.tileSize, gp.tileSize);
		
		down1 = setup("/monster/slime_front_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/slime_front_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/slime_front_3", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/slime_front_4", gp.tileSize, gp.tileSize);

		left1 = setup("/monster/slime_front_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/slime_front_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/slime_front_3", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/slime_front_4", gp.tileSize, gp.tileSize);

		right1 = setup("/monster/slime_front_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/slime_front_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/slime_front_3", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/slime_front_4", gp.tileSize, gp.tileSize);
		
	}
	
	
	/**
	 * draws the slime so that it will switch though it's 4 frames of animation
	 */
	public void draw(Graphics2D g2)
	{
		Composite originalComposite = g2.getComposite();
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
				if(spriteNum ==3)
				{
					image = up3;
				}
				if(spriteNum == 4)
				{
					image = up4;
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
				if(spriteNum == 3)
				{
					image = down3;
				}
				if(spriteNum == 4)
				{
					image = down4;
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
				if(spriteNum == 3)
				{
					image = left3;
				}
				if(spriteNum == 4)
				{
					image = left4;
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
				if(spriteNum == 3)
				{
					image = right3;
				}
				if(spriteNum == 4)
				{
					image = right4;
				}
				break;
			}
			

			if(type == type_monster && hpBarOn)
			{
				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarValue = oneScale*life;
				
				g2.setColor(new Color(40, 11, 11));
				g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
				
				g2.setColor(new Color(105, 25, 25));
				g2.fillRect(screenX,  screenY - 15,  (int)hpBarValue,  10);
				
				hpBarCounter++;
				
				if(hpBarCounter > 600)
				{
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			
			if(invincible == true)
			{
				hpBarCounter = 0;
				hpBarOn = true;
				changeAlpha(g2, 0.4f);
			}
			
			if(dying == true)
			{
				dyingAnimation(g2);
			}

			
		g2.drawImage(image, screenX, screenY, 48, 48, null);
		g2.setComposite(originalComposite);
		}
	}

	
	/**
	 * this is a very basic way to get my slime to move around
	 * it picks a random direction and goes there for 2 seconds
	 */
	public void setAction()
	{
		actionLockCounter++;
		
		if(actionLockCounter == 120)
		{
			Random random = new Random();
			int i = random.nextInt(100) + 1;//picks 1 - 100
			
			if(i <= 25)
			{
				direction = "up";
				
			}
			if(i > 25 && i <= 50)
			{
				direction = "down";
				
			}
			if(i > 50 && i <= 75)
			{
				direction = "left";
				
			}
			if(i > 75 && i <= 100)
			{
				direction = "right";
				
			}
			
			actionLockCounter = 0;
		}
		
	}
	
	public void damageReaction()
	{
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
}
