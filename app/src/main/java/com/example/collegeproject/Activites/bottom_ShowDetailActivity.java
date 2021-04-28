package com.example.collegeproject.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.collegeproject.R;
import com.example.collegeproject.databinding.ActivityBottomShowDetailBinding;
import com.google.firebase.auth.FirebaseAuth;

public class bottom_ShowDetailActivity extends AppCompatActivity {

    ActivityBottomShowDetailBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomShowDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.logOut)
        {
            Log.d("Aryan","Log Out Clciked");
            mAuth.signOut();
            startActivity(new Intent(bottom_ShowDetailActivity.this,MainActivity.class));
            finishAffinity();
            return true;
        }
        else if(item.getItemId() == R.id.profile)
        {
            Log.d("Aryan","Profile is clicked");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}