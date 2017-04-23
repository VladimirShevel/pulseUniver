import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vladimir on 18.03.2017.
 */
public class Wagon {
    public int wagonID;
    public static int maxCapacity = 20;
    public List<Passenger> wagonPassengers;

    public Wagon(int wagonID) {
        this.wagonID = wagonID;
        this.wagonPassengers = Collections.synchronizedList(new ArrayList<Passenger>());
    }

    //public int getCapacity() {
      //  return capacity;
  //  }

 //   public void setCapacity() {
 //       this.capacity = capacity + Station.passengers.size();
 //   }

//    public void removePassangers() {
//        this.capacity = getCapacity() - 5;
//    }

    @Override
    public String toString() {

        return "|[" + wagonID + ":" + wagonPassengers.size() + ":]|";
    }
}

