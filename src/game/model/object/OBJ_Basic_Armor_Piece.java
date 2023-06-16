package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Basic_Armor_Piece extends Entity
{
	public OBJ_Basic_Armor_Piece(GamePanel gp)
	{
		super(gp);
		
		name = "Base Armor Piece";
		down1 = setup("/objects/basic_armor_piece", gp.tileSize - 6, gp.tileSize - 6);
		defenseValue = 1;
		itemDescription = "[" + name + "]" +"\nBasic Armor piece with \nweak protection";
	}
}
