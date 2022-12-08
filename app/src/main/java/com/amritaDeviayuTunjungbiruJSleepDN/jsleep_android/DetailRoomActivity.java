package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.annotation.*;

import java.io.*;
import java.lang.*;
import java.util.*;

import android.view.*;
import android.content.res.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;
import android.graphics.*;

import retrofit2.*;

public class DetailRoomActivity extends AppCompatActivity {
    public static Room room;

    TextView roomName, roomPrice, roomSize, roomAddress, roomBedtype;
    CheckBox ac, refrig, wifi, bathtub, balcony, restaurant, pool, fitness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_detail_room);

        //Detail Room
        roomName = findViewById(R.id.finalName);
        roomPrice = findViewById(R.id.finalPrice);
        roomSize = findViewById(R.id.finalSize);
        roomAddress = findViewById(R.id.finalAddress);
        roomBedtype = findViewById(R.id.finalBedType);

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
        roomPrice.setText(String.valueOf(room.price.price));
        roomSize.setText(String.valueOf(room.size));
        roomAddress.setText(room.address);
        roomBedtype.setText(room.bedType.toString());

        //Set Color Untuk CheckBox
        int red = Color.parseColor("#883639");
        ColorStateList colorStateList = ColorStateList.valueOf(red);

        for (int i = 0; i < room.facility.size(); i++) {
            if (room.facility.get(i).equals(Facility.AC )) {
                ac.setChecked(true);
                ac.setButtonTintList(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Refrigerator)) {
                refrig.setChecked(true);
                refrig.setButtonTintList(colorStateList);
            } else if (room.facility.get(i).equals(Facility.WiFi)) {
                wifi.setChecked(true);
                wifi.setButtonTintList(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Bathtub)) {
                bathtub.setChecked(true);
                bathtub.setButtonTintList(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Balcony)) {
                balcony.setChecked(true);
                balcony.setButtonTintList(colorStateList);
            } else if (room.facility.get(i).equals(Facility.Restaurant)) {
                restaurant.setChecked(true);
                restaurant.setButtonTintList(colorStateList);
            } else if (room.facility.get(i).equals(Facility.SwimmingPool)) {
                pool.setChecked(true);
                pool.setButtonTintList(colorStateList);
            } else if (room.facility.get(i).equals(Facility.FitnessCenter)) {
                fitness.setChecked(true);
                fitness.setButtonTintList(colorStateList);
            }
        }
    }
}