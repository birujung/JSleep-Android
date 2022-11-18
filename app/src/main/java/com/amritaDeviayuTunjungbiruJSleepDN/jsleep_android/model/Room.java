package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model;

import java.util.*;

public class Room extends Serializable {
    public int size;
    public String name;
    public Facility facility;
    public Price price;
    public String address;
    public BedType bedType;
    public City city;
    public ArrayList<Date> booked;
    public int accountId;

    public Room(int id) {
        super(id);
    }
}
