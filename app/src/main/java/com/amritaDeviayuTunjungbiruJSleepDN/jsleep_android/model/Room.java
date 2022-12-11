package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model;

import java.util.*;

/**
 * This class represents a room.
 *
 * @author Amrita Deviayu Tunjungbiru (2106636584)
 */
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

    public Room(int id) {
        super(id);
    }
}
