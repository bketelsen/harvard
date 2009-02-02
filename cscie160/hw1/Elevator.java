package cscie160.hw1;

/**
 * Harvard CSCIE 160 
 * Homework Assignment HW1
 * Brian Ketelsen
 * <p>
 * Requirements:
 * <ul>
 * <li>Create an Elevator class in the package cscie160.hw1. The class should have a no argument constructor that sets up the elevator's state.</li>
 * <li>Define several constant values in the Elevator class. Include the capacity and number of floors in the building.</li>
 * <li>Augment the Elevator with data members indicating the current floor and current direction of travel. Also add data members for both its destination requests and the number of passengers destined for each floor. [It's tempting to think that an Elevator can determine if a given floor is among its destinations just by checking to see if it has any passengers destined for the floor. But this will not stand up under the later requirement that an Elevator could be destined for a particular floor just to fetch passengers, while having none on board who are going there.]</li>
 * <li>Add a method move() to the elevator with the characteristic that every time it is called it increments or decrements the value of the current floor, according to whether the elevator is moving up or down, changing directions as appropriate.</li>
 * <li>Override the toString() method of java.lang.Object (the default base class of every Java class) in your Elevator class which returns a String object summarizing all the pertinent values in the elevator, including the number of passengers on board and the current floor. [The call to System.out.println() will accept any object as an argument and will look for a toString method for a means to create a String it can write to standard out. Thus, if Elevator is equipped with toString, then System.out.println(myElevator) will get a String from myElevator.toString() and send it to standard out.]</li>
 * <li>When the Elevator reaches a floor that is among its destinations it should stop, otherwise it should keep moving. Write the stop() method in such a way that it prints a message to standard out announcing the event of its stopping and have it send your Elevator to standard out after all processing for the stop is complete, i.e. after discharging passengers, to show its post-stop-processing state.</li>
 * <li>Stopping should entail appropriate changes in state. For instance, if an Elevator had two passengers destined for a particular floor, stopping there should clear the number of passengers destined there and the number of passengers on board should decrease by two and eliminate the floor as a destination of the Elevator. [The class Floor comes in the next homework so you needn't worry about what happens to the passengers after the Elevator stops.]</li>
 * <li>Endow your Elevator with a method, boardPassenger(int floor), for boarding a passenger destined for a particular floor. Calling this method should increase the number of passengers destined there by one, and should increase the number of passengers on board by one. [Note: this does not require defining a class Passenger.] For now, allow your elevator to fill beyond its capacity, accepting more passengers than it can hold. In homework 2, we will use an exception to handle this error case and limit the occupancy in the elevator.</li>
 * <li>Add self-documenting javadoc comments to your class. Run the javadoc utility on your class and submit the resulting HTML file, Elevator.html, with your work. If you are submitting hard copy, submit a printout from your browser displaying the html documentation for the class. [This requirement will apply to every homework during the course.]</li>
 * </ul>
 *
 * @param  url  an absolute URL giving the base location of the image
 * @param  name the location of the image, relative to the url argument
 * @return      the image at the specified URL
 * @see         Image
 */
public class Elevator {

    /** 
     * <CODE>MAXIMIMUM_CAPACITY</code> declaration
	 * Assumed to be 10 for this assignment
     */
    public static final int MAXIMUM_CAPACITY = 10;

	/** 
 	* <CODE>NUMBER_OF_FLOORS</code> declaration
    * Assumed to be 7 for this assignment
    */
    public static final int NUMBER_OF_FLOORS = 7;

/** 
* Embedded class/enumeration <code>DirectionOfTravel</code>
*/
    enum DirectionOfTravel {
        UP, DOWN, STOPPED
    }

	/** 
	* Embedded class/enumeration <code>Floor</code>
	* Floor encapsulates all the logic and data surrounding floors.
	* Floor contains constant names e.g. <code>FIRST</code> for each floor.
	*/
    public enum Floor {
        FIRST("FIRST", 0, false),
        SECOND("SECOND", 0, false),
        THIRD("THIRD", 0, false),
        FOURTH("FOURTH", 0, false),
        FIFTH("FIFTH", 0, false),
        SIXTH("SIXTH", 0, false),
        SEVENTH("SEVENTH", 0, false);

        private final int _number;
        private final String _name;
        private int _queuedPassengers;
        private boolean _hasDestinationRequests;
        private Floor _nextUp;
        private Floor _nextDown;

		/** 
		* Default enumeration constructor
		* @param name					The name of the floor being created
		* @param queuedPax				Number of passengers queued for that floor
		* @param hasDestinationRequests	Boolean indicator of whether the floor has destination requests pending
		*/
        Floor(String name, int queuedPax, boolean hasDestinationRequests) {
            this._number = this.ordinal() + 1;
            this._name = name;
            this._queuedPassengers = queuedPax;
            this._hasDestinationRequests = hasDestinationRequests;
        }
		/** 
		* Default accessor for floor number
		* @return 				The number of the floor instance
		*/
        public int floorNumber() {
            return _number;
        }
		/** 
		* Default accessor for floor name
		* @return 				The name of the floor instance
		*/
        public String floorName() {
            return _name;
        }

