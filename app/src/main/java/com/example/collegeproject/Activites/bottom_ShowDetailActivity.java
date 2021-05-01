package com.example.collegeproject.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.collegeproject.Adapters.BottomFragmentsAdapter;
import com.example.collegeproject.Fragments.libraryFragment;
import com.example.collegeproject.Fragments.profileFragment;
import com.example.collegeproject.Fragments.sportFragment;
import com.example.collegeproject.R;
import com.example.collegeproject.databinding.ActivityBottomShowDetailBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class bottom_ShowDetailActivity extends AppCompatActivity {

    ActivityBottomShowDetailBinding binding;
    FirebaseAuth mAuth;
    MenuItem prevMenuItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomShowDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        BottomFragmentsAdapter navigationAdapter = new BottomFragmentsAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(navigationAdapter);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.libraryItem:
                        binding.viewPager.setCurrentItem(0);
                        break;
                    case R.id.Sports:
                        binding.viewPager.setCurrentItem(1);
                        break;
                    case R.id.profile_bottom:
                        binding.viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:

                        binding.bottomNavigationView.getMenu().findItem(R.id.libraryItem).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigationView.getMenu().findItem(R.id.Sports).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigationView.getMenu().findItem(R.id.profile_bottom).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    binding.bottomNavigationView.getMenu().getItem(0).setChecked(false);

                binding.bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = binding.bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

            GoogleSignInOptions gso = new GoogleSignInOptions.
                    Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                    build();

            GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(bottom_ShowDetailActivity.this,gso);
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        FirebaseAuth.getInstance().signOut(); // very important if you are using firebase.
                        startActivity(new Intent(bottom_ShowDetailActivity.this, MainActivity.class));
                        finishAffinity();
                    }
                }
            });

            return true;
        }
        else if(item.getItemId() == R.id.profile)
        {

            Intent intent = new Intent(this,EmptyFragmentActivity.class);
            intent.putExtra("name","profile");
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

}