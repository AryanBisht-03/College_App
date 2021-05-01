package com.example.collegeproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collegeproject.Adapters.itemModel;
import com.example.collegeproject.Adapters.libraryAdapter;
import com.example.collegeproject.R;
import com.example.collegeproject.databinding.FragmentSportBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class sportFragment extends Fragment {


    public sportFragment() {
        // Required empty public constructor
    }

    FragmentSportBinding binding;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    libraryAdapter adapter;
    ArrayList<itemModel> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSportBinding.inflate(getLayoutInflater(),container,false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(getString(R.string.key_student)).child(mAuth.getUid()).child(getString(R.string.key_sport));

        items = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren())
                {
                    itemModel value = data.getValue(itemModel.class);
                    items.add(value);
                }
                checkSizeOfItems();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        adapter = new libraryAdapter(getContext(),items);

        binding.recyclerView2.setAdapter(adapter);
        return binding.getRoot();
    }

    private void checkSizeOfItems() {
        if(items.size()==0)
        {
            binding.textView3.setVisibility(View.VISIBLE);
            binding.recyclerView2.setVisibility(View.GONE);
        }
        else
        {
            binding.textView3.setVisibility(View.GONE);
            binding.recyclerView2.setVisibility(View.VISIBLE);
        }
    }
}