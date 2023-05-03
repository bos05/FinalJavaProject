package game.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * The KeyHandler class is in charge of handling inputs
 * @author blin2710
 *
 */
public class KeyHandler implements KeyListener
{
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
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
