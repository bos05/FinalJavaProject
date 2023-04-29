package game.model;

import java.util.Random;

import game.model.landmark.TempleLandmark;
import game.model.monster.Slime;
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
		gp.landmark[0] = new TempleLandmark(gp);
		gp.landmark[0].worldX = 59 * gp.tileSize;
		gp.landmark[0].worldY = 47 * gp.tileSize;
	}
	
	public void setMonster ()
	{
		
		gp.monster[0] = new Slime(gp);
		gp.monster[0].worldX = gp.tileSize * 2;
		gp.monster[0].worldY = gp.tileSize * 2 ;
		

	}
}
