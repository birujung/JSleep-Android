package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import retrofit2.*;

/**
 * The RegisterActivity class is an Android activity that represents the register session to JSleep.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class RegisterActivity extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * The {@link EditText} where the user can enter their desired name, email and password to make a new account.
     */
    EditText name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_register);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        Button register = findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = requestRegister();
            }
        });
    }

    /**
     * This function is used to request register to the server
     *
     * @return Account object
     * @see Account
     */
    protected Account requestRegister() {
        mApiService.register(name.getText().toString(), email.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()) {
                    MainActivity.accounts = response.body();
                    System.out.println("ACCOUNT REGISTERED");
                    Intent go = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(go);
                    Toast.makeText(mContext, "Register Successful!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("ACCOUNT CAN'T BE REGISTERED");
                System.out.println(t.toString());
                Toast.makeText(mContext, "ACCOUNT ALREADY REGISTERED!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
}