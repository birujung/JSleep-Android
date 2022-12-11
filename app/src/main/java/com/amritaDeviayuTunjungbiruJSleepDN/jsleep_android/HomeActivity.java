package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * The HomeActivity class is an Android activity that represents the home screen of the JSleep app.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class HomeActivity extends AppCompatActivity {
    /**
     * Button for login or register.
     */
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        setContentView(R.layout.activity_home);

        //Animated Backgroung
        ConstraintLayout layout = findViewById(R.id.layout_Home);

        AnimationDrawable animationDrawable = (AnimationDrawable)
                layout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        //Declaration
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
