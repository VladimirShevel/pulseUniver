import java.util.ArrayList;

/**
 * Created by Vladimir on 25.03.2017.
 */
public class Station {
    public boolean haveTrain = false;
    public ArrayList<Passenger> vestibuleInPassengers;
    public ArrayList<Passenger> vestibuleOutPassengers;
    public ArrayList<Passenger> departingPassengers;
    public ArrayList<Passenger> arrivedPassengers;
    public String name;

    public Station(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " " + name + " ";
    }


}