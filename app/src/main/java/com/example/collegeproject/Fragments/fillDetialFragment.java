package com.example.collegeproject.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.collegeproject.Activites.bottom_ShowDetailActivity;
import com.example.collegeproject.R;
import com.example.collegeproject.databinding.FragmentFillDetialBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Objects;

public class fillDetialFragment extends Fragment {

    public fillDetialFragment() {
        // Required empty public constructor
    }

    FragmentFillDetialBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    private DatePickerDialog datePickerDialog;
    private boolean checkRadio = false;
    String genderText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFillDetialBinding.inflate(getLayoutInflater(),container,false);

        initDatePicker();
        binding.datePickerButton.setText(getTodaysDate());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(getString(R.string.key_student)).child(mAuth.getUid());

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("rollNum"))
        {
            binding.rollNumText.setText(intent.getStringExtra("rollNum"));
        }

        binding.nameTextDetial.setText(mAuth.getCurrentUser().getDisplayName());

        binding.datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton selectedButton = (RadioButton) binding.getRoot().findViewById(checkedId);
                checkRadio = true;
                genderText = selectedButton.getText().toString();

            }
        });

        Picasso.get().load(mAuth.getCurrentUser().getPhotoUrl()).noFade().placeholder(R.drawable.man).into(binding.profileImage);

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRadio && !binding.rollNumText.getText().toString().isEmpty() && !binding.batchText.getText().toString().isEmpty())
                {
                    reference.child(getString(R.string.key_gender)).setValue(genderText);
                    reference.child(getString(R.string.key_batch)).setValue(binding.batchText.getText().toString());
                    reference.child(getString(R.string.key_rollNum)).setValue(binding.rollNumText.getText().toString());
                    reference.child(getString(R.string.key_name)).setValue(mAuth.getCurrentUser().getDisplayName());
                    reference.child(getString(R.string.key_image)).setValue(mAuth.getCurrentUser().getPhotoUrl().toString());

                    startActivity(new Intent(getActivity(), bottom_ShowDetailActivity.class));
                    getActivity().finishAffinity();
                }
                else
                {
                    Toast.makeText(getActivity(), "Please fill all the detials Please buddy.:-)", Toast.LENGTH_SHORT).show();
                    if(binding.rollNumText.getText().toString().isEmpty())
                    {
                        binding.rollNumText.setError("Please Enter the Roll Number :-(");
                    }
                    if(binding.batchText.getText().toString().isEmpty())
                    {
                        binding.batchText.setError("Please Enter the semester :-(");
                    }
                }
            }
        });
        return binding.getRoot();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                binding.datePickerButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.BUTTON_NEUTRAL;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

}