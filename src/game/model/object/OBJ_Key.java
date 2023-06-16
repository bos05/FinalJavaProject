package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Key extends Entity {
	public OBJ_Key(GamePanel gp) 
	{
		super(gp);
		
		name = "Key";
		down1 = setup("/objects/key", gp.tileSize - 6, gp.tileSize - 6);
		attackValue = 1;
		itemDescription = "[" + name + "]" + "\nKey that will inevitabley \nnot be in the game";
	}
}
