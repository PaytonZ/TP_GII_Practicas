package tp.pr2;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Street[] mapa = crearMapa();
		City city= new City(mapa);
		RobotEngine robot = new RobotEngine( city, mapa[0].getPlace1(), Direction.NORTH);
		robot.startEngine();
	}
	
	private static Street[] crearMapa() 
	{
		Place p1 = new Place ("PLAZA DE ESPAÑA", false,  "What a big square! You have a big park where you can sleep under a tree." + Interpreter.LINE_SEPARATOR +
	            "But you have a problem, you have to come back to PLAZA MAYOR. " + Interpreter.LINE_SEPARATOR +
	            "There is no other exit");
		Place p2 = new Place ("CALLAO", false, "In this small square you can find some some fuel. " + Interpreter.LINE_SEPARATOR +
	            "Go to fnac and take the fuel for the heating");
		Place p3 = new Place ("MAYOR", false,"Mmmh... it smells squid fried in butter. You should try to eat something");
		Place p4 = new Place ("PUERTA DEL SOL", false, "You are at the PUERTA DEL SOL, the center of Madrid. " +  Interpreter.LINE_SEPARATOR +
                "Ufff, there are a lot of streets, but all of them are full of garbage." + Interpreter.LINE_SEPARATOR +
                "You should walk around this place to look for some interesting" + Interpreter.LINE_SEPARATOR +
                "objects to pick up." + Interpreter.LINE_SEPARATOR +
                "Note that there is a big clock. Remember, leave the square before " + Interpreter.LINE_SEPARATOR +
                "night. It can be dangerous");
		Place p5 = new Place ("JACINTO BENABENTE", false,  "If you are cold you can start a fire with the wheels of those old buses");
		Place p6 = new Place ("NEPTUNO", false, "Watch Wall-e! Another fountain! Perhaps this one has water for drinking" + Interpreter.LINE_SEPARATOR +
	            "If you are cold, use that red and white scarf");
		Place p7 =new Place ("ATOCHA", false, "You have a lot of old trains here, but they do not work" +  Interpreter.LINE_SEPARATOR +
	            "All trains were destroyed during the crisis of 2013" + Interpreter.LINE_SEPARATOR +
	            "Take all the iron you find");
		Place p8 = new Place ("CIBELES", false, "Arggg, the fountain is ugly! The water is very dirty. " + Interpreter.LINE_SEPARATOR +
	            "You should leave before the lions attack you");
		Place p9 = new Place ("COLON", true, "Sometime ago, Spanish people concentrates here to watch football " + Interpreter.LINE_SEPARATOR +
	            "in a big screen." +  Interpreter.LINE_SEPARATOR +
	            "Wall-e, did you know that in Spain there were very good football teams?." +  Interpreter.LINE_SEPARATOR +
	            "Look for cans! People throw cans after watching football!");
		Place p10= new Place ("PUERTA DE ALCALA", true, "Ok, finally you have found your spaceship...." + Interpreter.LINE_SEPARATOR + "The place is empty. There are no objects to pick");
		Street s[]=new Street[11];
		s[0]= new Street(p4,Direction.NORTH,p2,false, "red_pill");
        s[1]= new Street(p4,Direction.SOUTH, p5);
        s[2]= new Street(p4,Direction.EAST, p8,false, "12345");
        s[3]= new Street(p4,Direction.WEST, p3);
        s[4] = new Street(p3,Direction.NORTH, p1);      
        s[5] = new Street(p2,Direction.WEST, p1);      
        s[6] = new Street(p8,Direction.NORTH, p9,false, "Joshua");
        s[7] = new Street(p8,Direction.EAST, p10,false, "eva");
        s[8] = new Street(p8,Direction.SOUTH, p6);      
        s[9] = new Street(p6,Direction.SOUTH, p7);
        s[10] = new Street(p5,Direction.EAST, p6);
        p4.addItem(new Garbage("newspapers","news on sports",5));
        p4.addItem(new Fuel("grapes","celebrations of the new year",1,1));
        p4.addItem(new CodeCard("Spaceballs-card","This is the kind of combination an idiot would have on his luggage","12345"));
        p4.addItem(new Fuel("Coal","Be careful with this fuel because it is extremely contaminant",-80,1));
        
        p3.addItem(new Fuel("gas-oil","from the buses..",5,2));
        p3.addItem(new Garbage("explosive-plastic","too dangerous to be in the street",10));
        p3.addItem(new CodeCard("matrix-card","It shows you how deep the rabbit hole goes","red_pill"));
        
        p2.addItem(new Garbage("cans-of-beer","all of them are empty",2));
        p2.addItem(new Fuel("petrol","from olds heatings",10,3));
        
        p9.addItem(new Fuel("battery","to get cracking",4,1));
        p9.addItem(new Garbage("television","it is broken. Better. Usually programs are really bad",10));
        
        p8.addItem(new Fuel("water","to put out possible fires",11,3));
        p8.addItem(new Garbage("white-scarf","good for winter",2));
        p8.addItem(new CodeCard("walle-card","I lost it when I was looking for garbage","eva"));
        p8.addItem(new CodeCard("Spaceballs-card","This is the kind of combination an idiot would have on his luggage","12345"));
        
        p6.addItem(new Garbage("tinned-food","it seems spanish people were hungry",11));
        p6.addItem(new Garbage("yellow-press","what stupid things they write",1));
        
        p7.addItem(new Fuel("red-bull","good for flying",5,1));
        p7.addItem(new CodeCard("falken-card","Shall we play a game?","Joshua"));
		return s;
		
	}

}
