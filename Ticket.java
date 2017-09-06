public class Ticket implements Comparable<Ticket>{
	private float price;

	public Ticket(float price){
		this.price = price;
	}

	//Returns the price of a ticket
	public float getPrice(){
		return this.price;
	}

	/*
	Overrides comparison method in order to sort Tickets by price.
	*/
	@Override
    public int compareTo(Ticket compareTick) {
        int comparePrice=(int)(compareTick).getPrice()*100;
        return (int)this.getPrice()*100-comparePrice;
    }
}