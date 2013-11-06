package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

public class ItemGeneric extends Item {

	/**
	 * Default Builder
	 * @param i -The name of the item.
	 */
	public ItemGeneric(String i ) {
		super(i, "");

	}

	/**
	 * Unsupported Operation
	 */
	@Override
	public boolean canBeUsed() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unsupported Operation
	 */
	@Override
	public void unUse(RobotEngine r, NavigationModule nav) {
		throw new UnsupportedOperationException();
		
	}

	/**
	 * Unsupported Operation
	 */
	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		throw new UnsupportedOperationException();
	}

}
