package com.example.collegeproject.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.collegeproject.Fragments.fillDetialFragment;
import com.example.collegeproject.Fragments.profileFragment;
import com.example.collegeproject.R;
import com.example.collegeproject.databinding.ActivityEmptyFragmentBinding;

public class EmptyFragmentActivity extends AppCompatActivity {

    ActivityEmptyFragmentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmptyFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if(intent.hasExtra("name"))
        {
            profileFragment profileFragment = new profileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,profileFragment).commit();
        }
        else
        {
            fillDetialFragment detialFragment = new fillDetialFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,detialFragment).commit();
        }
    }
}