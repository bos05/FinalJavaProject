package game.model.object;

import javax.imageio.ImageIO;

public class Object_test extends SuperObject
{
	public Object_test()
	{
		name = "Test";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/test.png"));
		}
	}
}
