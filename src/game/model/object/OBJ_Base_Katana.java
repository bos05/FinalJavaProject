package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Base_Katana extends Entity
{

	public OBJ_Base_Katana(GamePanel gp) 
	{
		super(gp);
		
		name = "Base Katana";
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 20;
	}

}
