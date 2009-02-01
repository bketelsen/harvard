package cscie160.hw1;

/**
 * User: Brian Ketelsen
 * Date: Feb 1, 2009
 * Time: 12:56:05 PM
 */
public class Elevator {

    public static final int MAXIMUM_CAPACITY = 10;
    public static final int NUMBER_OF_FLOORS = 7;

    enum DirectionOfTravel {
        UP, DOWN, STOPPED
    }


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

        Floor(String name, int queuedPax, boolean hasDestinationRequests) {
            this._number = this.ordinal() + 1;
            this._name = name;
            this._queuedPassengers = queuedPax;
            this._hasDestinationRequests = hasDestinationRequests;
        }

        public int floorNumber() {
            return _number;
        }

        public String floorName() {
            return _name;
        }

        public int queuedPassengers() {
            return _queuedPassengers;
        }

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

        public void makeDestinationRequest() {
            this._hasDestinationRequests = true;
        }

        public void clearDestinationRequest() {
            this._hasDestinationRequests = false;
        }
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
        _directionOfTravel = DirectionOfTravel.STOPPED;

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
        } else {
            move();
        }

    }

    private void stop() {
        _currentFloor.clearDestinationRequest();


        

    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("Currently " + _passengersOnboard + " Passengers Onboard\r\n");
        output.append("On Floor   : " + _currentFloor + "\r\n");
        output.append("Direction of Travel : " + _directionOfTravel + "\r\n");
        return output.toString();

    }

    public static void main(String[] args) {
        Elevator theElevator = new Elevator();
        System.out.println(theElevator.toString());

        Floor.SECOND.makeDestinationRequest();

        theElevator.move();
        System.out.println(theElevator.toString());


        

    }

}
