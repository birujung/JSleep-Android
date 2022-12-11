package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model;

/**
 * A class that represents an invoice for a rental transaction.
 *
 * <p>This class extends the `Serializable` class and adds fields for the IDs of the buyer and renter, the payment status, and the rating of the rental.
 * It also provides a `print()` method that returns a string representation of the invoice.</p>
 *
 * @author Amrita Deviayu Tunjungbiru (2106636584)
 * @see Serializable
 */
public class Invoice extends Serializable {
    public int buyerId;
    public int renterId;
    public PaymentStatus status;
    public RoomRating rating;

    public Invoice(int id) {
        super(id);
    }

    public enum RoomRating{
        NONE,BAD,NEUTRAL,GOOD
    }

    public enum PaymentStatus{
        FAILED,WAITING,SUCCESS
    }
}
