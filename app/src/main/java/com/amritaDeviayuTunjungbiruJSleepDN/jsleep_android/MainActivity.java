package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;

import java.io.*;
import java.lang.*;
import java.util.*;

import android.view.*;
import android.widget.*;
import android.content.*;
import android.os.Bundle;

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class MainActivity<v> extends AppCompatActivity {
    public static Account accounts;
    public static Renter renter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        InputStream filepath = null;
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Room> roomList  = new ArrayList<Room>();
        try {
            filepath = getAssets().open("randomRoomList.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
            Room[] acc = gson.fromJson(reader, Room[].class);
            Collections.addAll(roomList, acc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Room room : roomList) {
            name.add(room.name);
        }
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        ListView view = (ListView) findViewById(R.id.listView);
        view.setAdapter(roomAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.person_button) {
            Intent move = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(move);
            return true;
        }else if (item.getItemId() == R.id.add_box_button) {
            Intent move = new Intent(MainActivity.this, CreateRoomActivity.class);
            startActivity(move);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}