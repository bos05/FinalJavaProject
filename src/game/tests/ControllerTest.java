package game.tests;

/**
 * Project imports
 */

import game.controller.Controller;

/**
 * Reflection imports
 */
import java.lang.reflect.*;

/**
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest
{
	private Controller testedController;
	@BeforeEach
	void setUp() throws Exception
	{
		testedController = new Controller();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		testedController = null;
	}

	@Test
	void testControllerStructure()
	{
		//assertTrue(testedController.getClass().getDeclaredConstructors().length == 1, "You need a zero parameter constructor!");
		Method [] methods = testedController.getClass().getDeclaredMethods();
		//assertTrue(methods.length >= 2, "You need to have at least two methods in your Controller class");
		
		int expectedPublicCount = 1;
		int expectedPrivateCount = 1;
		int totalPublic = 0;
		int totalPrivate = 0;
		String method = "";
		
		for (Method currentMethod : methods)
		{
			if(Modifier.isPrivate(currentMethod.getModifiers()))
			{
				totalPrivate++;
			}
			else if (Modifier.isPublic(currentMethod.getModifiers()))
			{
				totalPublic++;
			}	
		}
		
		assertTrue(totalPublic >= expectedPublicCount, "You need only 1 public method: start");
		assertTrue(totalPrivate >= expectedPrivateCount, "You need 1 or more private methods: interactWithChatbot");
		asserTure(method == "Controller", "looks for controller method");
		
//		Field [] dataMembers = testedController.getClass().getDeclaredFields();
//		assertTrue(dataMembers.length > 1, "You need at least 2 data members in the Controller!");
//		
		//String [] required = {"Chatbot","Popup"};
		//int requiredDataMembers = 0;
		
//		for (Field currentField : dataMembers)
//		{
//			String name = currentField.getType().getSimpleName();
//			if (name.equals(required[0]))
//			{
//				requiredDataMembers += 5;
//			}
//			else if (name.equals(required[1]))
//			{
//				requiredDataMembers += 6;
//			}	
//		}
		//assertTrue(requiredDataMembers == 11, "You need a Chatbot and a Popup data member in the Controller!");
		
	}

	private void asserTure(boolean b, String string)
	{
		// TODO Auto-generated method stub
		
	}

}
