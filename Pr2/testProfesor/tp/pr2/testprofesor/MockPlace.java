package tp.pr2.testprofesor;

import tp.pr2.Place;

public class MockPlace extends Place {
	public static final String DEF_NAME = "MockPlaceName";
	public static final String DEF_DESC = "MockPlace Description"; 

	public MockPlace(String name, boolean isSpaceShip, String description) {
		super(name, isSpaceShip, description);
	}

	public MockPlace() {
		this(MockPlace.DEF_NAME, false, MockPlace.DEF_DESC);
	}

}
