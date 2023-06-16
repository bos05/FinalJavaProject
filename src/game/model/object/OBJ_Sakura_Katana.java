package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Sakura_Katana extends Entity
{

	public OBJ_Sakura_Katana(GamePanel gp) 
	{
		super(gp);
		
		name = "Sakura Katana";
		down1 = setup("/objects/sakura_katana", gp.tileSize - 6, gp.tileSize - 6);
		attackValue = 1;
		itemDescription = "[" + name + "]" + "\nSword with pink sakura \nblade";
	}

}
