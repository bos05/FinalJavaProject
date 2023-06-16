package game.model.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
	Font maruMonica;
	public int commandNum = 0;
	
	
	BufferedImage heart_full, heart_half, heart_blank;
	
	/**
	 * constructor for UI class
	 * @param gp
	 */
	public UI(GamePanel gp)
	{
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch(FontFormatException e){
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
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
	    g2.setFont(maruMonica);
	    g2.setColor(Color.WHITE);
	    
	    if(gp.gameState == gp.titleState)
	    {
	    	drawTitleScreen();
	    }
	    if(gp.gameState == gp.playState)
	    {
	    	drawPlayerLife();
	    	drawPlayerCoordinates();
	    }
	    if(gp.gameState == gp.pauseState)
	    {
	    	drawPauseScreen();
	    }
	    if(gp.gameState == gp.characterState)
	    {
	    	drawCharacterScreen();
	    }
	    
	}
	
	public void drawTitleScreen()
	{
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96f));
		String text = "TITLE TBD :D";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		//SHADOW
		g2.setColor(new Color(105, 25, 25));
		g2.drawString(text, x+5, y+5);
		//MAIN TEXT
		g2.setColor(Color.white);
		g2.drawString(text,  x,  y);
		
		//PLAYER
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize+2;
		g2.drawImage(gp.player.down1, x, y,  gp.tileSize*2,  gp.tileSize*2, null);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);
		if(commandNum == 0)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		
	}
	
	public void drawPauseScreen()
	{
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
		String text = "PAUSE";
		int x = getXforCenteredText(text);
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x= gp.screenWidth/2 - length/2;
		
		int y = gp.screenHeight/2;
		
		g2.drawString(text,  x,  y);
	}
	
	public void drawCharacterScreen()
	{
		//Create a frame
		final int frameX = gp.tileSize*2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//TEXT
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 40;
		
		//NAMES
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 10;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight;
		g2.drawString("Sheild", textX, textY);
		textY += lineHeight;
		
		//Values
		int tailX = (frameX + frameWidth) - 30;
		//Reset TextY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 23, null);
		textY += lineHeight;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 23, null);
		

		
	}
	public int getXforCenteredText(String text)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x= gp.screenWidth/2 - length/2;
		
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();
		int x = tailX - length;
		return x;
		
	}
	
	public void drawPlayerLife()
	{
		int x = gp.tileSize/2;
		int y = gp.tileSize/3;
		int i = 0;
		
		//DRAW BLANK VALUES
		while(i < gp.player.maxLife/2)
		{
			g2.drawImage(heart_blank, x, y, gp.tileSize - 8, gp.tileSize - 8, null);
			i++;
			x += gp.tileSize;
		}
		
		//VARIABLE RESET
		x = gp.tileSize/2;
		y = gp.tileSize/3;
		i = 0;
		
		while(i < gp.player.life)
		{
			g2.drawImage(heart_half, x, y, gp.tileSize- 6, gp.tileSize - 8, null);
			i++;
			if( i < gp.player.life)
			{
				g2.drawImage(heart_full, x, y, gp.tileSize- 6, gp.tileSize- 8, null);

			}
			i++;
			x += gp.tileSize;
		}
	}
	
	public void drawPlayerCoordinates()
	{
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
		int playerX = (gp.player.worldX) / gp.tileSize;
		int playerY = (gp.player.worldY) / gp.tileSize;
		
		int xX = gp.tileSize * 1;
		int yX = gp.tileSize * 2;
		
		int xY = gp.tileSize * 1;
		int yY = gp.tileSize * 3;
		
		g2.drawString(String.valueOf(playerX), xX, yX);

		g2.drawString(String.valueOf(playerY), xY, yY);

		
	}
	public void drawSubWindow(int x, int y, int width, int height)
	{
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x,  y,  width,  height,  35,  35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,  y+5,  width-10,  height-10,  25,  25);
		
	}
}
