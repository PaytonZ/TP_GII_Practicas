package tp.pr4.items;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;

public class ItemGeneric extends Item {

	public ItemGeneric(String i ) {
		super(i, "");

	}

	@Override
	public boolean canBeUsed() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unUse(RobotEngine r, NavigationModule nav) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		throw new UnsupportedOperationException();
	}

}
