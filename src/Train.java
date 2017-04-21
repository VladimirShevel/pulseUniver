import java.util.ArrayList;

/**
 * Created by Vladimir on 18.03.2017.
 */
public class Train {
    private static int trainNumber= 1000;
    private ArrayList<Wagon> wagons;
    private Driver driver;
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Train(ArrayList<Wagon> wagons) {
        this.wagons = wagons;}

    public ArrayList<Wagon> getWagons() {
        return wagons;
    }

    public static ArrayList<Wagon> makeTrain(ArrayList<Wagon> depo) {
        int wagonCount = 0;
        int headWagonCout = 0;
        ArrayList<Wagon> train = new ArrayList<>();
        for (Wagon wagon : depo){
            if ((wagonCount+headWagonCout)>=5) break;

            if (headWagonCout == 0 && wagon instanceof HeadWagon) {
                train.add(wagon); headWagonCout++;}
                if (headWagonCout == 1 && wagonCount < 3 && (wagon.getClass().getSimpleName()).equals("Wagon")) {
                    train.add(wagon); wagonCount++;}
                if (headWagonCout == 1 && wagonCount == 3) if (wagon instanceof HeadWagon) {
                    train.add(wagon); headWagonCout++;}
        }
        if (train.size()== 5) { trainNumber++;
            System.out.println("Train "+ trainNumber + " created");return train;} else {
            System.out.println("Train create FALSE"); return null;}
    }

    public void moveTrain(){
        try {
            Thread.sleep(1000 * (int) (Math.random() * 3 + 1));
            System.out.println(this + "GFFFFFFFF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        String train="";
        for (Wagon x : wagons)
            train += (x + "-");
        return train;
    }
}
