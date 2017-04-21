import java.util.ArrayList;

/**
 * Created by Vladimir on 25.03.2017.
 */
public class Line {
    private String name;
    private ArrayList<Train> trainList;
    private ArrayList<Station> stationList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Train> getTrainList() {
        return trainList;
    }

    public void setTrainList(Train train) {
        trainList.add(train);
    }

    public ArrayList<Station> getStationList() {
        return stationList;
    }

    public void setStationList(Station station) {
        stationList.add(station);
    }

    public Line(String name) {
        this.name = name;
        this.trainList = new ArrayList<Train>();
        this.stationList = new ArrayList<Station>();
    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", trainList=" + trainList.size() +
                ", stationList=" + stationList.size() +
                '}';
    }
}
