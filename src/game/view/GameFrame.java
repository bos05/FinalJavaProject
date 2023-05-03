package game.view;

import javax.swing.JFrame;

import game.controller.Controller;
/**
 *GameFrame sets up the frame 
 * @author blin2710
 *
 */
public class GameFrame extends JFrame
{
	private Controller app;
	private GamePanel panel;
	/**
	 * the constructor for the GameFrame class
	 * @param app
	 */
	public GameFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new GamePanel(this.app);

		
		setupFrame();
	}
	/**
	 * sets the default attributes for the frame
	 */
	private void setupFrame()
	{
	    this.setContentPane(panel);
	    this.setResizable(false);
	    this.setSize(768, 576);
	    this.setTitle("2D game Adventure :)");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    
		

		panel.setupGame();
	    panel.startGameThread();
	}
}
