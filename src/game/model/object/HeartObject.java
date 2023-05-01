package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class HeartObject extends Entity
{
	GamePanel gp;
	
	public HeartObject (GamePanel gp)
	{
		super(gp);
		
		name = "Heart";
		image = setup("/objects/heart_full");
		image2 = setup("/objects/heart_falf");
		image3 = setup("/objects/heard_blank");

	}
	

}
