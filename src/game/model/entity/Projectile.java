package game.model.entity;

import game.view.GamePanel;

public class Projectile extends Entity {

	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		
	}
	public void update(int haveTo, int haveThese) {
		System.out.println("test1");
		if (user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				System.out.println("test fr hit");
				gp.player.damageMonster(monsterIndex, attack);
				alive = false;
			}
		}
		if (user != gp.player) {
			
		}
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		
		life --;
		if(life <= 0)
		{
			alive = false;
		}
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2){
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

}

