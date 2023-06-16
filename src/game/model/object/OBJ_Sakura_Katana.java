package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Sakura_Katana extends Entity
{

	public OBJ_Sakura_Katana(GamePanel gp) 
	{
		super(gp);
		
		name = "Base Katana";
		down1 = setup("/objects/sakura_katana", gp.tileSize - 16, gp.tileSize - 16);
		attackValue = 1;
	}

}
