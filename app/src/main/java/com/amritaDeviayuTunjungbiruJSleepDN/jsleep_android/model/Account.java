package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model;

/**
 * The {@code Account} class represents an account for the JSleep Android app.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class Account extends Serializable {
    /**
     * The name of the user associated with the account.
     */
    public String name;

    /**
     * The email address of the user associated with the account.
     */
    public String email;

    /**
     * The password for the account.
     */
    public String password;

    /**
     * The balance of the account.
     */
    public double balance;

    /**
     * The renter associated with the account.
     */
    public Renter renter;

    public Account(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance= " + balance +
                ", email= '" + email + '\'' +
                ", name= '" + name + '\'' +
                ", password= '" + password + '\'' +
                ", renter= " + renter +
                '}';
    }
}
