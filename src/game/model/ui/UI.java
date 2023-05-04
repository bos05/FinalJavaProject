package game.model.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.model.entity.Entity;
import game.model.object.HeartObject;
import game.view.GamePanel;
/**
 * In charge of on-screen text and User-Interface
 * @author blin2710
 *
 */
public class UI 
{
	GamePanel gp;
	Graphics2D g2;
	Font arial40;
	
	BufferedImage heart_full, heart_half, heart_blank;
	
	/**
	 * constructor for UI class
	 * @param gp
	 */
	public UI(GamePanel gp)
	{
		this.gp = gp;
		arial40 = new Font("Arial", Font.PLAIN, 40);
		
		//CREATE HUD OBJECTS
		Entity heart = new HeartObject(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
	}
	/**
	 * draws the UI elements
	 * @param g2
	 */
	public void draw(Graphics2D g2)
	{
	    this.g2 = g2; // Initialize the g2 instance variable
	    g2.setFont(arial40);
	    g2.setColor(Color.WHITE);
	    drawPlayerLife();
	}

	
	public void drawPlayerLife()
	{
		int x = gp.tileSize/2;
		int y = gp.tileSize/3;
		int i = 0;
		
		//DRAW BLANK VALUES
		while(i < gp.player.maxLife/2)
		{
			g2.drawImage(heart_blank, x, y, gp.tileSize, gp.tileSize, null);
			i++;
			x += gp.tileSize;
		}
		
		//VARIABLE RESET
		x = gp.tileSize/2;
		y = gp.tileSize/3;
		i = 0;
		
		while(i < gp.player.life)
		{
			g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize, null);
			i++;
			if( i < gp.player.life)
			{
				g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize, null);

			}
			i++;
			x += gp.tileSize;
		}
	}
}
