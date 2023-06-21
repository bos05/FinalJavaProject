package game.model.monster;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.Random;

import game.model.entity.Entity;
import game.view.GamePanel;

public class Giant_Orange_Ranchu extends Entity {

	public Giant_Orange_Ranchu(GamePanel gp) {
		super(gp);
		
		type = type_monster;
		speed  = 1;
		maxLife = 8;
		life = maxLife;
		attack = 5;
		defense = 2;
		exp = 10;
		
		solidArea.x = 2;
		solidArea.y = 2;
		solidArea.width = 30 * gp.scale;
		solidArea.height = 23 * gp.scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}

	public void getImage() {
		
		up1 = setup("/monster/giant_ranchu_left_1", 30 * gp.scale, 23 * gp.scale); 
		down1 = setup("/monster/giant_ranchu_right_1", 30 * gp.scale, 23 * gp.scale);
		left1 = setup("/monster/giant_ranchu_left_1", 30 * gp.scale, 23 * gp.scale); 
		right1 = setup("/monster/giant_ranchu_right_1", 30 * gp.scale, 23 * gp.scale); 
	}
	
	public void draw(Graphics2D g2) {
		Composite originalComposite = g2.getComposite();
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	       worldY + gp.tileSize * 2 + 2  > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY){
			switch(direction)
			{
			case "up":
				if(spriteNum == 1)
				{
					image = up1;
				}
				
				break;
			case "down":
				if(spriteNum == 1)
				{
					image = down1;
				}
				
				break;
			case "left":
				if(spriteNum == 1)
				{
					image = left1;
				}				
				break;
			case "right":
				if(spriteNum == 1)
				{
					image = right1;
				}
				
				break;
			}
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

		
	g2.drawImage(image, screenX, screenY, 30 * gp.scale, 23 * gp.scale, null);
	g2.setComposite(originalComposite);
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
