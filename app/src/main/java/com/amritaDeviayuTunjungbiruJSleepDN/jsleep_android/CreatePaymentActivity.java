package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.core.widget.ListViewAutoScrollHelper;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.text.*;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePaymentActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;

    Payment payment;
    Button createPayment, cancelPayment;
    EditText createFrom, createTo;
    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_create_payment);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = dateFormat.parse(PaymentDetailActivity.endDate);
            Date startDate = dateFormat.parse(PaymentDetailActivity.startDate);
            long diff = endDate.getTime() - startDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            //Deklarasi Button
            createPayment = findViewById(R.id.create_payment_button);
            cancelPayment = findViewById(R.id.cancel_create_payment_button);

            //Deklarasi EditText dan TextView
            createFrom = findViewById(R.id.input_from);
            createTo = findViewById(R.id.input_to);
            totalPrice = findViewById(R.id.totalPrice);

            //Set Text untuk Booking
            totalPrice.setText(String.valueOf(((double)diffDays) * DetailRoomActivity.room.price.price));

            createPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Success!");
                    Payment payment = createPayment(MainActivity.accounts.id, DetailRoomActivity.room.accountId, DetailRoomActivity.room.id, PaymentDetailActivity.startDate, PaymentDetailActivity.endDate);
                    Intent move = new Intent(CreatePaymentActivity.this, PaymentDetailActivity.class);
                    startActivity(move);
                }
            });

            cancelPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Failed!");
                    Intent move = new Intent(CreatePaymentActivity.this, MainActivity.class);
                    startActivity(move);
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected Payment createPayment(int buyerId, int renterId, int roomId, String from, String to){
        System.out.println("Callback");
        //Print all parameter
        System.out.println(buyerId);
        System.out.println(renterId);
        System.out.println(roomId);
        System.out.println(from);
        System.out.println(to);

        mApiService.createPayment(buyerId, renterId, roomId, from, to).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(@NonNull Call<Payment> call, @NonNull Response<Payment> response) {
                if(response.isSuccessful()){
                    System.out.println("Success to Pay");
                    payment = response.body();
                    System.out.println(payment);
                    Intent move = new Intent(CreatePaymentActivity.this, MainActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Payment created", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Payment> call, @NonNull Throwable t) {
                System.out.println("Failed to Pay");
                Toast.makeText(mContext, "Create Payment Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}