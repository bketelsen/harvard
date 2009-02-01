package cscie160.hw1;

/**
 * User: Brian Ketelsen
 * Date: Feb 1, 2009
 * Time: 12:56:05 PM
 */
public class Elevator {

    public static final int MAXIMUM_CAPACITY = 3;
    public static final int NUMBER_OF_FLOORS = 7;

    enum DirectionOfTravel {
        UP, DOWN, STOPPED
    }


    public enum Floor {
        FIRST(1, "FIRST"),
        SECOND(2, "SECOND"),
        THIRD(3, "THIRD"),
        FOURTH(4, "FOURTH"),
        FIFTH(5, "FIFTH"),
        SIXTH(6, "SIXTH"),
        SEVENTH(7, "SEVENTH");

        private final int number;
        private final String name;

        Floor(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public int floorNumber() {
            return number;
        }

        public String floorName() {
            return name;
        }

        public Floor nextFloor(DirectionOfTravel dot) {
            int floorOperand;
            if (dot == DirectionOfTravel.UP) {
                floorOperand = 1;
            } else {
                floorOperand = -1;
            }

            

            return floor;
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

    void move() {

    }

    void stop() {

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
    }

}
