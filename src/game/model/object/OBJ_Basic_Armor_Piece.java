package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Basic_Armor_Piece extends Entity
{
	public OBJ_Basic_Armor_Piece(GamePanel gp)
	{
		super(gp);
		
		name = "Wooden Shield";
		down1 = setup("/objects/basic_armor_piece", gp.tileSize - 10, gp.tileSize - 10);
		defenseValue = 1;
	}
}
