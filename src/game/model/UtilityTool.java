package game.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * this class is to hold methods that will be used to help in various ways
 * @author Boss_
 *
 */
public class UtilityTool 
{
	/**
	 * scales images
	 * @param original
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage scaleImage(BufferedImage original, int width, int height)
	{
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
}
