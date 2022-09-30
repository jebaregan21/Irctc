package model;

import datamodel.CarriageInfo;
import datamodel.Ticket;
import exception.NoVacancyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Carriage {
    private final Map<Integer, List<Passenger>> bookedTickets = new HashMap<>();
    private final Map<String, Integer> layoutMap = new HashMap<>();
    private final Map<Integer, String> berthMap = new HashMap<>();
    private Map<Station, Integer> route;
    private final int capacity;
    private int tatkalSeatCount;
    private int generalSeatCount;
    private final String type;
    private String name;

    public Carriage(CarriageInfo carriageInfo) {
        this.capacity = carriageInfo.getCapacity();
        this.type = carriageInfo.getType();
        tatkalSeatCount = (int)(capacity * ((float)carriageInfo.getTatkalPercentage() / 100));
        generalSeatCount = capacity - tatkalSeatCount;
        setLayout(carriageInfo.getLayout());
    }

    public Carriage(Carriage carriage) {
        this.name = carriage.name;
        this.capacity = carriage.capacity;
        this.type = carriage.type;
        this.generalSeatCount = carriage.generalSeatCount;
        this.tatkalSeatCount = carriage.tatkalSeatCount;
        this.layoutMap.putAll(carriage.layoutMap);
        this.berthMap.putAll(carriage.berthMap);
        this.route = carriage.route;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoute(Map<Station, Integer> route) {
        this.route = route;
    }

    private void setLayout(String layout) {
        String[] berths = layout.split(" ");
        int count = 1;
        for (String berth : berths) {
            layoutMap.put(berth, count);
            berthMap.put(count, berth);
            count++;
        }
    }

    public Ticket bookGeneralTicket(Passenger passenger) {
        if (generalSeatCount <= 0)
            return null;
        Ticket ticket = bookTicket(passenger);
        generalSeatCount--;
        return ticket;
    }

    public Ticket bookTatkalTicket(Passenger passenger) {
        if (tatkalSeatCount <= 0)
            return null;
        Ticket ticket = bookTicket(passenger);
        tatkalSeatCount--;
        return ticket;
    }

    private Ticket bookTicket(Passenger passenger) {
        int seat = -1;
        try {
            if (passenger.getPreference() != null)
                seat = findPreferredSeat(passenger);
            else
                seat = findAvailableSeat(passenger);
        } catch (NoVacancyException e) {
            try {
                seat = findAvailableSeat(passenger);
            } catch (NoVacancyException exception) {
                return null;
            }
        }
        addPassengerToMap(seat,passenger);
        return new Ticket(passenger, getBerth(seat), seat, name);
    }

    private void addPassengerToMap(int seat, Passenger passenger){
        if(bookedTickets.containsKey(seat)) {
            bookedTickets.get(seat).add(passenger);
            return;
        }
        List<Passenger> tempList = new ArrayList<>();
        tempList.add(passenger);
        bookedTickets.put(seat,tempList);
    }

    private int findPreferredSeat(Passenger passenger) throws NoVacancyException {
        int seatNo = 1;
        String preference = passenger.getPreference();
        int berth = layoutMap.get(preference);
        for (; seatNo <= capacity; seatNo++) {
            if (getBerthInt(seatNo) == berth && doCollide(bookedTickets.get(seatNo),passenger)==false) {
                return seatNo;
            }
        }
        throw new NoVacancyException();
    }

    private int findAvailableSeat(Passenger passenger) throws NoVacancyException {
        int seatNo = 1;
        for (; seatNo <= capacity; seatNo++) {
            if (doCollide(bookedTickets.get(seatNo),passenger)==false)
                return seatNo;
        }
        throw new NoVacancyException();
    }

    private int getBerthInt(int seatNo) {
        return seatNo % layoutMap.size() + 1;
    }

    private String getBerth(int seatNo) {
        return berthMap.get(getBerthInt(seatNo-1));
    }

    public int getTatkalAvailability() {
        return tatkalSeatCount;
    }

    public int getGeneralAvailability() {
        return generalSeatCount;
    }

    public Map getChart() {
        return new HashMap<>(bookedTickets);
    }

    private boolean doCollide(List<Passenger> passengerList, Passenger newPassenger) {
        if(passengerList==null)
            return false;
        for (Passenger passenger : passengerList) {
            if (doCollide(passenger, newPassenger)) {
                return true;
            }
        }
        return false;
    }

    private boolean doCollide(Passenger passenger1, Passenger passenger2){
        int firstStart = route.get(passenger1.getBoardingPoint());
        int firstEnd = route.get(passenger1.getDropPoint());
        int secondStart = route.get(passenger2.getBoardingPoint());
        int secondEnd = route.get(passenger2.getDropPoint());
        if(secondStart>firstStart){
            if(secondStart-firstEnd<0)
                return true;
        }
        else if(firstStart>secondStart){
            if(firstEnd-secondStart<0)
                return true;
        }
        else if(firstStart==secondStart)
            return true;
        return false;
    }
}
