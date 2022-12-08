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

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class MainActivity<v> extends AppCompatActivity {
    public static Account accounts;
    public static Renter renter;
    BaseApiService mApiService;
    Context mContext;
    Button next, prev, go;
    ListView listView;
    String name;

    static ArrayList<Room> roomList  = new ArrayList<Room>();
    List<Room> roomTemp;
    List<Room> roomAcc;
    List<String> nameStr;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Gson gson = new Gson();
        InputStream filepath = null;

        mApiService = UtilsApi.getApiService();
        mContext = this;

        next = findViewById(R.id.nextButton);
        prev = findViewById(R.id.prevButton);

        listView = findViewById(R.id.listViewMain);
        listView.setOnItemClickListener(this::onItemClick);

        System.out.println("Hello");
        roomAcc = getRoomList(10, 10);

        //Button Paginate
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roomTemp.size() > currentPage) {
                    currentPage = 1;
                    return;
                } currentPage++;

                try {
                    roomAcc = getRoomList(currentPage-1, 1);
                    Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage <= 1) {
                    currentPage = 1;
                    Toast.makeText(mContext, "This is Page 1", Toast.LENGTH_LONG).show();
                    return;
                } currentPage--;

                try {
                    roomAcc = getRoomList(currentPage-1, 1);
                    Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem register = menu.findItem(R.id.add_box_button);
        if(accounts.renter == null){
            register.setVisible(false);
        }
        else {
            register.setVisible(true);
        }
        return true;
    }

    protected List<Room> getRoomList(int page, int pageSize) {
        mApiService.getAllRoom(page, pageSize).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                roomTemp = response.body();
                System.out.println(roomTemp);
                nameStr = getName(roomTemp);
                System.out.println("Name extracted " + roomTemp.toString());

                ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, nameStr);
                listView = (ListView) findViewById(R.id.listViewMain);
                listView.setAdapter(itemAdapter);
                Toast.makeText(mContext, "Get Room Success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "Get Room Failed!", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    public static ArrayList<String> getName(List<Room> list) {
        ArrayList<String> ret = new ArrayList<String>();
        int i;
        for (i = 0; i < list.size(); i++) {
            ret.add(list.get(i).name);
        }
        return ret;
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("Hello User!\n", "You clicked Item: " + id + " at position: " + position);

        Intent intent = new Intent();
        intent.setClass(this, DetailRoomActivity.class);
        DetailRoomActivity.room = roomTemp.get(position);

        intent.putExtra("Position ", position);
        intent.putExtra("Id ", id);
        startActivity(intent);
    }
}

//        try {
//            filepath = getAssets().open("randomRoomList.json");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
//            Room[] acc = gson.fromJson(reader, Room[].class);
//            Collections.addAll(roomList, acc);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (Room room : roomList) {
//            name.add(room.name);
//        }
//        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
//        ListView view = (ListView) findViewById(R.id.listView);
//        view.setAdapter(roomAdapter);