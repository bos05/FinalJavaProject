package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Sakura_Katana extends Entity
{

	public OBJ_Sakura_Katana(GamePanel gp) 
	{
		super(gp);
		type = sakura_katana;
		name = "Sakura Katana";
		down1 = setup("/objects/sakura_katana", gp.tileSize - 6, gp.tileSize - 6);
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		itemDescription = "[" + name + "]" + "\nSword with pink sakura \nblade";
	}

}
