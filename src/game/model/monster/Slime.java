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
		super(gp);
		
		speed = 1;
		
		
		//solidArea.x = 3;
		//solidArea.y = 18;
		//solidArea.width = 42;
		//solidArea.height = 30;
		
		
		getImage();
		
	}
	public void getImage()
	{
		
		up1 = setup("/monster/greenslime_down_1");
		up2 = setup("/monster/greenslime_down_2");
		down1 = setup("/monster/greenslime_down_1");
		down2 = setup("/monster/greenslime_down_2");
		left1 = setup("/monster/greenslime_down_1");
		left2 = setup("/monster/greenslime_down_2");
		right1 = setup("/monster/greenslime_down_1");
		right2 = setup("/monster/greenslime_down_2");
		
		
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
