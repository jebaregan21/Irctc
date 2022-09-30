package model;

import datamodel.Quota;

public class Passenger {
    private final String name, gender;
    private String preference;
    private final int age;
    private final Quota quota;
    private Station boardingPoint, dropPoint;

    public Passenger(String name, String gender, int age, Quota quota, Station boardingPoint, Station dropPoint){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.quota = quota;
        this.boardingPoint = boardingPoint;
        this.dropPoint = dropPoint;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Station getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(Station boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public Station getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(Station dropPoint) {
        this.dropPoint = dropPoint;
    }

    public int getAge() {
        return age;
    }
}
