/**
 * Created by Vladimir on 18.03.2017.
 */
public class Driver {
    public int tripsCounter;
    private String name;
    private int sciles;

    public Driver(String name, int sciles) {
        this.name = name;
        this.sciles = sciles;
    }

    public int getSciles() {
        return sciles;
    }

    public String getName() {
        return name;
    }

    public void setSciles() {
     //  System.out.println("Машинисту "+ this + " добавили опыт.");
        sciles++;
    }



    @Override
    public String toString() {
        return "{ "+ name + " опыт " + sciles + " }" ;
    }
}
