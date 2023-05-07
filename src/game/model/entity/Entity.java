package game.model.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.model.UtilityTool;
import game.view.GamePanel;
/**
 * this is the superclass to a tone of other classes
 * it hold many common methods and variables like update and draw
 * @author blin2710
 *
 */
public class Entity
{
	public GamePanel gp;
	public int worldX, worldY, speed;
	//BufferedImage describes an image with an accessible buffer of image data
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1,
	attackLeft2, attackRight1, attackRight2;
	
	public String direction = "down";
	
	public int spriteCounter ;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public int invincibleCounter = 0;
	public int actionLockCounter = 0;
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision  = false;
	int framesPerSprite = 0;
	int spriteFrameCounter = 0;
	public int type;// 0 = player, 1 = monster
	

	public int maxLife;
	public int life;
	
	/**
	 * constructor for Entity
	 * @param gp
	 */
	public Entity(GamePanel gp)
	{
		this.gp = gp;
	}
	/**
	 * need setAction method in superClass
	 */
	public void setAction() {}
	/**
	 * this is the default update method if the
	 *  subclass doens't require something special
	 * @param framesPerSprite
	 * @param totalFrames
	 */
	public void update(int framesPerSprite, int totalFrames) 
	{
		setAction();
		
		collisionOn = false;
		
		gp.cChecker.checkTile(this);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		
		if(this.type == 2 && contactPlayer == true)
		{
			if(gp.player.invincible == false)
			{
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
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
				for (int i = 0; i < totalFrames; i++) {
				    // Display current sprite frame

				    spriteCounter++;
				    spriteFrameCounter++;
				    if (spriteFrameCounter > framesPerSprite) {
				        spriteNum = (spriteNum % totalFrames) + 1; // cycle through frames
				        spriteFrameCounter = 0;
				    }
				    if (spriteCounter > totalFrames) 
				    {
				        spriteCounter = 0;
				    }
				}
				if(invincible == true)
				{
					invincibleCounter++;
					if(invincibleCounter > 40)
					{
						invincible = false;
						invincibleCounter = 0;
					}
				}
				
				
	}
	/**default draw method for 
	 * all subclasses of Entity
	 * @param g2
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
	
	/**
	 * this helps to make it simpler to 
	 * add resources in subclasses of Entity
	 * @param imagePath
	 * @return
	 */
	public BufferedImage setup(String imagePath, int width, int height)
	{
		BufferedImage image = null;
		UtilityTool uTool = new UtilityTool();
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return image;
	}
	
	

}
