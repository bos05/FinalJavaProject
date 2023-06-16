package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Wood_Shield extends Entity
{
	public OBJ_Wood_Shield(GamePanel gp)
	{
		super(gp);
		
		name = "Wooden Shield";
		down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue = 1;
	}
}
