package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;

import java.text.*;
import java.lang.*;
import java.util.*;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.os.Bundle;

import retrofit2.*;

/**
 * The PaymentDetailActivity class is an Android activity that represents the payment session in the app.
 *
 * <p> It displays details of the payment, including the dates of the payment, the name and address of the room being rented,
 * the price of the room, and the total price for the payment period. It also provides a button for creating the payment.</p>
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class PaymentDetailActivity extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * The payment to be displayed.
     */
    Payment payment;

    /**
     * Button to finalize the payment.
     */
    Button createButton;

    /**
     * The {@link TextView} that displays the button for going back to the payment invoice page,
     * the start and end dates of the payment period, the name and the address of the room being rented,
     *  the price for the room per day, and the total price of the booking.
     */
    TextView backPaymentInvoice,
            cpFrom, cpTo, cpName, cpAddress,
            cpTotalPrice, cpPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        mApiService = UtilsApi.getApiService();
        mContext = this;

        backPaymentInvoice = findViewById(R.id.cancelFinalBookButton);
        backPaymentInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(PaymentDetailActivity.this, CreatePaymentActivity.class);
                startActivity(move);
            }
        });

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = dateFormat.parse(CreatePaymentActivity.enddate);
            Date startDate = dateFormat.parse(CreatePaymentActivity.startdate);
            long diff = endDate.getTime() - startDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            cpName = findViewById(R.id.createpayment_title_name);
            cpAddress = findViewById(R.id.createpayment_title_address);
            cpName.setText(DetailRoomActivity.room.name);
            cpAddress.setText(DetailRoomActivity.room.address);
            cpPrice = findViewById(R.id.piPrice);
            cpTotalPrice = findViewById(R.id.createpayment_price);

            createButton = findViewById(R.id.createpayment_button);
            cpFrom = findViewById(R.id.createpayment_from);
            cpFrom.setText(CreatePaymentActivity.startdate);
            cpTo = findViewById(R.id.createpayment_to);
            cpTo.setText(CreatePaymentActivity.enddate);
            cpPrice.setText(String.valueOf(DetailRoomActivity.room.price.price));
            cpTotalPrice.setText(String.valueOf(((double)diffDays) * DetailRoomActivity.room.price.price));



            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked");
                    createPayment(MainActivity.accounts.id,
                            DetailRoomActivity.room.accountId,
                            DetailRoomActivity.room.id,
                            CreatePaymentActivity.startdate,
                            CreatePaymentActivity.enddate);
                }
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a payment for booking a room.
     *
     * @param buyerId the ID of the buyer
     * @param renterId the ID of the renter
     * @param roomId the ID of the room being rented
     * @param from the start date of the rental period
     * @param to the end date of the rental period
     * @return the created Payment object, or null if the payment failed to be created
     */
    protected Payment createPayment(int buyerId,
                                    int renterId,
                                    int roomId,
                                    String from,
                                    String to){
        System.out.println("Callback");
        //print all parameter
        System.out.println(buyerId);
        System.out.println(renterId);
        System.out.println(roomId);
        System.out.println(from);
        System.out.println(to);

        mApiService.createPayment(buyerId, renterId, roomId, from, to).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(@NonNull Call<Payment> call, @NonNull Response<Payment> response) {
                if(response.isSuccessful()){
                    System.out.println("Success");
                    payment = response.body();
                    System.out.println(payment);
                    Intent move = new Intent(PaymentDetailActivity.this, MainActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Payment created", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Payment> call, @NonNull Throwable t) {
                Toast.makeText(mContext, "Create Payment Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}