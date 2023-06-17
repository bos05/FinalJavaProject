package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Basic_Axe extends Entity{

	public OBJ_Basic_Axe(GamePanel gp) {
		super(gp);
		type = type_melee_weapon;
		name = "Basic Axe";
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue= 2;
		attackArea.width = 30;
		attackArea.height = 30;
		itemDescription = "[" + name +"]\n" + "Must have been droped \nby a lumberjack";
	}
	
	
}
