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
		this.setResizable(false);
		this.setSize(768, 576);
		this.setTitle("2D game Adventure :)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		panel.startGameThread();
	}
}