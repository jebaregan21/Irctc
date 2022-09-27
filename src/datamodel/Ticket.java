package datamodel;

import model.Passenger;

public class Ticket {
    private final String name, gender, berth;
    private final int age;
    private final int seat;

    public Ticket(Passenger passenger, String berth, int seat) {
        this.name = passenger.getName();
        this.gender = passenger.getGender();
        this.age = passenger.getAge();
        this.berth = berth;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getBerth() {
        return berth;
    }

    public int getSeat() {
        return seat;
    }

    public String toString(){
        return "Name : "+name+
                "\nGender : "+
                "\nAge : "+age+
                "\nSeat : "+seat+
                "\nBerth : "+berth;
    }
}
