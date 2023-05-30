package game.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.view.GamePanel;
/**
 * The KeyHandler class is in charge of handling inputs
 * @author blin2710
 *
 */
public class KeyHandler implements KeyListener
{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	
	
	public KeyHandler(GamePanel gp)
	{
		this.gp = gp;
	}
	/**
	 * KeyTyped constructor
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		//don't use 
	}
	/**
	 * this detects keys pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();//returns integer keyCode associated with the key pressed
		
		if(gp.gameState == gp.titleState)
		{
			if(code == KeyEvent.VK_W)
			{
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0)
				{
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S)
			{
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2)
				{
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.commandNum == 0)
				{
					gp.gameState = gp.playState;
				}
				if(gp.ui.commandNum == 1)
				{
					//later
				}
				if(gp.ui.commandNum == 2)
				{
					System.exit(0);
				}
			}
		}
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = true;
		}
		if(code == KeyEvent.VK_S)
		{
			downPressed = true;
		}
		if(code == KeyEvent.VK_A)
		{
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D)
		{
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P)
		{
			if(gp.gameState == gp.playState) {gp.gameState = gp.pauseState;}
			else if(gp.gameState == gp.pauseState) {gp.gameState = gp.playState;}
			System.out.println("TAB");
				
		}
		if(code == KeyEvent.VK_ENTER)
		{
			enterPressed = true;
		}
	}
	/**
	 * this detects keys released
	 */
	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();//returns integer keyCode associated with the key pressed
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		if(code == KeyEvent.VK_S)
		{
			downPressed = false;
		}
		if(code == KeyEvent.VK_A)
		{
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D)
		{
			rightPressed = false;
		}
		
		
	}
	
}
