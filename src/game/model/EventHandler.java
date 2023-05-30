package game.model;

import java.awt.Rectangle;

import game.view.GamePanel;
/**
 * EventHandler creates and looks for 
 * events such as taking damage and healing
 * @author Boss_
 *
 */
public class EventHandler 
{
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	/**
	 * This is the constructor and it also has some things
	 *  like reventRect that will be used throughout the code
	 * @param gp
	 */
	public EventHandler(GamePanel gp)
	{
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
		
	}
	/**
	 * Looks for event then does according thing 
	 */
	public void checkEvent()
	{
//		if(hit(2, 2, "any") == true)
//		{
//			gp.player.life -= 1;
//		}
	}
	/**
	 * checks for interaction of something to see if there is collision/interacts with zone
	 * @param eventCol
	 * @param eventRow
	 * @param reqDirection
	 * @return
	 */
	public boolean hit(int eventCol, int eventRow, String reqDirection)
	{
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol * gp.tileSize + eventRect.x;
		eventRect.y = eventRow * gp.tileSize + eventRect.y;
		
		if (gp.player.solidArea.intersects(eventRect))
		{
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
			{
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	

	
}
