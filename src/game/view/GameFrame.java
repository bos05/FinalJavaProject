package game.view;

import javax.swing.JFrame;

import game.controller.Controller;

public class GameFrame extends JFrame
{
	private Controller app;
	private GamePanel panel;
	
	public GameFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new GamePanel(this.app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(panel);
		this.setSize(100, 100);
		this.setResizable(false);
		this.setTitle("2D game Adventure :)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
