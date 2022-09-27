package model;

import datamodel.Quota;

public class Passenger {
    private final String name, gender;
    private String preference;
    private final int age;
    private final Quota quota;

    public Passenger(String name, String gender, int age, Quota quota){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.quota = quota;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Quota getQuota() {
        return quota;
    }

    public String getPreference() {
        return preference;
    }

    public int getAge() {
        return age;
    }
}
