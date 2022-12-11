package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.*;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.os.Bundle;

import retrofit2.*;

/**
 * This class represents an activity that displays details of an order made by a buyer.
 *
 * <p>The activity allows the renter to accept or cancel the order.</p>
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class OrderDetailActivity extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * The {@link TextView} that displays the buyer's ID , the start and end dates of the booking,
     * and the order's current status.
     */
    TextView buyerId, from, to, status;

    /**
     * The {@link Payment} instance representing the order.
     */
    Payment payment;

    /**
     * Button to accept or cancel an order.
     */
    Button accept, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        payment = RenterOrderListActivity.orderListData.get(RenterOrderListActivity.orderIndex);
        buyerId = findViewById(R.id.orderdetail_filltextviewidbuyer);
        from = findViewById(R.id.orderdetail_filltextviewfromdate);
        to = findViewById(R.id.orderdetail_filltextviewtodate);
        status = findViewById(R.id.orderdetail_filltextviewstatus);
        accept = findViewById(R.id.orderdetail_buttonaccept);
        cancel = findViewById(R.id.orderdetail_buttoncancel);

        buyerId.setText(String.valueOf(payment.buyerId));
        from.setText(payment.from.toString());
        to.setText(payment.to.toString());
        status.setText(payment.status.toString());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(OrderDetailActivity.this, RenterOrderListActivity.class);
                startActivity(move);
            }
        });

        if(payment.status.equals(Invoice.PaymentStatus.WAITING)){
            accept.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
        }
        else{
            accept.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptOrder(payment.id);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOrder(payment.id);
            }
        });
    }

    /**
     * This method is used to accept an order with a given ID.
     *
     * @param id The ID of the order to be accepted.
     * @return Boolean value indicating whether the order was successfully accepted.
     */
    protected Boolean acceptOrder(int id) {
        mApiService.accept(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if(response.body()){
                        Toast.makeText(mContext, "Accept Order Success", Toast.LENGTH_SHORT).show();
                        Intent move = new Intent(OrderDetailActivity.this,RenterOrderListActivity.class);
                        startActivity(move);
                    }else {
                        Toast.makeText(mContext, "Accept Order Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "Accept Order Failed", Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }

    /**
     * This method is used to cancel an order with a given ID.
     *
     * @param id The ID of the order to be cancelled.
     * @return Boolean value indicating whether the order was successfully cancelled.
     */
    protected Boolean cancelOrder(int id) {
        //System.out.println(pageSize);
        mApiService.cancel(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if(response.body()){
                        Toast.makeText(mContext, "Cancel Order Success", Toast.LENGTH_SHORT).show();
                        Intent move = new Intent(OrderDetailActivity.this,RenterOrderListActivity.class);
                        startActivity(move);
                    }else {
                        Toast.makeText(mContext, "Cancel Order Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "Cancel Order Failed", Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }
}