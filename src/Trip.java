import java.util.ArrayList;

/**
 * Created by Vladimir on 25.03.2017.
 */
public class Trip implements Runnable {
    public Driver driver;
    public Train train;
    public Line line;

    public Trip(Train train, Line line, Driver driver) {
        this.line = line;
        this.train = train;
        this.driver = driver;
    }

    public void changePassengers(Train train, Station station) {
        for (Wagon wagon : train.getWagons()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (!wagon.wagonPassengers.isEmpty())
                        for (int i = 0; i < (int) (wagon.wagonPassengers.size() * 0.8); i++) {
                            station.arrivedPassengers.add(wagon.wagonPassengers.remove(i));
                        }
                    if (!station.departingPassengers.isEmpty())
                        for (int i = 0; i < (int) (station.departingPassengers.size() * .25); i++) {
                            wagon.wagonPassengers.add(station.departingPassengers.remove(i));
                        }
                }
            }).start();
        }
    }

    @Override
    public void run() {
        for (Station station : line.getStationList()) {
            train.moveTrain();
            if (station.haveTrain) try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            else {
                station.haveTrain = true;
                changePassengers(train, station);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                station.haveTrain = false;
            }
        }
    }
}