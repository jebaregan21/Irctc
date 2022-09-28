package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Station {
    private final String name, code;
    private final Set<Integer> trainSet = new HashSet<>();

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

    public void addToTrainSet(int trainNo){
        trainSet.add(trainNo);
    }

    public Set<Integer> getTrainSet(){
        return new HashSet<>(trainSet);
    }
}
