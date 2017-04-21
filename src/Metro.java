import java.util.*;

//Киевское метро.
//
//        Существуют вагоны двух типов: обчыные и головные. Каждый поезд метро состоит из 5 вагонов.
//        Три из которых могут быть любыми, а первый и последний только головным.
//        В депо (каждый вечер) загоняют все вагоны в случайном порядке.
//        Наполните депо вагонами.

//        Сформируйте несколько поездов из вагонов, принимая во внимание, что одновременно из депо может выехать один (последний) вагон.
//        Далее этот вагон может быть помещен в любое место поезда.
//        Сформируйте пул поездов, готовых выехать на линии (у  них есть один головной и один конечный вагон и вагонов всего 5)


public class Metro {
    public static boolean metroStop = false;


    public static void main(String[] args) throws InterruptedException {

        ArrayList<Wagon> depo = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            if (i % 3 == 0) {
                depo.add(new HeadWagon(2000 + i));
                depo.add(new HeadWagon(2001 + i));
            }
            depo.add(new Wagon(1000 + i));
        }
        for (Wagon d : depo)
            System.out.println(d);

        ArrayList<Train> trainList = new ArrayList<>();
        while (true) {
            try {
                ArrayList<Wagon> wagons = Train.makeTrain(depo);
                if (!wagons.equals(null)) trainList.add(new Train(wagons));
                depo.removeAll((trainList.get(trainList.size() - 1)).getWagons());
            } catch (NullPointerException e) {
                System.out.println("depo is empty");
                break;
            }
        }

        System.out.println(trainList.get(trainList.size()-1));


        System.out.println("==========================================================");
        Line line1 = new Line("Line1");
        for (int i = 0; i < 10; i++) {
            line1.getStationList().add(new Station(i+""));
        }
        Line line2 = new Line("Line2");
        Line line3 = new Line("Line3");

        System.out.println("Всего поездов " + trainList.size());
//        Iterator<Train> iterator = trainList.iterator();
//        while (iterator.hasNext()){
//            if (iterator.hasNext()) {line1.setTrainList(iterator.next()); iterator.remove();}
//            if (iterator.hasNext()) {line2.setTrainList(iterator.next()); iterator.remove();}
//            if (iterator.hasNext()) {line3.setTrainList(iterator.next()); iterator.remove();}
//        }

        Comparator<Driver> comparator = new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                if (o1.getSciles() > o2.getSciles()) return -1;
                if (o1.getSciles() < o2.getSciles()) return 1;
                else return 0;
            }
        };

        Queue<Driver> driverses = new PriorityQueue<Driver>(comparator);
        driverses.add(new Driver("Driv_1", 0));
        driverses.add(new Driver("Driver2", 0));
        driverses.add(new Driver("Driver3", 0));
        driverses.add(new Driver("Driver4", 0));
        driverses.add(new Driver("Driver5", 0));
        System.out.println("==========================================================");

        for (Driver d : driverses)
            System.out.println(d);


        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);

//        for (Train train : line1.getTrainList())
//            for (Station station : line1.getStationList())
//                for (Wagon wagon : train.getWagons()){
//                    wagon.removePassangers();
//                    wagon.setCapacity();
//                }
   //     for (Train train : trainList)
     //       System.out.println(train);

        for (Train train1 : trainList)

       //   Train train1 = trainList.get(0);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Driver driver = null;
                    try {
                        synchronized (driverses) {
                            do {
                              if  (driverses.isEmpty()) {
                                System.out.println("Поезд: " + train1 + " ждет машиниста");
                                driverses.wait();
                              }
                              else driver = driverses.poll();} while (driver==null);
                        }
                       // Thread.sleep(1000);

                        //driver.tripsCounter++;
                        train1.setDriver(driver);
                       // System.out.print(driver.getName() + " ");
                        System.out.println(Thread.currentThread().getName() + " " + driver + " едет на поезде " + train1);
                        Thread trip = new Thread(new Trip(train1, line1, driver));
                        trip.start();
                        trip.join();
                        //Thread.sleep(1000 * (int) (Math.random() * 3 + 1));
                        synchronized (driverses) {
                            driver.setSciles();
                            driverses.add(driver);driverses.notifyAll();
                            System.out.println("Машинист " + driver + " вернулся в депо");

                        }
                    } catch (NullPointerException e) {
                        System.out.println("Nety Sbododnyh Draiverov");
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


//                ArrayList<Passenger> vestibule = new ArrayList<Passenger>();
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//
//                    Passenger passenger = new Passenger();
//
//                    synchronized (vestibule) {
//                    vestibule.add(passenger);
//                    System.out.println("Пассажир "+ passenger.hashCode() + " Зашел в вестибюль.");
//                    vestibule.notifyAll();
//                    }
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//
//        thread.start();
//
//        Escalator escalator1 = new Escalator("Escalator1", vestibule);
//        Thread escalator1Thread = new Thread(escalator1);
//        escalator1Thread.start();
//
//        Escalator escalator2 = new Escalator("Escalator2", vestibule);
//        Thread escalator2Thread = new Thread(escalator2);
//        escalator2Thread.start();
//
//
//        Escalator escalator3 = new Escalator("Escalator3", vestibule);
//
//        Thread escalator3Thread = new Thread(escalator3);
//
//        escalator3Thread.start();
//
//
//
//        new Thread(new Escalator("Escalator4", vestibule)).start();
//
//
//        Thread.sleep(10000);
//
//        escalator3Thread.interrupt();
    }


}









