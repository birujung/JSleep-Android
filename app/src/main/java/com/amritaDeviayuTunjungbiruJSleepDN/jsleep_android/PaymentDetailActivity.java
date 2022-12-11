package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.core.widget.ListViewAutoScrollHelper;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetailActivity extends AppCompatActivity {
    public static String startDate;
    public static String endDate;

    Button finalBook;
    TextView hotelName, hotelPrice, bookedDateFrom, bookedDateTo, cancelFinalBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_payment_detail);

        //Deklarasi Button dan TextView
        finalBook = findViewById(R.id.finalBookButton);
        cancelFinalBook= findViewById(R.id.cancelFinalBookButton);
        hotelName = findViewById(R.id.judulHotelInvoice);
        hotelPrice = findViewById(R.id.detailBookedPrice);
        bookedDateFrom = findViewById(R.id.detailBookedDateFrom);
        bookedDateTo = findViewById(R.id.detailBookedDateTo);

        finalBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotelName.setText(DetailRoomActivity.room.name);
                hotelPrice.setText(String.valueOf(DetailRoomActivity.room.price.price));
                bookedDateFrom.setText(PaymentDetailActivity.startDate);
                bookedDateTo.setText(PaymentDetailActivity.endDate);

                Intent move = new Intent(PaymentDetailActivity.this, MainActivity.class);
                startActivity(move);
            }
        });

        cancelFinalBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(PaymentDetailActivity.this, DetailRoomActivity.class);
                startActivity(move);
            }
        });
    }
}