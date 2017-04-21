import java.util.ArrayList;

/**
 * Created by Vladimir on 08.04.2017.
 */
public class Escalator implements Runnable {
    public String name;
    public ArrayList<Passenger> escalatorPassengers;
    public ArrayList<Passenger> vestibulePassengers;

    public Escalator(String name, ArrayList<Passenger> vestibulePassengers) {
        this.name = name;
        this.escalatorPassengers = new ArrayList<>();
        this.vestibulePassengers = vestibulePassengers;
    }

    @Override

    public void run() {
        while (!Thread.interrupted()){
            try {

                synchronized (vestibulePassengers)
                {
               if (vestibulePassengers.isEmpty()) vestibulePassengers.wait();
               else {
                Passenger passenger = vestibulePassengers.remove(0);
                escalatorPassengers.add(passenger);
                System.out.println("Пассажир " + passenger.hashCode() + " сел на " + name);
                }
                }

            }
            catch (IndexOutOfBoundsException e){
                System.out.println("В вестибюле пусто.");

           } catch (InterruptedException e) {
                System.out.println("Eskalator " + name + " slomalsya");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}