package game.model.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import game.view.GamePanel;

public class UI 
{
	GamePanel gp;
	Font arial40;
	
	
	public UI(GamePanel gp)
	{
		this.gp = gp;
		arial40 = new Font("Arial", Font.PLAIN, 40);
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setFont(arial40);
		g2.setColor(Color.WHITE);
		g2.drawString("Sugma",50, 50);
	}
}
