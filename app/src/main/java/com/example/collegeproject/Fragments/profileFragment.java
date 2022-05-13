package com.example.collegeproject.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
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
    FirebaseStorage storage = FirebaseStorage.getInstance();
    public static final int PICK_IMAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(getString(R.string.key_student)).child(mAuth.getUid());

        binding.nameText.setText(mAuth.getCurrentUser().getDisplayName());
        binding.emailText.setText(mAuth.getCurrentUser().getEmail());


        //Working on database
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentDetail details = snapshot.getValue(studentDetail.class);

                Picasso.get().load(details.getImage()).placeholder(R.drawable.man).into(binding.profileImage);
                binding.rollText.setText(details.getRollNum());
                String semester = details.getBatch()+"rd Semester";
                binding.batchText.setText(semester);
                binding.genderText.setText(details.getGender());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

        binding.changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            Uri image = data.getData();
            binding.profileImage.setImageURI(image);
            storage.getReference().child(mAuth.getUid()).putFile(image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        storage.getReference().child(mAuth.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String link = uri.toString();
                                database.getReference().child(getString(R.string.key_student)).child("image").setValue(link);
                            }
                        });
                    }
                    else
                        Toast.makeText(getContext(), "Not able to upload due to some error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}