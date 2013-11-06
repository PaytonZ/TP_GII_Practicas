package tp.pr5;

/**
 * An enum class that represents in which direction the robot rotates (left or right) plus a value that represents an unknown direction.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

public enum Rotation {LEFT, RIGHT, UNKNOWN;

/**
 * this method return the opossite direction.
 * @param d the direction
 * @return the opossite direction.
 */
public Rotation oposite(Rotation d) {
	Rotation a;
	if (d==Rotation.LEFT)
	{
		a=Rotation.RIGHT;
	}
	else if (d==Rotation.RIGHT)
	{
		a=Rotation.LEFT;
	}
	else a=Rotation.UNKNOWN;
	return a;
}

}
