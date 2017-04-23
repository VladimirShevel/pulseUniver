/**
 * Created by Vladimir on 18.03.2017.
 */
public class HeadWagon extends Wagon {
    public HeadWagon(int wagonID) {
        super(wagonID);
    }

    @Override
    public String toString() {
        return "<|[" + wagonID + ":" + wagonPassengers.size() + ":]|>";
    }
}
