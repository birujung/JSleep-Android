package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model;

public class Account extends Serializable {
    public double balance;
    public String email;
    public String name;
    public String password;
    public Renter renter;

    public Account(int id) {
        super(id);
    }
}
