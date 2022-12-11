package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.annotation.*;

import java.io.*;
import java.lang.*;
import java.lang.Integer.*;
import java.util.*;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import retrofit2.*;
import retrofit2.http.Query;

/**
 * The CreateRoomActivity class is an Android activity that represents all of the information to create a new room.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class CreateRoomActivity extends AppCompatActivity {
    /**
     * The {@link EditText} where the renter can enter the room's name, the room's price,
     * the room's size, and the room's address.
     */
    EditText roomName, roomPrice, roomSize, roomAddress;

    /**
     * The {@link Spinner} where the renter can choose between the provided selection.
     */
    Spinner bedSpin, citySpin;

    /**
     * Button for submitting a new room and cancel it.
     */
    Button submitRoom, cancel;

    /**
     * The {@link CheckBox} that the renter can choose from the room's facility- ac, refrigerator, bath tub,
     * balcony, restaurant, pool, and fitness center- is provided with the room.
     */
    CheckBox ac, refrig, wifi, bathtub, balcony, restaurant, pool, fitness;

    /**
     * Arraylist of facilities to store the facilities provided.
     */
    ArrayList<Facility> facility = new ArrayList<Facility>();

    /**
     * Declaration from enum of BedType to use in the spinner.
     */
    BedType bedType;

    /**
     * Declaration to use price.
     */
    Price price;

    /**
     * Declaration from enum of City to use in the spinner.
     */
    City city;

    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_create_room);

        mApiService = UtilsApi.getApiService();
        mContext = this;

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

        //Set Adapter for Spinner
        bedSpin.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
        citySpin.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));

        submitRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ac.isChecked()) {
                    facility.add(Facility.AC);}
                if (refrig.isChecked()) {
                    facility.add(Facility.Refrigerator);}
                if (wifi.isChecked()) {
                    facility.add(Facility.WiFi);}
                if (bathtub.isChecked()) {
                    facility.add(Facility.Bathtub);}
                if (balcony.isChecked()) {
                    facility.add(Facility.Balcony);}
                if (restaurant.isChecked()) {
                    facility.add(Facility.Restaurant);}
                if (pool.isChecked()) {
                    facility.add(Facility.SwimmingPool);}
                if (fitness.isChecked()) {
                    facility.add(Facility.FitnessCenter);}

                String bed = bedSpin.getSelectedItem().toString();
                String cityStr = citySpin.getSelectedItem().toString();
                bedType = BedType.valueOf(bed);
                city = City.valueOf(cityStr);

                int priceInt = Integer.parseInt(roomPrice.getText().toString());
                int sizeInt = Integer.parseInt(roomSize.getText().toString());

                submitRoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestRoom(MainActivity.accounts.id, roomName.getText().toString(), sizeInt, priceInt, facility, city, roomAddress.getText().toString(), bedType);
                        Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                        startActivity(move);
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                        startActivity(move);
                    }
                });
            }
        });
    }

    /**
     * This function is used to to request a new room.
     *
     * @param id  the id
     * @param name  the name of the room
     * @param size  the size of the room
     * @param price  the price of the room
     * @param facility the facilities provided with the room
     * @param city  the city the room located to
     * @param address   the address the room located to
     * @param bedType the bed type the room has
     * @return Room
     */
    protected Room requestRoom(int id, String name, int size, int price, ArrayList<Facility> facility, City city, String address, BedType bedType) {
        System.out.println("Room ada gasi");
        mApiService.createRoom(id, name, size, price, facility, city, address, bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    Room responseq = response.body();
                    System.out.println(responseq.toString());
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