package game.model;

import java.util.Random;

import game.model.landmark.TempleLandmark;
import game.model.monster.Slime;
import game.model.object.OBJ_Basic_Axe;
import game.model.object.OBJ_Key;
import game.model.object.OBJ_Small_Health_Potion;
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
		int i = 0;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 10*gp.tileSize;
		gp.obj[i].worldY = 10*gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Basic_Axe(gp);
		gp.obj[i].worldX = 15*gp.tileSize;
		gp.obj[i].worldY = 10*gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Small_Health_Potion(gp);
		gp.obj[i].worldX = 18*gp.tileSize;
		gp.obj[i].worldY = 10*gp.tileSize;
		
		
	}
	public void setLandmark ()
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
		int i = 0;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 40;
		gp.monster[i].worldY = gp.tileSize * 20;
		i++;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 60;
		gp.monster[i].worldY = gp.tileSize * 35;
		i++;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 63;
		gp.monster[i].worldY = gp.tileSize * 38;
		i++;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 64;
		gp.monster[i].worldY = gp.tileSize * 43;
		i++;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 88;
		gp.monster[i].worldY = gp.tileSize * 17;
		i++;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 85;
		gp.monster[i].worldY = gp.tileSize * 24;
		i++;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 81;
		gp.monster[i].worldY = gp.tileSize * 29;
		i++;
		gp.monster[i] = new Slime(gp);
		gp.monster[i].worldX = gp.tileSize * 106;
		gp.monster[i].worldY = gp.tileSize * 63;
		

		

	}
}
