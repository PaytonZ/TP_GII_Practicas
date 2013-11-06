package tp.pr2.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr2.Item;
import tp.pr2.ItemContainer;

public class ItemContainerTest {

	private ItemContainer testContainer;
	private Item testItem;
	private String testName;
	private String testDesc;

	@Before
	public void setUp() {
		testContainer = new ItemContainer();
		testName = MockItem.DEF_NAME;
		testDesc = MockItem.DEF_DESC;
		testItem = new MockItem();
	}


	@Test
	public void testNumberOfItemsEmptyContainer() {
		assertEquals("ERROR: An empty item container does not return 0 items", 0, testContainer.numberOfItems());
	}

	@Test
	public void testAddItem() {		
		int containersize = testContainer.numberOfItems();
		assertTrue("ERROR: An item can be added to an  empty container but add method returns false",testContainer.addItem(testItem));
		assertEquals("ERROR: A container should increment the number of elements after adding a new item", containersize+1, testContainer.numberOfItems());

		// Creates a new item with the same itemname and try to add it
		testItem = new MockItem();
		containersize = testContainer.numberOfItems();
		assertFalse("ERROR: An item with the same name of another item in the container can not be added but add method returns true",testContainer.addItem(testItem));
		assertEquals("ERROR: The item has not been added but the container size has been incremented", containersize, testContainer.numberOfItems());

		// Creates a new item with different name to the previous one and try to add it
		testItem = new MockItem(MockItem.WRONG_NAME);
		containersize = testContainer.numberOfItems();
		assertTrue("ERROR: An item with unique name in the container can be added but add method returns false",testContainer.addItem(testItem));
		assertEquals("ERROR: A container should increment the number of elements after adding a new item", containersize+1, testContainer.numberOfItems());
	}

	@Test
	public void testGetItem() {
		assertNull("ERROR: An item is not stored in an empty container but getItem method returns an object",testContainer.getItem(testName));
		
		// Store an item
		if (testContainer.addItem(testItem)){
			assertNull("ERROR: Container does not contain an item with name \""+MockItem.WRONG_NAME+"\" but getItem method returns an object",testContainer.getItem(MockItem.WRONG_NAME));
			assertNotNull("ERROR: Container stores the item but getItem method returns null",testContainer.getItem(testName));
			assertEquals("ERROR: Container stores the item but getItem method returns an object that is different to the one previously stored",testItem, testContainer.getItem(testName));
			
		}
		else fail("ERROR: Add method is not correct. Try first to pass the testAddItem test");
	}

	@Test
	public void testPickItem() {
		int containersize = testContainer.numberOfItems();
		assertNull("ERROR: An item can not be picked from an empty container but pick method returns an object",testContainer.pickItem(testName));
		assertEquals("ERROR: An item can not be picked from an empty container but pick method modifies the container size", containersize, testContainer.numberOfItems());
		
		// Store an item and try to pick it
		if (testContainer.addItem(testItem)){
			containersize = testContainer.numberOfItems();
			assertNull("ERROR: Container does not contain an item with name \""+MockItem.WRONG_NAME+"\" but pick method returns an object",testContainer.pickItem(MockItem.WRONG_NAME));
			assertEquals("ERROR: Container does not contain an item with name \""+MockItem.WRONG_NAME+"\" but pick method modifies the container size", containersize, testContainer.numberOfItems());
			
			containersize = testContainer.numberOfItems();
			Item picked = testContainer.pickItem(testName);
			assertNotNull("ERROR: Container stores the item but pick method returns null", picked);
			assertEquals("ERROR: Container stores the item but pick method returns an object that is different to the one previously stored",testItem, picked);
			assertEquals("ERROR: Container stores the item but pick method does not modify the container size", containersize-1, testContainer.numberOfItems());
			
		}
		else fail("ERROR: Add method is not correct. Try first to pass the testAddItem test");
	}
	
	@Test
	public void testToString() {
		// Store an item
		if (testContainer.addItem(testItem)){
			String containerDesc = testContainer.toString();
			assertTrue("ERROR: the container description does not contain the item name", containerDesc.contains(testName));
		}
		else fail("ERROR: Add method is not correct. Try first to pass the testAddItem test");
		
	}

}
