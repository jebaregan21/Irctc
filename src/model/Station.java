package model;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private final String name, code;
    private final List<Integer> trainList = new ArrayList<>();

    public Station(String name, String code){
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void addToTrainList(int trainNo){
        trainList.add(trainNo);
    }

    public List<Integer> getTrainList(){
        return new ArrayList<>(trainList);
    }
}
