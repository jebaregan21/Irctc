package database;

import datamodel.TrainSearchModel;
import datamodel.Time;
import model.Station;
import model.Train;
import utility.DateUtility;

import java.text.ParseException;
import java.util.*;

public class RailwayDatabase {
    private static RailwayDatabase railwayDatabase;
    private Map<String,String> stationCodeMap = new HashMap<>();
    private Map<String, Station> stationMap = new HashMap<>();
    private Map<Date, Map<Integer, Train>> journeyMap = new HashMap<>();
    private Map<Integer,Train> trainMap = new HashMap<>();

    private RailwayDatabase(){}
    public void addStation(Station station){
        stationCodeMap.put(station.getName(),station.getCode());
        stationMap.put(station.getCode(), station);
    }

    public Train getTrain(int trainNo){
        return trainMap.get(trainNo);
    }

    public Station getStation(String code){
        return stationMap.get(code);
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

    public static RailwayDatabase getInstance(){
        if(railwayDatabase==null)
            railwayDatabase = new RailwayDatabase();
        return railwayDatabase;
    }

    public void addTrain(Train train){
        trainMap.put(train.getTrainNo(),train);
        addMultipleTrainInstances(train);
        System.out.println(journeyMap);
    }

    private void addMultipleTrainInstances(Train train){
        DateUtility dateUtility;
        try {
            dateUtility = new DateUtility("30/09/2022");
        } catch (ParseException e) {
            System.err.println("Invalid date");
            return;
        }
        for(int increment=1;increment<=7;increment++){
            Train tempTrain = new Train(train);
            Date tempDate = dateUtility.addToDate(increment);
            insertTrainInstance(tempTrain,tempDate);
        }
    }

    private void insertTrainInstance(Train train, Date date){
        if(journeyMap.containsKey(date)){
            journeyMap.get(date).put(train.getTrainNo(),train);
        }
        else{
            Map<Integer,Train> tempMap = new HashMap<>();
            tempMap.put(train.getTrainNo(),train);
            journeyMap.put(date,tempMap);
        }
    }

    public List<Train> getTrainsBetween(TrainSearchModel model){
        Set<Integer> sourceTrainSet = model.getSource().getTrainSet();
        Set<Integer> desTrainSet = model.getDestination().getTrainSet();
        List<Train> resultList = new ArrayList<>();
        for(int trainNo : sourceTrainSet){
            if(desTrainSet.contains(trainNo)){
                if(doesReach(trainNo,model.getSource(),model.getDestination()))
                    resultList.add(getTrain(trainNo));
            }
        }
        return resultList;
    }

    private boolean doesReach(int trainNo, Station source, Station destination){
        Train train = getTrain(trainNo);
        Map<Station, Time> routeMap = train.getRoute();
        Time sourceTime = routeMap.get(source);
        Time desTime = routeMap.get(destination);
        if(sourceTime.compareTo(desTime)<0){
            return true;
        }
        return false;
    }
}
