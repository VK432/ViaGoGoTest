import java.util.ArrayList;
import java.util.Collections;

public class Event{
	private int id;
	private int numTickets;
	private ArrayList<Ticket> tickets = new ArrayList<>();


	public Event(int id){
		this.id = id;
		this.numTickets = 0;
	}

	// Adds a Ticket to the list of tickets for this event.
	public void addTicket(Ticket ticket){
		this.tickets.add(ticket);

	}

	// Retruns the id of the event.
	public int getId(){
		return this.id;

	}

	// Sorts the Ticket list by price then returns the first item, i.e. the cheapest
	public Ticket getCheapestTicket(){
		Collections.sort(this.tickets);
		return this.tickets.get(0);
	}

	// Returns the Ticket list
	public ArrayList<Ticket> getTickets(){
		return this.tickets;
	}

}