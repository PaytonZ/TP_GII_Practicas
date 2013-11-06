package tp.pr5;

/**
 * An enumerated type that represents the compass directions (north, east, south and west) plus a value that represents an unknown direction.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 */

public enum Direction {EAST, NORTH, SOUTH, WEST, UNKNOWN;

/**
 * This method receives a direction and return the opposite direction
 * @param d - Direction received
 * @return -  Return opposite direction
 */

public Direction opposite( Direction d)
{
	switch (d)
	{
	case EAST: d=Direction.WEST; break;
	case WEST: d=Direction.EAST; break;
	case NORTH: d=Direction.SOUTH; break;
	case SOUTH: d=Direction.NORTH; break;
	case UNKNOWN: d=Direction.UNKNOWN; break;
			
	}
	return d;		
}

/**
 * This method turn left the direction received
 * @param d - Direction received
 * @return - Returns the  direction after turning left
 */

public Direction turnLeft( Direction d)
{
	switch (d)
	{
	case EAST: d=Direction.NORTH; break;
	case WEST: d=Direction.SOUTH; break;
	case NORTH: d=Direction.WEST; break;
	case SOUTH: d=Direction.EAST; break;
	case UNKNOWN: d=Direction.UNKNOWN; break;
			
	}
	return d;		
}

/**
 * This method turn right the direction received 
 * @param d - Direction received
 * @return - Returns the  direction after turning right 
 */

public Direction turnRight( Direction d)
{
	switch (d)
	{
	case EAST: d=Direction.SOUTH; break;
	case WEST: d=Direction.NORTH; break;
	case NORTH: d=Direction.EAST; break;
	case SOUTH: d=Direction.WEST; break;
	case UNKNOWN: d=Direction.UNKNOWN; break;
			
	}
	return d;		
}

/**
 * This method converts a string in a direction
 * @param a - String to convert
 * @return  - Returns the direction after converting
 */

 public static Direction stringDirection(String a)
{
	Direction convert;
	switch (a.toUpperCase())
	{
		case "EAST": {convert = Direction.EAST;
					  break;
					 }
		case "NORTH": {convert = Direction.NORTH;
		  			  break;
		 			  }
		case "SOUTH": {convert = Direction.SOUTH;
					   break;
					  }
		case "WEST": {convert = Direction.WEST;
					  break;
					 }
		default:{convert = Direction.UNKNOWN;}	 
	}
	return convert;
}

/**
 * this method shows for the console the direction 
 */

public String toString()
{
	String s;
	if(this==EAST)
	{
		s = "EAST";
	}
	else if (this==NORTH)
	{
		s = "NORTH";
	}
	else if (this==SOUTH)
	{
		s = "SOUTH";
	}
	else if (this==WEST)
		s = "WEST";
	else
		s = "UNKNOWN";
	return s;
}

}
