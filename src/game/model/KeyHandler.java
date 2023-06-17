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
	//public boolean upRightPressed, upLeftPressed, downLeftPressed, downRightPressed;
	
	
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
		
		if (gp.gameState == gp.titleState)
		{
			titleState(code);
		}
		else if(gp.gameState == gp.playState)
		{
			playState(code);
		}
		else if (gp.gameState == gp.pauseState)
		{
			pauseState(code);
		}
		else if(gp.gameState == gp.characterState)
		{
			characterState(code);
		}
				
	}
	public void titleState(int code)
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
	public void playState(int code)
	{
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
			gp.gameState = gp.pauseState;
				
		}
		if(code == KeyEvent.VK_ENTER)
		{
			enterPressed = true;
		}
		if(code == KeyEvent.VK_C)
		{
			gp.gameState = gp.characterState;
			
		}

	}
	public void pauseState(int code)
	{
		if(code == KeyEvent.VK_P)
		{
			gp.gameState = gp.characterState;
		}

				
		
	}
	public void characterState(int code)
	{
		if(code == KeyEvent.VK_C)
		{
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W){
			if(gp.ui.slotRow!= 0){
				gp.ui.slotRow--;
			}
		}
		if(code == KeyEvent.VK_A){
			if(gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
			}
		}
		if(code == KeyEvent.VK_S){
			if(gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
			}		

		}
		if(code == KeyEvent.VK_D){
			if(gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
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
