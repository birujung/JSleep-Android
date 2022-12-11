package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.os.Bundle;

import okhttp3.ResponseBody;
import retrofit2.*;

public class AboutMeActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    EditText regisName, regisAddress, regisPhoneNumber;
    TextView name, email, balance, amount, logOut,
            detailRegisName, detailRegisAddress, detailRegisPhoneNumber;
    Button topUp, registerRenter, newRegisRenter, cancel;
    CardView cardDetail, cardRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_about_me);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        Account account = MainActivity.accounts;
        logOut = findViewById(R.id.logoutButton);

        //Account Details
        name = findViewById(R.id.detailNama);
        email = findViewById(R.id.detailEmail);
        balance = findViewById(R.id.detailBalance);
        amount = findViewById(R.id.amountTextBox);

        name.setText(MainActivity.accounts.name);
        email.setText(MainActivity.accounts.email);
        String balanceText = "Rp. " + String.valueOf(MainActivity.accounts.balance);
        balance.setText(balanceText);

        //Button
        topUp = findViewById(R.id.topUpButton);
        registerRenter = findViewById(R.id.registerRenterButton);
        newRegisRenter = findViewById(R.id.newRegisRenterButton);
        cancel = findViewById(R.id.cancelRegisRentButton);

        //Card View
        cardDetail = findViewById(R.id.cardViewDetail);
        cardRegis = findViewById(R.id.cardViewRegis);

        //Register Renter
        regisName = findViewById(R.id.regisRenterName);
        regisAddress = findViewById(R.id.regisRenterAddress);
        regisPhoneNumber = findViewById(R.id.regisRenterPhoneNum);

        detailRegisName = findViewById(R.id.detailRenterName);
        detailRegisAddress = findViewById(R.id.detailRenterAddress);
        detailRegisPhoneNumber = findViewById(R.id.detailRenterPhoneNum);

        topUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUp(MainActivity.accounts.id,Double.parseDouble(amount.getText().toString()));
            }
        });
        if(account.renter == null) {
            registerRenter.setVisibility(View.VISIBLE);
            cardDetail.setVisibility(View.GONE);
            cardRegis.setVisibility(View.GONE);

            registerRenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerRenter.setVisibility(View.GONE);
                    cardDetail.setVisibility(View.GONE);
                    cardRegis.setVisibility(View.VISIBLE);

                    newRegisRenter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MainActivity.renter = requestRegisterRenter(MainActivity.accounts.id, regisName.getText().toString(),
                                    regisAddress.getText().toString(), regisPhoneNumber.getText().toString());
                            Intent move = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                            startActivity(move);
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent move = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                            startActivity(move);
                            registerRenter.setVisibility(View.VISIBLE);
                            cardDetail.setVisibility(View.GONE);
                            cardRegis.setVisibility(View.GONE);

                            detailRegisName.setText("");
                            detailRegisAddress.setText("");
                            detailRegisPhoneNumber.setText("");
                        }
                    });
                }
            });
        } else {
            registerRenter.setVisibility(View.GONE);
            cardDetail.setVisibility(View.VISIBLE);
            cardRegis.setVisibility(View.GONE);

            detailRegisName.setText(MainActivity.accounts.renter.username);
            detailRegisAddress.setText(MainActivity.accounts.renter.address);
            detailRegisPhoneNumber.setText(MainActivity.accounts.renter.phoneNumber);
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(AboutMeActivity.this, HomeActivity.class);
                startActivity(move);
            }
        });
    }

    public Renter requestRegisterRenter(int id, String username, String address, String phoneNumber) {
        mApiService.registerRenter(id, username, address, phoneNumber).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if(response.isSuccessful()) {
                    MainActivity.accounts.renter = response.body();
                    System.out.println("ACCOUNT RENTER ADDED");
                    Intent move = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Register Renter Successful!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                System.out.println("ACCOUNT RENTER ALREADY REGISTERED");
                System.out.println(t.toString());
                Toast.makeText(mContext, "ACCOUNT RENTER ALREADY REGISTERED!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }

    protected Renter topUp(int id, double balance) {
        mApiService.topUp(id, balance).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    // Update the TextView with the new balance
                    MainActivity.accounts.balance = MainActivity.accounts.balance + balance;
                    System.out.println("BALANCE ADDED");
                    Toast.makeText(mContext, "Top Up Successful!", Toast.LENGTH_LONG).show();
                    recreate();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // Handle the failure
                System.out.println("BALANCE FAILED TO ADD");
                Toast.makeText(mContext, "Top Up Failed!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
}