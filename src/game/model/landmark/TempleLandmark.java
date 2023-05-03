package game.model.landmark;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.model.entity.Entity;
import game.view.GamePanel;
/**
 * this class used to make objects on the map
 * that are bigger than single tile squares
 * it is also a subclass for Entity
 * @author blin2710
 *
 */
public class TempleLandmark extends Entity
{
	/**
	 * constructor for TempleLandMarker
	 * @param gp
	 */
	public TempleLandmark(GamePanel gp)
	{
		super(gp);
		
		name = "Temple";
		down1 = setup("/landmarks/temple_full");
		
		
	}
	/**
	 * this draws the landmark regardless of weather the player sees it
	 */
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		
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
		g2.drawImage(image, screenX, screenY, gp.tileSize * 18, gp.tileSize * 18, null);
		
	}
}
