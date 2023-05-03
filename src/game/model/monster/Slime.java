package game.model.monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

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
		
		speed = 1;
		
		
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
	
		
		
		up1 = setup("/monster/slime_front_1");
		up2 = setup("/monster/slime_front_2");
		up3 = setup("/monster/slime_front_3");
		up4 = setup("/monster/slime_front_4");
		
		down1 = setup("/monster/slime_front_1");
		down2 = setup("/monster/slime_front_2");
		down3 = setup("/monster/slime_front_3");
		down4 = setup("/monster/slime_front_4");

		left1 = setup("/monster/slime_front_1");
		left2 = setup("/monster/slime_front_2");
		left3 = setup("/monster/slime_front_3");
		left4 = setup("/monster/slime_front_4");

		right1 = setup("/monster/slime_front_1");
		right2 = setup("/monster/slime_front_2");
		right3 = setup("/monster/slime_front_3");
		right4 = setup("/monster/slime_front_4");
		
	}
	
	
	/**
	 * draws the slime so that it will switch though it's 4 frames of animation
	 */
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
		g2.drawImage(image, screenX, screenY, 48, 48, null);
		}
	}

	
	
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
				if (collisionOn = true)
				{
					direction = "down";
				}
			}
			if(i > 25 && i <= 50)
			{
				direction = "down";
				if (collisionOn = true)
				{
					direction = "up";
				}
			}
			if(i > 50 && i <= 75)
			{
				direction = "left";
				if (collisionOn = true)
				{
					direction = "right";
				}
			}
			if(i > 75 && i <= 100)
			{
				direction = "right";
				if (collisionOn = true)
				{
					direction = "left";
				}
			}
			
			actionLockCounter = 0;
		}
		
	}
}
