package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import retrofit2.*;

/**
 * The MainActivity class is an Android activity that represents the main page for the app.
 *
 * <p>It displays a list of rooms that are available for rent, and allows the user to navigate to different pages of the list
 * or to view the details of a selected room. It also provides options for viewing the user's profile and creating a new room.</p>
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class MainActivity<v> extends AppCompatActivity {
    /**
     * The user's account information.
     */
    public static Account accounts;

    /**
     * The user's renter information.
     */
    public static Renter renter;

    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * Button to navigate to next or previous page.
     */
    Button next, prev;

    /**
     * The `ListView` used to display the list of rooms.
     */
    ListView listView;

    /**
     * The list of rooms to be displayed.
     */
    List<Room> activitylist;

    /**
     * An array to store a room list.
     */
    public static ArrayList<Room> listRoom;

    /**
     * The index of the selected room.
     */
    public static int roomIndex;

    /**
     * The current page of rooms being displayed.
     */
    int current;

    /**
     * The number of the current page being displayed.
     */
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        current = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream filepath = null;

        mApiService = UtilsApi.getApiService();
        mContext = this;
        activitylist = getRoomList(current);

        // Button Declaration
        next = findViewById(R.id.nextButton);
        prev = findViewById(R.id.prevButton);

        listView = findViewById(R.id.listViewMain);
        listView.setOnItemClickListener(this::onItemClick);

        //Button Paginate
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current += 1;
                activitylist = getRoomList(current);
                Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_SHORT).show();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current == 0) {
                    Toast.makeText(mContext, "You are at the first page", Toast.LENGTH_SHORT).show();
                } else {
                    current -= 1;
                    activitylist = getRoomList(current);
                    Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_SHORT).show();
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
        } else if (item.getItemId() == R.id.add_box_button) {
            Intent move = new Intent(MainActivity.this, CreateRoomActivity.class);
            startActivity(move);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem register = menu.findItem(R.id.add_box_button);
        if (accounts.renter == null) {
            register.setVisible(false);
        } else {
            register.setVisible(true);
        }
        return true;
    }

    /**
     * This function is used to get all available rooms.
     *
     * @param page amount of page wants to display
     * @return Renter
     */
    protected List<Room> getRoomList(int page) {
        mApiService.getAllRoom(page, 5).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                activitylist = response.body();
                assert activitylist != null;
                listRoom = new ArrayList<Room>(activitylist);
                CustomListAdapter adapter = new CustomListAdapter(mContext, listRoom);
                listView.setAdapter(adapter);

                System.out.println("Berhasil Get Room");
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

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        System.out.println("onItemClick Success");
        Log.i("Hello User!\n", "You clicked Item: " + id + " at position: " + position);

        Intent intent = new Intent(this, DetailRoomActivity.class);
        roomIndex = position;
        System.out.println("Clicked");
        intent.setClass(mContext, DetailRoomActivity.class);
        intent.putExtra("Position ", position);
        intent.putExtra("Id ", id);
        startActivity(intent);
    }
}
