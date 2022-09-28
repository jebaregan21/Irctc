package database;

import datamodel.Date;
import model.Station;
import model.Train;

import java.util.*;

public class RailwayDatabase {
    private Map<String,String> stationCodeMap = new HashMap<>();
    private Map<String, Station> stationMap = new HashMap<>();
    private Map<Date, Map<Integer, Train>> journeyMap = new HashMap<>();
    private Map<Integer,Train> trainMap = new HashMap<>();

    public void addStation(Station station){
        stationCodeMap.put(station.getName(),station.getCode());
        stationMap.put(station.getCode(), station);
    }

    public Train getTrain(int trainNo){
        return trainMap.get(trainNo);
    }

    public List<Train> getTrainsReaching(String stationName){
        String stationCode = stationCodeMap.get(stationName);
        Station station = stationMap.get(stationCode);
        Set<Integer> trainNoSet = station.getTrainSet();
        List<Train> result = new ArrayList<>();
        for(int trainNo : trainNoSet){
          result.add(trainMap.get(trainNo));
        }
        return result;
    }

}
