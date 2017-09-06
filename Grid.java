import java.util.*;


public class Grid {
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private int rangeX;
	private int rangeY;

	private Event[][] events;

	public Grid(int minX, int maxX, int minY, int maxY){
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.rangeX = Math.abs(maxX - minX)+1;
		this.rangeY = Math.abs(maxY - minY)+1;

		events = new Event[rangeX][rangeY];
	}

	/*
	Checks that the X coordinate and Y coordinate for the event are within the grid.
	Throws an excpetion if either coordinate is not.
	Also checks that there is not already an event at this coordinate.
	Returns true if succesfully added, returns false if another event is already at that location.
	*/
	public boolean addEvent(int xCord, int yCord, Event event){
		if(this.minX <= xCord && xCord <= this.maxX){
			if(this.minY <= yCord && yCord <= this.maxY){
				if(this.events[xCord - minX][yCord - minY] == null){
					this.events[xCord - minX][yCord - minY] = event;
					return true;
				}
				else return false;
			}
			else{
				throw new IndexOutOfBoundsException("Y Coordinate for event is outside of the grid space.");
			}
		}
		else{
			throw new IndexOutOfBoundsException("X Coordinate for event is outside of the grid space.");
		}
	}

	/*
	Cycles through entire grid and makes a HashMap of Events to their distances.
	Excludes events which do not have tickets.
	This Map is then sorted by distances, and the top 5 are selected. (sortByValue())
	And the final output is printed.
	*/
	public HashMap<Event,Integer> findClosestEvents(int xCord, int yCord){
		HashMap<Event,Integer> allEvents = new HashMap<>();
		for(int i = 0; i<rangeX; i++){
			for (int j = 0; j<rangeY; j++){
				if(events[i][j] != null && !events[i][j].getTickets().isEmpty()){
					int distance = calcDistance(xCord,yCord,i+minX,j+minY);
					allEvents.put(events[i][j],distance);
				}				
			}
		}
		
		return sortByValue(allEvents);
	}


	/*
	Calculates the manhattan distance between coordinates (aX,aY) and (bX,bY).
	*/
	public int calcDistance(int aX, int aY, int bX, int bY){
		return Math.abs(aX - bX) + Math.abs(aY - bY);
	}

	/*
	Sorts the HashMap by value (distance).
	First converts the map into a list of Maps
	Then orders the List by values in the Map
	Then puts the newly ordered key value pairs back into a Map
	Only Adds the 5 closest entries.
	*/
	private static HashMap<Event, Integer> sortByValue(HashMap<Event, Integer> unsorted) {

	    List<Map.Entry<Event, Integer>> list =
	            new LinkedList<Map.Entry<Event, Integer>>(unsorted.entrySet());

	    Collections.sort(list, new Comparator<Map.Entry<Event, Integer>>() {
	        public int compare(Map.Entry<Event, Integer> a,
	                           Map.Entry<Event, Integer> b) {
	            return (a.getValue()).compareTo(b.getValue());
	        }
	    });

	    HashMap<Event, Integer> sortedMap = new LinkedHashMap<Event, Integer>();
	    int counter = 0;
	    for (Map.Entry<Event, Integer> entry : list) {
	    	if(counter < 5) counter ++;
	    	else break;
	        sortedMap.put(entry.getKey(), entry.getValue());
	    }

	    return sortedMap;
	}


}


