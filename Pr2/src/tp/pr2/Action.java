package tp.pr2;

// ESTA ESTA FINIQUITADA.

public enum Action {HELP, MOVE, QUIT, TURN, PICK, OPERATE, SCAN, UNKNOWN;

/**
 * Comparamos que la acción recibida sea igual a la acción 
 * @param c - recibe una accion
 * @return devuelve true si la acciones son iguales y devuelve false en caso contrario. 
 */

	public boolean equals (Action c)
	{
		if (this == c)
		{
			return true;
		}
		else 	return false;
	}
}