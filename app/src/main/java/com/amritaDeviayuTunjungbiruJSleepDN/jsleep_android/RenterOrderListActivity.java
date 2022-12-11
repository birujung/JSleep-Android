package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.*;
import java.util.*;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import retrofit2.*;

/**
 * The RenterOrderListAcitivity class is an Android activity that displays a list of orders for a given renter.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class RenterOrderListActivity extends AppCompatActivity {
    /**
     * The `ListView` used to display the list of orders..
     */
    ListView orderList;

    /**
     * The index of the selected order.
     */
    public static int orderIndex;

    /**
     * An array to store a payment list.
     */
    public static ArrayList<Payment> orderListData;

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
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_order_list);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        orderList = findViewById(R.id.seeorder_ListView);
        orderList.setOnItemClickListener(this::onItemClick);
        getOrderForRenter(MainActivity.accounts.id);
    }

    /**
     * Retrieves the orders for a given renter ID.
     *
     * @param renterId the ID of the renter
     */
    protected void getOrderForRenter(int renterId){
        mApiService.getOrderForRenter(renterId,0,10).enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                if(response.isSuccessful()){
                    List<Payment> orderlist = response.body();
                    assert orderlist != null;
                    orderListData = new ArrayList<Payment>(orderlist);
                    Toast.makeText(mContext, "Get Order Success", Toast.LENGTH_SHORT).show();
                    OrderListAdapter adapter = new OrderListAdapter(mContext,orderListData);
                    orderList.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Toast.makeText(mContext, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onItemClick (AdapterView<?> l, View v, int position, long id){
        System.out.println("onItemClick Success");
        Log.i("ListView", "You clicked Item np : " + id + " at position:" + position);
        Intent intent = new Intent();
        orderIndex = position;
        System.out.println("clicked");
        intent.setClass(mContext, OrderDetailActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}