package game.model.entity;

import java.awt.image.BufferedImage;

public class Entity
{
	public int x, y, speed;
	//BufferedImage describes an image with an accessible buffer of image data
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
}
