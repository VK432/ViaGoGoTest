# ViaGoGoTest

## Compilation and Running

From within the directory run the following command the compile all the files:

**javac *.java**

You can then run the program by exectuing the folowing command:

**java TaskManager**

## Assumptions
There are multiple assumptions that I have made, which I have listed below.

* If an event has no tickets do not display it in the Top 5 closest events.
* If two events occur in the same location, the one first assigned there is chosen the other is discarded.
* If two or more events are equidistant it does not matter the order in which they are displayed, or if they are the 5th choice, it does not matter which is displayed.
* The user always inputs coordinates in the same format.
* The user is allowed to input coordinates outside the grid area.

### Random Generation Assumptions
* There is between 1-400 events (The maximum possible on the grid).
* There is between 0-100 tickets available for each event (In order to give the random generator a range).
* The price of a ticket is between $0.01 - $99.99 (In order to give the generator seed a range).


#### How might you change your program if you needed to support multiple events at the same location?

Currently I am storing the event locations in a 2d array in the format of grid[x][y]. This however, only allows the storing of one event per location. If you wanted to store mulitple events in a single location you could flatten the 2d array so that the first dimension of the array expresses all the cells(instead of just one axis), and the second expresses an event at that cell. This would change the array format to grid[(y*gridWidth)+x][EventNumber].

A simpler but less scalable solution would simply be to use a Map, and Map each coordinate to a List of events. 

#### How would you change your program if you were working with a much larger world size?

The biggest change that would need to be made in order to implement this on a larger world size is the method of searching for the closest events. Currently I look through all the events, calculate the distance to each of them, and then sort them by distance. This is the simplest and most easy to read and understand method, and is more then adequate for a grid of this size. However, on much larger grids (e.g. the size of a country) this would not be practical. Instead I would implement a search technique that radiates outwards from the users location, i.e. first search squares distance 1 away, then distance 2 etc. Until 5 events have been found.


