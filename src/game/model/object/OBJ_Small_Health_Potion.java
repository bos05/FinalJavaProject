package game.model.object;

import game.model.entity.Entity;
import game.view.GamePanel;

public class OBJ_Small_Health_Potion extends Entity {
	
	GamePanel gp;
	int value = 5;
	
	public OBJ_Small_Health_Potion(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = consumable;
		name = "Small Health Potion";
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		itemDescription ="[" + name + "]\n" + "Heals " + value + " heal points";
	}
	public void use(Entity entity) {
		entity.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
	}
}
