package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import retrofit2.*;

public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText name, email, password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mApiService = UtilsApi.getApiService();
        mContext = this;


        name = findViewById(R.id.nameRegister);
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        Button register = findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = requestRegister();
            }
        });
    }

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