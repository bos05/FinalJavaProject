package game.model;

import java.util.Random;

import game.model.landmark.TempleLandmark;
import game.model.monster.Slime;
import game.view.GamePanel;
/**
 * This fills the arrays with the Entity objects
 * @author blin2710
 *
 */
public class AssetSetter 
{
	GamePanel gp;
	/**
	 * constructor
	 * @param gp
	 */
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	/**
	 * sets new objects and tells their coordinates
	 */
	public void setObject ()
	{
		gp.landmark[0] = new TempleLandmark(gp);
		gp.landmark[0].worldX = 97 * gp.tileSize;
		gp.landmark[0].worldY = 45 * gp.tileSize;
	}
	
	/**
	 * sets new new monsters and tells their coordinates
	 */
	public void setMonster ()
	{
		
		gp.monster[0] = new Slime(gp);
		gp.monster[0].worldX = gp.tileSize * 40;
		gp.monster[0].worldY = gp.tileSize * 20;
		
		gp.monster[1] = new Slime(gp);
		gp.monster[1].worldX = gp.tileSize * 60;
		gp.monster[1].worldY = gp.tileSize * 35;
		
		gp.monster[2] = new Slime(gp);
		gp.monster[2].worldX = gp.tileSize * 63;
		gp.monster[2].worldY = gp.tileSize * 38;
		
		gp.monster[3] = new Slime(gp);
		gp.monster[3].worldX = gp.tileSize * 64;
		gp.monster[3].worldY = gp.tileSize * 43;
		
		gp.monster[4] = new Slime(gp);
		gp.monster[4].worldX = gp.tileSize * 88;
		gp.monster[4].worldY = gp.tileSize * 17;
		
		gp.monster[5] = new Slime(gp);
		gp.monster[5].worldX = gp.tileSize * 85;
		gp.monster[5].worldY = gp.tileSize * 24;
		
		gp.monster[5] = new Slime(gp);
		gp.monster[5].worldX = gp.tileSize * 81;
		gp.monster[5].worldY = gp.tileSize * 29;
		
		gp.monster[6] = new Slime(gp);
		gp.monster[6].worldX = gp.tileSize * 106;
		gp.monster[6].worldY = gp.tileSize * 63;
		

		

	}
}
