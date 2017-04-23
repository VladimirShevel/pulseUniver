import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Vladimir on 25.03.2017.
 */
public class Trip implements Runnable {
    public Driver driver;
    public Train train;
    public Line line;
    private CyclicBarrier barrier;
    CountDownLatch changePassengersStart;
    CountDownLatch changePassengersFinish;



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
                    try {
                        changePassengersStart.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!wagon.wagonPassengers.isEmpty())
                        System.out.println("Пассажиры выходят из вагона." + wagon);
                        for (int i = 0; i < (int) (wagon.wagonPassengers.size() * 0.8); i++) {
                        if (wagon.wagonPassengers.isEmpty()) break;
                        station.arrivedPassengers.add(wagon.wagonPassengers.remove(0));

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!station.departingPassengers.isEmpty()){
                        int capacity = wagon.maxCapacity - wagon.wagonPassengers.size()-1;
                        System.out.println("Пассажиры садятся в вагон." + wagon);
                        for (int i = 0; i < capacity; i++) {
                            if (station.departingPassengers.isEmpty()) break;
                            wagon.wagonPassengers.add(station.departingPassengers.remove(0));
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Вагон " + wagon + " готов ехать.");
                    changePassengersFinish.countDown();
                }
            }).start();

       }
    }
    @Override
    public void run() {
        for (Station station : line.getStationList()) {
            System.out.println();
            train.moveTrain();
            station.haveTrain = true;
            changePassengersStart = new CountDownLatch(1);
            changePassengersFinish = new CountDownLatch(5);
            changePassengers(train, station);
            try {
                changePassengersStart.countDown();
                changePassengersFinish.await();
                System.out.println("поезд " + train + "уехал со станции " + station);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
               //station.haveTrain = false;

        }
    }
}