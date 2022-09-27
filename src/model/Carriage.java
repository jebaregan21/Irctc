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

    public Carriage(int capacity, String type, int tatkalPercentage){
        this.capacity = capacity;
        this.type = type;
        tatkalSeatCount = capacity*(tatkalPercentage/100);
        generalSeatCount = capacity - tatkalSeatCount;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public Ticket bookGeneralTicket(Passenger passenger) throws NoVacancyException {
        if(generalSeatCount<=0)
            throw new NoVacancyException();
        Ticket ticket = bookTicket(passenger);
        generalSeatCount--;
        return ticket;
    }

    public Ticket bookTatkalTicket(Passenger passenger) throws NoVacancyException{
        if(tatkalSeatCount<=0)
            throw new NoVacancyException();
        Ticket ticket = bookTicket(passenger);
        tatkalSeatCount--;
        return ticket;
    }

    private Ticket bookTicket(Passenger passenger) throws NoVacancyException {
        int seat = -1;
        try {
            if(passenger.getPreference()!=null)
                seat = findPreferredSeat(passenger.getPreference());
        }catch (NoVacancyException e){
            try{
                seat = findAvailableSeat();
            }
            catch (NoVacancyException exception){
                throw new NoVacancyException();
            }
        }
        bookedTickets.put(seat,passenger);
        return new Ticket(passenger,getBerth(seat), seat);
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
}
