package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model;

/**
 * The `Renter` class represents a person who rents a room.
 *
 * @author  Amrita Deviayu Tunjungbiru (2106636584)
 * @see Serializable
 */
public class Renter extends Serializable {
    public String phoneNumber;
    public String address;
    public String username;

    public Renter(int id) {
        super(id);
    }
}
