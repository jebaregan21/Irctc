package model;

import datamodel.Time;

import java.util.*;

public class Train {
    private final int trainNo;
    private final String name;
    private final Map<Station, Time> stationTimeMap = new LinkedHashMap<>();
    private final Map<String,List<Carriage>> carriageListMap = new HashMap<>();
    private final Map<String,Integer> priceMap = new HashMap<>();

    public Train(int trainNo, String name){
        this.trainNo = trainNo;
        this.name = name;
    }

    public String getAvailability(){
        String result = "";
        for(String type : carriageListMap.keySet()){
            int generalSeats = 0, tatkalSeats = 0;
            for(Carriage carriage : carriageListMap.get(type)){
                generalSeats += carriage.getGeneralAvailability();
                tatkalSeats += carriage.getTatkalAvailability();
            }
            result += getAvailability(type,generalSeats,tatkalSeats);
        }
        return result;
    }

    private String getAvailability(String type, int generalSeats, int tatkalSeats){
        return "Class : "+type+
                "\nGeneral Seats : "+generalSeats+
                "\nTatkal Seats : "+tatkalSeats;
    }

    public void addCarriage(Carriage carriage, int price){
        String type = carriage.getType();
        setPrice(carriage,price);
        if(carriageListMap.containsKey(type)) {
            carriageListMap.get(type).add(carriage);
            return;
        }
        List<Carriage> carriageList = new ArrayList<>();
        carriageList.add(carriage);
        carriageListMap.put(type, carriageList);
    }

    private void setPrice(Carriage carriage, int price){
        priceMap.put(carriage.getType(), price);
    }
}
