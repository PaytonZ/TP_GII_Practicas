package tp.pr2.testprofesor;

import static org.junit.Assert.*;

import org.junit.Test;

import tp.pr2.Action;
import tp.pr2.Instruction;
import tp.pr2.Rotation;


public class InstructionTest {

	@Test
	public void testIsValid() {
		Instruction c = new Instruction();
		assertFalse("ERROR: An instruction created with default constructor is not a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.TURN);
		assertFalse("ERROR: A TURN instruction without rotation is not a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.MOVE);
		assertTrue("ERROR: A instruction created with a action (MOVE) is a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.HELP);
		assertTrue("ERROR: A instruction created with a action (QUIT) is a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.QUIT);
		assertTrue("ERROR: A instruction created with a action (QUIT) is a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.PICK);
		assertFalse("ERROR: A instruction created with a action (PICK) without an itemName is not a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.OPERATE);
		assertFalse("ERROR: A instruction created with a action (OPERATE) without an itemName is not a valid instruction",
				c.isValid());
		c = new Instruction(Action.SCAN);
		assertTrue("ERROR: A instruction created with a action (SCAN) is a valid instruction without an itemname",
				c.isValid());
	}

	@Test
	public void testGetAction() {
		Instruction c = new Instruction(Action.MOVE);
		assertEquals("ERROR: A instruction created with the action MOVE does not return the correct action (MOVE)",
				Action.MOVE, c.getAction());
	}

	@Test
	public void testGetDirection(){
		Instruction c = new Instruction(Action.TURN, Rotation.LEFT);
		assertEquals("ERROR: A instruction configured with a direction should return the correct direction when executing getDirection method",Rotation.LEFT, c.getRotation());
	}
	
	@Test
	public void testGetId(){
		String itemName =MockItem.DEF_NAME;
		Instruction c = new Instruction(Action.PICK, itemName);
		assertEquals("ERROR: A instruction configured with a direction should return the correct direction when executing getDirection method",itemName, c.getId());
	}

}
