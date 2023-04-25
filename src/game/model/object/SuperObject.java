package game.model.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.view.GamePanel;

public class SuperObject 
{
	
	public BufferedImage image;
	public String name;
	public boolean collision  = false;
	public int worldX, worldY; 
	/**
	 * Decides whether or not objects are on screen then
	 * it will draw the object on screen
	 * @param g2 Referencing Graphics2D
	 * @param gp Referencing GamePanel
	 */
	public void draw(Graphics2D g2, GamePanel gp)
	{
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	       worldY + gp.tileSize * 2 > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY)
		{
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
