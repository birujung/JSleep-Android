package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.os.Bundle;

import retrofit2.*;

public class AboutMeActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    EditText name, email, balance, amount,
            id, regisName, regisAddress, regisPhoneNumber,
            detailRegisName, detailRegisAddress, detailRegisPhoneNumber;
    Button topUp, registerRenter, newRegisRenter, cancel;
    CardView cardView1, cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        Account account = MainActivity.accounts;

        //Account Details
        name = findViewById(R.id.detailNama);
        email = findViewById(R.id.detailEmail);
        balance = findViewById(R.id.detailBalance);
        amount = findViewById(R.id.amountTextBox);

        name.setText(MainActivity.accounts.name);
        email.setText(MainActivity.accounts.email);
        balance.setText(String.valueOf(MainActivity.accounts.balance));

        //Button
        topUp = findViewById(R.id.topUpButton);
        registerRenter = findViewById(R.id.registerRenterButton);
        newRegisRenter = findViewById(R.id.newRegisRenterButton);
        cancel = findViewById(R.id.cancelRegisRentButton);

        //Card View
        cardView1 = findViewById(R.id.card_view1);
        cardView2 = findViewById(R.id.card_view2);

        //Register Renter
        regisName = findViewById(R.id.regisRenterName);
        regisAddress = findViewById(R.id.regisRenterAddress);
        regisPhoneNumber = findViewById(R.id.regisRenterPhoneNum);

        detailRegisName = findViewById(R.id.detailRenterName);
        detailRegisAddress = findViewById(R.id.detailRenterAddress);
        detailRegisPhoneNumber = findViewById(R.id.detailRenterPhoneNum);

        if(account.renter == null) {
            registerRenter.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.GONE);
        } else {
            registerRenter.setVisibility(View.GONE);
            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
        }

        registerRenter.setOnClickListener(v -> {
            registerRenter.setVisibility(View.GONE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
            Account acc = requestRegisterRenter();
        });

//        newRegisRenter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent move = new Intent(AboutMeActivity.this, LoginActivity.class);
//                startActivity(move);
//            }
//        });
        newRegisRenter.setOnClickListener(v -> {
            Account acc = requestRegisterRenter();
//            registerRenter.setVisibility(View.GONE);
//            cardView1.setVisibility(View.VISIBLE);
//            cardView2.setVisibility(View.GONE);
//
//            detailRegisName.setText(MainActivity.accounts.renter.username);
//            detailRegisAddress.setText(MainActivity.accounts.renter.address);
//            detailRegisPhoneNumber.setText(MainActivity.accounts.renter.phoneNumber);
        });

        cancel.setOnClickListener(v -> {
            registerRenter.setVisibility(View.GONE);
            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
            detailRegisName.setText("");
            detailRegisAddress.setText("");
            detailRegisPhoneNumber.setText("");
        });
    }

    public Account requestRegisterRenter() {
        mApiService.registerRenter(Integer.parseInt(id.toString()), regisName.getText().toString(), regisAddress.getText().toString(), regisPhoneNumber.getText().toString()).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if(response.isSuccessful()) {
                    MainActivity.renter = response.body();
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
}