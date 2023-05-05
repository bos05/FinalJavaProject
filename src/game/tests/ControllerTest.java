package game.tests;

/**
 * Project imports
 */
import game.controller.Controller;
import game.view.GameFrame;

/**
 * Reflection imports
 */
import java.lang.reflect.*;
import java.util.ArrayList;

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
		this.testedController = new Controller();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController = null;
	}

	@Test
	void testMethods()
	{
		Method [] methods = testedController.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 7, "You need at least seven methods in the controller");
		
		boolean hasCreate = false;
		boolean hasSave = false;
		boolean hasGetData = false;
		boolean hasBuild = false;
		boolean hasValidate = false;
		boolean hasUpdate = false;
		
		for (Method method : methods)
		{
			if (method.getName().equals("buildPokedexText"))
			{
				hasBuild = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The buildPokedexText method method must be public");
				Class <?> returnType = method.getReturnType();
				assertTrue(returnType.isArray(), "This method needs to return an array");
				assertTrue(returnType.getComponentType().equals(String.class), "The array needs to be of Strings" );
			}
			else if (method.getName().equals("getPokemonData"))
			{
				hasGetData = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The getPokemonData method method must be public");
				Class <?> returnType = method.getReturnType();
				assertTrue(returnType.isArray(), "This method needs to return an array");
				assertTrue(returnType.getComponentType().equals(String.class), "The array needs to be of Strings" );
			}
			else if (method.getName().equals("save"))
			{
				hasSave = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The save method method must be public");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The save method needs to be a void method!");
			}
			else if (method.getName().equals("createPokedex"))
			{
				hasCreate = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The createPokedex method method must be private");
			}
			else if (method.getName().equals("updateCurrentPokemon"))
			{
				hasUpdate = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The updateCurrentPokemon method method must be public");
			}
			else if (method.getName().equals("validateNumber"))
			{
				hasValidate = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The validateNumber method method must be public");
			}
			
			
		}
		
		assertTrue(hasCreate, "You need a method named createPokedex");
		assertTrue(hasGetData, "You need a method named getPokemonData");
		assertTrue(hasSave, "You need a method named save");
		assertTrue(hasBuild, "You need a method named buildPokedexText");
		assertTrue(hasUpdate, "You need a method named updateCurrentPokemon");
		assertTrue(hasValidate, "You need a method named validateNumber");
	}
	
	@Test
	void testStructure()
	{
		Field [] dataMembers = testedController.getClass().getDeclaredFields();
		assertTrue(dataMembers.length >= 3, "The controller needs at least three data members");
		
		boolean hasSave = false;
		boolean hasFrame = false;
		boolean hasList = false;
		
		for (Field field : dataMembers)
		{
			assertTrue(Modifier.isPrivate(field.getModifiers()), "The data member must be private");
			if (field.getType().equals(ArrayList.class))
			{
				hasList = true;
			}
			else if (field.getType().equals(String.class))
			{
				hasSave = true;
			}
			else if (field.getType().equals(GameFrame.class))
			{
				hasFrame = true;
			}
		}
		
		assertTrue(hasFrame, "You need a PokeFrame!");
		assertTrue(hasList, "You need a pokedex ArrayList!");
		assertTrue(hasSave, "You need a save file text!");
	}

}
