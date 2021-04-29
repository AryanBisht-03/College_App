package com.example.collegeproject.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.collegeproject.Fragments.libraryFragment;
import com.example.collegeproject.Fragments.profileFragment;
import com.example.collegeproject.Fragments.sportFragment;

public class BottomFragmentsAdapter extends FragmentStatePagerAdapter {

    public BottomFragmentsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    public BottomFragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new libraryFragment();

            case 1:
                return new sportFragment();
            case 2:
                return new profileFragment();
            default:
                return new libraryFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;       //Number of navigation at the Bottom
    }
}
