package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.annotation.*;

import java.io.*;
import java.lang.*;
import java.util.*;

import android.view.*;
import android.content.*;
import android.content.res.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;
import android.graphics.*;

import retrofit2.*;

public class DetailRoomActivity extends AppCompatActivity {
    public static Room room;

    TextView roomName, roomCity, roomPrice, roomSize, roomAddress, roomBedtype;
    CheckBox ac, refrig, wifi, bathtub, balcony, restaurant, pool, fitness;
    Button bookButton, cancelBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        room = MainActivity.listRoom.get(MainActivity.roomIndex);
        setContentView(R.layout.activity_detail_room);

        //Deklrasi
        roomName = findViewById(R.id.finalName);
        roomCity = findViewById(R.id.finalCity);
        roomPrice = findViewById(R.id.finalPrice);
        roomSize = findViewById(R.id.finalSize);
        roomAddress = findViewById(R.id.finalAddress);
        roomBedtype = findViewById(R.id.finalBedType);
        bookButton = findViewById(R.id.bookButton);
        cancelBookButton = findViewById(R.id.cancelBookButton);

        //Facility
        ac = findViewById(R.id.ac);
        refrig = findViewById(R.id.refrigerator);
        wifi = findViewById(R.id.wifi);
        bathtub = findViewById(R.id.bathtub);
        balcony = findViewById(R.id.balcony);
        restaurant = findViewById(R.id.restaurant);
        pool = findViewById(R.id.pool);
        fitness = findViewById(R.id.fitness);

        //Set Text Detail Room
        roomName.setText(room.name);
        roomCity.setText(String.valueOf(room.city));
        String finalPrice = "Rp. " + String.valueOf(room.price.price);
        roomPrice.setText(finalPrice);
        String finalSize = String.valueOf(room.size) + " m^2";
        roomSize.setText(finalSize);
        roomAddress.setText(room.address);
        String finalBed = room.bedType.toString() + " Bed";
        System.out.println(finalBed);
        roomBedtype.setText(finalBed);

        //Set Color Untuk CheckBox
        int red = Color.parseColor("#883639");
        ColorStateList colorStateList = ColorStateList.valueOf(red);

        for (int i = 0; i < room.facility.size(); i++) {
            if (room.facility.get(i).equals(Facility.AC )) {
                ac.setChecked(true);
                ac.setTextColor(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Refrigerator)) {
                refrig.setChecked(true);
                refrig.setTextColor(colorStateList);
            } else if (room.facility.get(i).equals(Facility.WiFi)) {
                wifi.setChecked(true);
                wifi.setTextColor(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Bathtub)) {
                bathtub.setChecked(true);
                bathtub.setTextColor(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Balcony)) {
                balcony.setChecked(true);
                balcony.setTextColor(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Restaurant)) {
                restaurant.setChecked(true);
                restaurant.setTextColor(colorStateList);
            } else if (room.facility.get(i).equals(Facility.SwimmingPool)) {
                pool.setChecked(true);
                pool.setTextColor(colorStateList);
            } else if (room.facility.get(i).equals(Facility.FitnessCenter)) {
                fitness.setChecked(true);
                fitness.setTextColor(colorStateList);
            }
        }

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(DetailRoomActivity.this,CreatePaymentActivity.class);
                startActivity(move);
            }
        });

        cancelBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(DetailRoomActivity.this,MainActivity.class);
                startActivity(move);
            }
        });
    }
}