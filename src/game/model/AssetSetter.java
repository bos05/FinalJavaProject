package game.model;

import game.model.object.Object_test;
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
		gp.obj[0] = new Object_test();
		gp.obj[0].worldX = 5 * gp.tileSize;
		gp.obj[0].worldY = 5 * gp.tileSize;
	}
}
