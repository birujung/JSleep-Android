package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model;

import java.util.*;

public class Room extends Serializable {
    public int size;
    public String name;
    public ArrayList<Facility> facility = new ArrayList<>();
    public Price price;
    public String address;
    public BedType bedType;
    public City city;
    public ArrayList<Date> booked;
    public int accountId;

    public Room(int id, int accountId, String name, int size, Price price, ArrayList<Facility> facility, City city, String address, BedType bedType) {
        super(id);
        this.accountId = accountId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility.addAll(facility);
        this.bedType = bedType;
        this.city = city;
        this.address = address;
        this.booked = new ArrayList<Date>();
    }

    public String toString() {
        return "\nID: " + id +
                "\nName: " + name +
                "\nAddress: " + address +
                "\nCity: " + city +
                "\nFacility: " + facility +
                "\nSize: " + size +
                "\n(" + price + ")" +
                "\nBed Type: " + bedType;
    }
}
