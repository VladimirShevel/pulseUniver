import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vladimir on 25.03.2017.
 */
public class Station {
    public boolean haveTrain = false;
    public ArrayList<Passenger> vestibuleInPassengers;
    public ArrayList<Passenger> vestibuleOutPassengers;
    public List<Passenger> departingPassengers;
    public List<Passenger> arrivedPassengers;
    public String name;

    public Station(String name) {
        this.name = name;
        this.departingPassengers = Collections.synchronizedList(new ArrayList<Passenger>());
        for (int i = 0; i < 50; i++) {
            this.departingPassengers.add(new Passenger());
        }
        this.arrivedPassengers = Collections.synchronizedList(new ArrayList<Passenger>());
        this.vestibuleOutPassengers = new ArrayList<Passenger>();
        this.vestibuleInPassengers = new ArrayList<Passenger>();
    }

    @Override
    public String toString() {
        return " " + name + " ";
    }


}