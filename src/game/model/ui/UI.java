package game.model.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import game.view.GamePanel;
/**
 * In charge of on-screen text and User-Interface
 * @author blin2710
 *
 */
public class UI 
{
	GamePanel gp;
	Font arial40;
	
	/**
	 * constructor for UI class
	 * @param gp
	 */
	public UI(GamePanel gp)
	{
		this.gp = gp;
		arial40 = new Font("Arial", Font.PLAIN, 40);
	}
	/**
	 * draws the UI elements
	 * @param g2
	 */
	public void draw(Graphics2D g2)
	{
		g2.setFont(arial40);
		g2.setColor(Color.WHITE);
		g2.drawString("UI test",50, 50);
	}
}
