package com.example.collegeproject.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.collegeproject.R;
import com.example.collegeproject.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        Thread delay = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                        Thread.sleep(4000);
                }
                catch (Exception e)
                {
                    Log.d("Aryan","There is some error in SplashScreen "+ e.getMessage());
                }
                finally {
                    startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
        delay.start();

    }
}