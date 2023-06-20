package game.model.object;

import game.model.entity.Projectile;
import game.view.GamePanel;

public class OBJ_Basic_Arrow extends Projectile{

	GamePanel gp;
	
	public OBJ_Basic_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Basic Arow";
		speed = 10;
		maxLife = 80;
		life  = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;
		getImage();
	}
	public void getImage() {
		up1 = setup("/projectiles/arrow_up", gp.tileSize, gp.tileSize);
		up2 = setup("/projectiles/arrow_up", gp.tileSize, gp.tileSize);
		down1 = setup("/projectiles/arrow_down", gp.tileSize, gp.tileSize);
		down2 = setup("/projectiles/arrow_down", gp.tileSize, gp.tileSize);
		left1 = setup("/projectiles/arrow_left", gp.tileSize, gp.tileSize);
		left2 = setup("/projectiles/arrow_left", gp.tileSize, gp.tileSize);
		right1 = setup("/projectiles/arrow_right", gp.tileSize, gp.tileSize);
		right2 = setup("/projectiles/arrow_right", gp.tileSize, gp.tileSize);

	}

}
