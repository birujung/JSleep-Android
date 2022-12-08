package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.annotation.*;

import java.io.*;
import java.lang.*;
import java.util.*;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import retrofit2.*;

public class CreateRoomActivity extends AppCompatActivity {
    EditText roomName, roomPrice, roomSize, roomAddress;
    Spinner bedSpin, citySpin;
    Button submitRoom, cancel;
    CheckBox ac, refrig, wifi, bathtub, balcony, restaurant, pool, fitness;
    ArrayList<Facility> facility = new ArrayList<Facility>();
    BedType bedType;
    Price price;
    City city;

    BaseApiService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_create_room);

        //Spinner Object
        bedSpin = (Spinner) findViewById(R.id.bedSpinner);
        citySpin = (Spinner) findViewById(R.id.citySpinner);

        //Button Object
        ac = findViewById(R.id.ac);
        refrig = findViewById(R.id.refrigerator);
        wifi = findViewById(R.id.wifi);
        bathtub = findViewById(R.id.bathtub);
        balcony = findViewById(R.id.balcony);
        restaurant = findViewById(R.id.restaurant);
        pool = findViewById(R.id.pool);
        fitness = findViewById(R.id.fitness);

        //EditText Object
        roomName = findViewById(R.id.addName);
        roomPrice = findViewById(R.id.addPrice);
        roomSize = findViewById(R.id.addSize);
        roomAddress = findViewById(R.id.addAddress);

        //Button Object
        submitRoom = findViewById(R.id.createRoomButton);
        cancel = findViewById(R.id.cancelCreateRoomButton);

        //Set Color Untuk CheckBox
        int red = Color.parseColor("#883639");
        ColorStateList colorStateList = ColorStateList.valueOf(red);

        bedSpin.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
        citySpin.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));

        submitRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ac.isChecked()) {
                    facility.add(Facility.AC);
                    ac.setButtonTintList(colorStateList);}
                if (refrig.isChecked()) {
                    facility.add(Facility.Refrigerator);
                    refrig.setButtonTintList(colorStateList);}
                if (wifi.isChecked()) {
                    facility.add(Facility.WiFi);
                    wifi.setButtonTintList(colorStateList);}
                if (bathtub.isChecked()) {
                    facility.add(Facility.Bathtub);
                    bathtub.setButtonTintList(colorStateList);}
                if (balcony.isChecked()) {
                    facility.add(Facility.Balcony);
                    balcony.setButtonTintList(colorStateList);}
                if (restaurant.isChecked()) {
                    facility.add(Facility.Restaurant);
                    restaurant.setButtonTintList(colorStateList);}
                if (pool.isChecked()) {
                    facility.add(Facility.SwimmingPool);
                    pool.setButtonTintList(colorStateList);}
                if (fitness.isChecked()) {
                    facility.add(Facility.FitnessCenter);
                    fitness.setButtonTintList(colorStateList);}

                String bed = bedSpin.getSelectedItem().toString();
                String cityStr = citySpin.getSelectedItem().toString();
                bedType = BedType.valueOf(bed);
                city = City.valueOf(cityStr);

                Integer priceObj = new Integer(roomPrice.getText().toString());
                Integer sizeObj = new Integer(roomSize.getText().toString());

                int priceInt = priceObj.parseInt(roomPrice.getText().toString());
                int sizeInt = sizeObj.parseInt(roomSize.getText().toString());

                Room room = requestRoom(MainActivity.accounts.id, roomName.getText().toString(), sizeInt, priceInt, facility, city, roomAddress.getText().toString(), bedType);
            }
        });
    }

    protected Room requestRoom(int id, String name, int size, int price, ArrayList<Facility> facility, City city, String address, BedType bedType) {
        mApiService.create(id, name, size, price, facility, city, address, bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                    startActivity(move);
                    System.out.println("Berhasil!");
                    Toast.makeText(mContext, "Room Added Successfully!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                System.out.println("Gagal!");
                Toast.makeText(mContext, "Room Failed to Add", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
}