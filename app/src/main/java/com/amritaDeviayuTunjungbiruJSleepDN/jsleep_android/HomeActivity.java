package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.*;
import android.view.View;
import android.widget.*;

public class HomeActivity extends AppCompatActivity {
    Button login, register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_home);

        ConstraintLayout layout = findViewById(R.id.layout_Home);

        AnimationDrawable animationDrawable = (AnimationDrawable)
                layout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        login = findViewById(R.id.loginButtonHome);
        register = findViewById(R.id.registerButtonHome);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(move);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(move);
            }
        });
    }
}