		/** 
		* Default accessor for number of queued passengers
		* @return 				The number of queued passengers for the floor instance
		*/
        public int queuedPassengers() {
            return _queuedPassengers;
        }
		/** 
		* Adds a passenger to the list of passengers queued for this floor instance
		*/
        public void addQueuedPassenger(){
            this._queuedPassengers +=1;
        }
		/** 
		* Clears the queued passenger list for this floor - to be used when the elevator stops and passengers disembark
		*/
        public void clearQueuedPassengers(){
            this._queuedPassengers = 0;
        }

		/** 
		* nextFloorUp
		* Convenience method to return the next floor up on the chain
		* Added to facilitate moving up and down without complicated if statements
		* Artifact of design decision to use embedded enumeration of floors instead of simple floor array
		*/
        public Floor nextFloorUp() {
            Floor next = Floor.FIRST; // default value
            switch(_number){
                case 1:  next =  Floor.SECOND;
                    break;
                case 2:  next = Floor.THIRD;
                    break;
                case 3:  next = Floor.FOURTH;
                    break;
                case 4:  next =  Floor.FIFTH;
                    break;
                case 5:  next = Floor.SIXTH;
                    break;
                case 6:  next =  Floor.SEVENTH;
                    break;

            }
            return next;
        }
		/** 
		* nextFloorDown
		* Convenience method to return the next floor down on the chain
		* Added to facilitate moving up and down without complicated if statements
		* Artifact of design decision to use embedded enumeration of floors instead of simple floor array
		*/
        public Floor nextFloorDown() {
            Floor next = Floor.FIRST; // default value
            switch(_number){
                case 2:  next = Floor.FIRST;
                    break;
                case 3:  next = Floor.SECOND;
                    break;
                case 4:  next =  Floor.THIRD;
                    break;
                case 5:  next = Floor.FOURTH;
                    break;
                case 6:  next =  Floor.FIFTH;
                    break;
                case 7:  next = Floor.SIXTH;
                    break;

            }
            return next;

        }

		/** 
		* makeDestinationRequest
		* Modifies the <code>hasDestinationRequests</code> flag to be <code>true</code>
		*/
        public void makeDestinationRequest() {
            this._hasDestinationRequests = true;
        }
		/** 
		* clearDestinationRequest
		* Modifies the <code>hasDestinationRequests</code> flag to be <code>false</code>
		*/
        public void clearDestinationRequest() {
            this._hasDestinationRequests = false;
        }

		/** 
		* hasDestinationRequests
		* @return				<code>true</code> if the floor has pending destination requests
		*/
        public boolean hasDestinationRequests(){
            return this._hasDestinationRequests;
        }


    }


    private DirectionOfTravel _directionOfTravel;
    private Floor _currentFloor;
    private int _passengersOnboard;
    private java.util.HashMap _floorQueue;


    Elevator() {
        _currentFloor = Floor.FIRST;
        _passengersOnboard = 0;
        _directionOfTravel = DirectionOfTravel.UP;

    }

    public void move() {

        if (_currentFloor == Floor.FIRST) {
            _directionOfTravel = DirectionOfTravel.UP;
        }
        if (_currentFloor == Floor.SEVENTH) {
            _directionOfTravel = DirectionOfTravel.DOWN;
        }


        if (_directionOfTravel == DirectionOfTravel.UP) {
            _currentFloor = _currentFloor.nextFloorUp();
        } else if (_directionOfTravel == DirectionOfTravel.DOWN) {
           _currentFloor = _currentFloor.nextFloorDown();
        }

        if(_currentFloor.hasDestinationRequests()){
            stop();
        } 

    }

    private void stop() {
        System.out.println("Stopping on "+ _currentFloor.floorName() +" floor " );
        _currentFloor.clearDestinationRequest();
        _passengersOnboard = _passengersOnboard - _currentFloor.queuedPassengers();
        _currentFloor.clearQueuedPassengers();
        System.out.println(this);

    }

    public void boardPassenger(int floor){
        _passengersOnboard +=1;
        switch(floor){
            case 1: Floor.FIRST.makeDestinationRequest();
                Floor.FIRST.addQueuedPassenger();
                break;
            case 2: Floor.SECOND.makeDestinationRequest();
                Floor.SECOND.addQueuedPassenger();
                break;
            case 3: Floor.THIRD.makeDestinationRequest();
                Floor.THIRD.addQueuedPassenger();
                break;
            case 4: Floor.FOURTH.makeDestinationRequest();
                Floor.FOURTH.addQueuedPassenger();
                break;
            case 5: Floor.FIFTH.makeDestinationRequest();
                Floor.FIFTH.addQueuedPassenger();
                break;
            case 6: Floor.SIXTH.makeDestinationRequest();
                Floor.SIXTH.addQueuedPassenger();
                break;
            case 7: Floor.SEVENTH.makeDestinationRequest();
                Floor.SEVENTH.addQueuedPassenger();
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("Currently " + _passengersOnboard + " Passengers Onboard\r\n");
        output.append("On Floor   : " + _currentFloor + "\r\n");
        return output.toString();

    }

    public static void main(String[] args) {
        Elevator theElevator = new Elevator();

        theElevator.boardPassenger(2);
        theElevator.boardPassenger(2);
        theElevator.boardPassenger(3);

        System.out.println(theElevator);

        for(int i=1;i<21;i++){
        theElevator.move();
        }
        

        

    }

}
