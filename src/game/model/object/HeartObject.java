package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;
/**
 * this method isn't complete but it will display 
 * hearts that correlate to the players heath
 * It also is a subclass of Entity
 * @author blin2710
 *
 */
public class HeartObject extends Entity
{
	GamePanel gp;
	
	public HeartObject (GamePanel gp)
	{
		super(gp);
		type = 3;
		
		name = "Heart";
		image = setup("/objects/heart_full", gp.tileSize - 8, gp.tileSize - 12);
		image2 = setup("/objects/heart_half", gp.tileSize - 8, gp.tileSize - 12);
		image3 = setup("/objects/heart_blank", gp.tileSize - 8, gp.tileSize - 12);
		
		
	}
	

}
