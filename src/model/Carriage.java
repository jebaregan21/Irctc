package model;

import datamodel.Ticket;
import exception.NoVacancyException;

import java.util.HashMap;
import java.util.Map;


public class Carriage {
    private final Map<Integer, Passenger> bookedTickets = new HashMap<>();
    private final Map<String, Integer> layoutMap = new HashMap<>();
    private final Map<Integer, String> berthMap = new HashMap<>();
    private final int capacity;
    private int tatkalSeatCount;
    private int generalSeatCount;
    private final String type;
    private String name;

    public Carriage(int capacity, String type, int tatkalPercentage, String layout){
        this.capacity = capacity;
        this.type = type;
        tatkalSeatCount = capacity*(tatkalPercentage/100);
        generalSeatCount = capacity - tatkalSeatCount;
        setLayout(layout);
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

    private void setLayout(String layout){
        String[] berths = layout.split(" ");
        int count = 1;
        for(String berth : berths){
            layoutMap.put(berth,count);
            berthMap.put(count,berth);
        }
    }

    public Ticket bookGeneralTicket(Passenger passenger){
        if(generalSeatCount<=0)
            return null;
        Ticket ticket = bookTicket(passenger);
        generalSeatCount--;
        return ticket;
    }

    public Ticket bookTatkalTicket(Passenger passenger){
        if(tatkalSeatCount<=0)
            return null;
        Ticket ticket = bookTicket(passenger);
        tatkalSeatCount--;
        return ticket;
    }

    private Ticket bookTicket(Passenger passenger){
        int seat = -1;
        try {
            if(passenger.getPreference()!=null)
                seat = findPreferredSeat(passenger.getPreference());
        }catch (NoVacancyException e){
            try{
                seat = findAvailableSeat();
            }
            catch (NoVacancyException exception){
                return null;
            }
        }
        bookedTickets.put(seat,passenger);
        return new Ticket(passenger,getBerth(seat), seat, name);
    }

    private int findPreferredSeat(String preference) throws NoVacancyException {
        int seatNo = 1;
        int berth = layoutMap.get(preference);
        for(;seatNo<=capacity;seatNo++){
            if(getBerthInt(seatNo) == berth && !bookedTickets.containsKey(seatNo)){
                return seatNo;
            }
        }
        throw new NoVacancyException();
    }

    private int findAvailableSeat() throws NoVacancyException {
        int seatNo = 1;
        for (; seatNo <= capacity; seatNo++) {
            if (!bookedTickets.containsKey(seatNo))
                return seatNo;
        }
        throw new NoVacancyException();
    }

    private int getBerthInt(int seatNo){
        return seatNo%layoutMap.size()+1;
    }

    private String getBerth(int seatNo){
        return berthMap.get(getBerthInt(seatNo));
    }

    public int getTatkalAvailability(){
        return tatkalSeatCount;
    }

    public int getGeneralAvailability(){
        return generalSeatCount;
    }

    public Map getChart(){
        return new HashMap<>(bookedTickets);
    }
}
