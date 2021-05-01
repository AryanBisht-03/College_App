package com.example.collegeproject.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.collegeproject.Activites.MainActivity;
import com.example.collegeproject.Adapters.studentDetail;
import com.example.collegeproject.R;
import com.example.collegeproject.databinding.FragmentProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class profileFragment extends Fragment {

    public profileFragment() {
        // Required empty public constructor
    }

    FragmentProfileBinding binding;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(getString(R.string.key_student)).child(mAuth.getUid());

        binding.nameText.setText(mAuth.getCurrentUser().getDisplayName());
        Picasso.get().load(mAuth.getCurrentUser().getPhotoUrl()).placeholder(R.drawable.ic_football_profile).into(binding.profileImage);
        binding.emailText.setText(mAuth.getCurrentUser().getEmail());


        //Working on database
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentDetail details = snapshot.getValue(studentDetail.class);

                binding.rollText.setText(details.getRollNum());
                String semester = details.getBatch()+"rd Semester";
                binding.batchText.setText(semester);
                binding.genderText.setText(details.getGender());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,Object> map = new HashMap<>();

                map.put("name","Football 2");
                map.put("quantity","2");
                map.put("issueDate","05/12/2021");
                map.put("returnDate","06/01/2021");

                reference.child(getString(R.string.key_sport)).push().updateChildren(map);
                //Set new Image in the photo section.
            }
        });

        binding.logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.
                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                        build();

                GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(getActivity(),gso);
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            FirebaseAuth.getInstance().signOut(); // very important if you are using firebase.
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finishAffinity();
                        }
                    }
                });
            }
        });
        return binding.getRoot();
    }
}