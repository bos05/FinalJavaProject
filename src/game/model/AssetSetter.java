package game.model;

import java.util.Random;

import game.model.monster.Slime;
import game.model.object.Temple_Object;
import game.view.GamePanel;

public class AssetSetter 
{
	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setObject ()
	{
		gp.landmark[0] = new Temple_Object(gp);
		gp.landmark[0].worldX = 5 * gp.tileSize;
		gp.landmark[0].worldY = 5 * gp.tileSize;
	}
	
	public void setMonster ()
	{
		
		gp.monster[0] = new Slime(gp);
		gp.monster[0].worldX = gp.tileSize * 2;
		gp.monster[0].worldY = gp.tileSize * 2 ;
		

	}
}
