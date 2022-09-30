package model;

import datamodel.Ticket;
import datamodel.Time;
import exception.NoVacancyException;
import utility.CloneUtility;

import java.util.*;

public class Train {
    private final int trainNo;
    private final String name;
    private final Map<Station, Time> stationTimeMap;
    private final Map<String,List<Carriage>> carriageListMap;
    private final Map<String,Integer> priceMap;

    private final Map<Station,Integer> route = new HashMap<>();
    public Train(int trainNo, String name){
        this.trainNo = trainNo;
        this.name = name;
        stationTimeMap = new LinkedHashMap<>();
        priceMap = new HashMap<>();
        carriageListMap = new HashMap<>();
    }

    public Train(Train train){
        this.name = train.name;
        this.trainNo = train.trainNo;
        this.stationTimeMap = train.stationTimeMap;
        priceMap = train.priceMap;
        this.carriageListMap = CloneUtility.cloneCarriageMap(train.carriageListMap);
    }

    public int getTrainNo() {
        return trainNo;
    }

    public String getAvailability(){
        StringBuilder result = new StringBuilder();
        for(String type : carriageListMap.keySet()){
            int generalSeats = 0, tatkalSeats = 0;
            for(Carriage carriage : carriageListMap.get(type)){
                generalSeats += carriage.getGeneralAvailability();
                tatkalSeats += carriage.getTatkalAvailability();
            }
            result.append(getAvailability(type, generalSeats, tatkalSeats));
        }
        return result.toString();
    }

    private String getAvailability(String type, int generalSeats, int tatkalSeats){
        return "Class : "+type+
                "\nGeneral Seats : "+generalSeats+
                "\nTatkal Seats : "+tatkalSeats;
    }

    public void addCarriage(Carriage carriage, int price){
        String type = carriage.getType();
        setPrice(carriage,price);
        carriage.setRoute(route);
        if(carriageListMap.containsKey(type)) {
            carriage.setName(type+""+(carriageListMap.get(type).size()+1));
            carriageListMap.get(type).add(carriage);
            return;
        }
        List<Carriage> carriageList = new ArrayList<>();
        carriageList.add(carriage);
        carriage.setName(type+""+1);
        carriageListMap.put(type, carriageList);
    }

    private void setPrice(Carriage carriage, int price){
        priceMap.put(carriage.getType(), price);
    }

    public int getPrice(String type){
        return priceMap.get(type);
    }
    public List<Ticket> bookGeneralTicket(List<Passenger> passengers, String type) throws NoVacancyException {
        List<Carriage> carriageList = carriageListMap.get(type);
        List<Ticket> resultList = new ArrayList<>();
        for(Passenger passenger : passengers){
            Ticket ticket = bookGeneralTicket(carriageList,passenger);
            resultList.add(ticket);
        }
        return resultList;
    }

    public List<Ticket> bookTatkalTicket(List<Passenger> passengers, String type) throws NoVacancyException {
        List<Carriage> carriageList = carriageListMap.get(type);
        List<Ticket> resultList = new ArrayList<>();
        for(Passenger passenger : passengers){
            Ticket ticket = bookTatkalTicket(carriageList,passenger);
            resultList.add(ticket);
        }
        return resultList;
    }

    private Ticket bookGeneralTicket(List<Carriage> carriageList, Passenger passenger) throws NoVacancyException {
        Ticket ticket = null;
        for(Carriage carriage : carriageList){
            ticket = carriage.bookGeneralTicket(passenger);
            if(ticket!=null)
                break;
        }
        if(ticket==null)
            throw new NoVacancyException();
        return ticket;
    }

    private Ticket bookTatkalTicket(List<Carriage> carriageList, Passenger passenger) throws NoVacancyException {
        Ticket ticket = null;
        for(Carriage carriage : carriageList){
            ticket = carriage.bookGeneralTicket(passenger);
            if(ticket!=null)
                break;
        }
        if(ticket==null)
            throw new NoVacancyException();
        return ticket;
    }

    public void addStop(Station station, Time time){
        stationTimeMap.put(station,time);
        station.addToTrainSet(trainNo);
        route.put(station,route.size());
    }

    public List<Map<Integer,Passenger>> getChart(){
        List<Map<Integer,Passenger>> result = new ArrayList<>();
        for(String type : carriageListMap.keySet()){
            List<Carriage> carriageList = carriageListMap.get(type);
            for(Carriage carriage : carriageList){
                result.add(carriage.getChart());
            }
        }
        return result;
    }

    public String toString(){
        return name+" "+trainNo;
    }
}
