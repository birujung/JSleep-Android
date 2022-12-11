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
 * The LoginActivity class is an Android activity that represents the login session to JSleep.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * The {@link EditText} where the user can enter their email and password.
     */
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_login);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        //Declaration
        TextView register = findViewById(R.id.registerLink);
        Button login = findViewById(R.id.login_button);
        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = requestLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }
        });

    }

    /**
     * This function is used to request login to the server
     *
     * @return Account object
     * @see Account
     */
    public Account requestLogin() {
        mApiService.login(username.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()) {
                    MainActivity.accounts = response.body();
                    System.out.println("ACCOUNT IS TRUE");
                    Intent move = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Login Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("ACCOUNT ISN'T TRUE");
                System.out.println(t.toString());
                Toast.makeText(mContext, "WRONG ACCOUNT!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
}
