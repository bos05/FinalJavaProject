package game.model.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_test extends SuperObject
{
	public Object_test()
	{
		name = "Test";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/body_water_1.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
