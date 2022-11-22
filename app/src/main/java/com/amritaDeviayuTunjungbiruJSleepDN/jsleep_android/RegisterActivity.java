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
    EditText name, username, password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        Button register = findViewById(R.id.registerButton);
        name = findViewById(R.id.nameRegister);
        username = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Account account = requestRegister();
                Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(move);
            }
        });
    }

//    public Account requestRegister() {
//        mApiService.register(name.getText(), username.getText(), password.getText()).enqueue(new Callback<Account>() {
//            @Override
//            public void onResponse(Call<Account> call, Response<Account> response) {
//                if(response.isSuccessful()) {
//                    MainActivity.loginAccount = response.body();
//                    System.out.println("ACCOUNT IS TRUE");
//                    Intent move = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(move);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Account> call, Throwable t) {
//                System.out.println("ACCOUNT ISN'T TRUE");
//                System.out.println(t.toString());
//                Toast.makeText(mContext, "WRONG ACCOUNT!", Toast.LENGTH_LONG).show();
//            }
//        });
//        return null;
//    }
}