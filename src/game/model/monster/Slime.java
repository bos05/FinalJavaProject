package game.model.monster;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game.model.entity.Entity;
import game.view.GamePanel;

public class Slime extends Entity
{
	public Slime(GamePanel gp)
	{
		super();
		
		speed = 1;
		
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		
		
		getImage();
		
	}
	public void getImage()
	{
		try
		{
			up1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("//monster/greenslime_down_2.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
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
				direction = "left";
			}
			if(i > 50 && i <= 75)
			{
				direction = "right";
			}
			if(i > 75 && i <= 100)
			{
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}
}
