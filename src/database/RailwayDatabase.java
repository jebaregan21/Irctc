package database;

import datamodel.Date;
import model.Station;
import model.Train;

import java.util.HashMap;
import java.util.Map;

public class RailwayDatabase {
    private Map<String,String> stationCodeMap = new HashMap<>();
    private Map<String, Station> stationMap = new HashMap<>();
    private Map<Date, Map<Integer, Train>> journeyMap = new HashMap<>();
    private Map<Integer,Train> trainMap = new HashMap<>();

    public void addStation(Station station){
        stationCodeMap.put(station.getName(),station.getCode());
        stationMap.put(station.getCode(), station);
    }
}
