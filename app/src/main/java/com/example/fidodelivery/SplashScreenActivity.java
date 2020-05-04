package com.example.fidodelivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fidodelivery.login_details.LoginActivity;
import com.example.fidodelivery.usermianscreen.UserMainActivity;

public class SplashScreenActivity extends AppCompatActivity {
    TextView tv_food, tv_court;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        tv_court = findViewById(R.id.tv_court_splash);
        tv_food = findViewById(R.id.tv_food_splash);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splashanimation);

        tv_food.startAnimation(animation);

        tv_court.startAnimation(animation);
        SharedPreferences sharedPreferences = this.getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
        final boolean isAutoLogin = sharedPreferences.getBoolean("isAutoLogin", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAutoLogin) {
                    startActivity(new Intent(SplashScreenActivity.this, UserMainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 4000);

    }
}
