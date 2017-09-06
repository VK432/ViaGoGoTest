import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class TaskManager{
		/*Set Constants used to seed random start state*/
		private static final int minEvents = 1; 	// Minimum number of events
		private static final int maxEvents = 400;	// Maximum number of events
		private static final int minTicks = 0;		// Minimum number of tickets per event
		private static final int maxTicks = 100;	// Maximum number of tickets per event
		private static final int minPrice = 1;     	// Minimum ticket price - Measured in cents
		private static final int maxPrice = 9999;  	// Maximum ticket price - Measured in cents


	public static void main(String[] args) {
		Grid grid = new Grid(-10,10,-10,10);
		int coordinates[] = takeInput();
	
		ArrayList<Event> events = seedEvents(grid);
		seedTickets(events);
	
		HashMap<Event,Integer> closestEvents =  grid.findClosestEvents(coordinates[0],coordinates[1]);
		printEvents(closestEvents);

	}

	/*
	Takes the user input in the format of "x,y". Parses it and returns the x and y coordinates in an array.
	arrray[0] = x, array[1] = y.
	*/
	private static int[] takeInput(){
		int coordinates[] = new int[2];

		System.out.println("Please Input Coordinates:");

		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();

		String[] coordinatesStr = line.split(",");

		coordinates[0] = Integer.parseInt(coordinatesStr[0]);
		coordinates[1] = Integer.parseInt(coordinatesStr[1]);

		System.out.println("Closest Events to ("+coordinates[0]+","+coordinates[1]+"):");		
		return coordinates;
	}

	/*
	Seeds a random number of events between the minEvents and maxEvents constants set at the top of the page. 
	Also randomly assigns each a event a location within the grid space.
	*/
	private static ArrayList<Event> seedEvents(Grid grid){
		ArrayList<Event> eventList = new ArrayList<>();

		Random rn = new Random();
		int numEvents = rn.nextInt(maxEvents - minEvents + 1) + minEvents;

		for(int i = 1; i <= numEvents; i++){
			int xCord = rn.nextInt(10 - (-10) + 1) -10;
			int yCord = rn.nextInt(10 - (-10) + 1) -10;
			Event e = new Event(i);
			eventList.add(e);
			grid.addEvent(xCord,yCord,e);
		}	
		return eventList;
	}

	/*
	Seeds a random number of tickets between the minTicks and maxTicks, set as global constants at the top.
	Also Seeds a random price for each of those tickets of between $0.01 and $99.99. Both constants are also set at the top, 
	and are measured in cents.
	*/
	private static void seedTickets(ArrayList<Event> events){
		Random rn = new Random();

		for(Event event: events){
			int numTicks = rn.nextInt(maxTicks - minTicks + 1) + minTicks;
			for(int i = 0; i<numTicks; i++){
				float price = ((float)rn.nextInt(maxPrice - minPrice + 1) + minPrice)/100;
				Ticket ticket = new Ticket(price);
				event.addTicket(ticket);
			}
		}

	}


	/*
	Prints event and ticket data in the required format.
	Event (event ID) - (Ticket Price), Distance (Distance from user to event)
	*/
	private static void printEvents(HashMap<Event,Integer> closestEvents){
		closestEvents.forEach((k,v)-> System.out.println("Event "+String.format("%03d", k.getId())+ " - $"+String.format("%05.2f", k.getCheapestTicket().getPrice())+", Distance "+v));
	}
}
