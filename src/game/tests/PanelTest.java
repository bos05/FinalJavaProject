package game.tests;

/**
 * Project imports
 */
import poke.view.PokePanel;
import poke.controller.Controller;
/**
 * Reflection imports
 */
import java.lang.reflect.*;
import java.awt.*;
import javax.swing.*;
/**
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PanelTest
{
	private PokePanel testedPanel;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedPanel = new PokePanel(new Controller());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedPanel = null;
	}

	@Test
	void testStructure()
	{
		Field [] dataMembers = testedPanel.getClass().getDeclaredFields();
		
		for (Field field : dataMembers)
		{
			int status = field.getModifiers();
			assertTrue(Modifier.isPrivate(status), "All data members need to be private!");
		}
		
		Component [] contents = testedPanel.getComponents();
		int fieldCount = 0;
		int panelCount = 0;
		int paneCount = 0;
		int buttonCount = 0;
		boolean comboBoxExists = false;
		boolean labelExists = false;
		
		for (Component current : contents)
		{
			
			if (current instanceof JPanel)
			{
				JPanel subPanel = (JPanel) current;
				panelCount++;
				for (Component panelItem : subPanel.getComponents())
				{
					if (panelItem instanceof JTextField)
					{
						fieldCount++;
					}
					else if (panelItem instanceof JButton)
					{
						buttonCount++;
					}
					else if (panelItem instanceof JScrollPane)
					{
						paneCount++;
						JScrollPane tested = (JScrollPane) panelItem;
						assertTrue(tested.getViewport().getView() instanceof JTextArea, "Your Scrollpane needs the JTextArea as a view");
						assertTrue(tested.getVerticalScrollBarPolicy() == JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, "The vertical Scroll should be as needed");
						assertTrue(tested.getHorizontalScrollBarPolicy() == JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, "The horizontal Scroll should be never");
					}	
				}
			}
			else if (current instanceof JComboBox)
			{
				comboBoxExists = true;
			}
			else if (current instanceof JLabel)
			{
				labelExists = true;
			}
		}
		
		assertTrue(paneCount >= 1, "You need a JScrollPane");
		assertTrue(buttonCount >= 1, "You need at least one button");
		assertTrue(fieldCount >= 3, "You need at least three jtextfields");
		assertTrue(panelCount == 1, "You need a JPanel with a grid layout");
		assertTrue(labelExists, "You need a JLabel" );
		assertTrue(comboBoxExists, "You need a JComboBox" );
		
	}
	
	
	@Test
	void testRequiredMethods()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 4, "You need at least 4 methods in the panel");
		boolean hasUpdateDisplay = false;
		boolean hasSetupPanel = false;
		boolean hasSetupListeners = false;
		boolean hasSetupLayout = false;
		
		for (Method method : methods)
		{
			if (method.getName().equals("updateDisplay"))
			{
				hasUpdateDisplay = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The updateDisplay method must be private");
				assertTrue(method.getParameterCount() == 1, "The updateDisplay method needs a single parameter!");
				assertTrue(method.getGenericParameterTypes()[0].getTypeName().equals("java.lang.String"), "The only parameter should be a String");
			}
			else if (method.getName().equals("setupPanel"))
			{
				hasSetupPanel = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The setupPanel method must be private");
			}
			else if (method.getName().equals("setupListeners"))
			{
				hasSetupListeners = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The setupListeners method must be private");
			}
			else if (method.getName().equals("setupLayout"))
			{
				hasSetupLayout = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The setupLayout method must be private");
			}
		}
		assertTrue(hasUpdateDisplay, "You need a method named updateDisplay");
		assertTrue(hasSetupPanel, "You need a method named setupPanel");
		assertTrue(hasSetupListeners, "You need a method named setupListeners");
		assertTrue(hasSetupLayout, "You need a method named setupLayout");
	}

}
