package datamodel;

import model.Passenger;

public class Ticket {
    private final String name, gender, berth;
    private final int age;
    private final int seat;
    private final String carriage;
    public Ticket(Passenger passenger, String berth, int seat , String carriage) {
        this.name = passenger.getName();
        this.gender = passenger.getGender();
        this.age = passenger.getAge();
        this.berth = berth;
        this.seat = seat;
        this.carriage = carriage;
    }

    public String toString(){
        return "Name : "+name+
                "\tGender : "+gender+
                " \tAge : "+age+
                "\tSeat : "+carriage+" "+seat+
                "\tBerth : "+berth;
    }
}
