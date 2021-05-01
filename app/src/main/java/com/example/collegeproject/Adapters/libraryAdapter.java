package com.example.collegeproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeproject.R;
import com.example.collegeproject.databinding.LibraryRecyclerLayoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class libraryAdapter extends RecyclerView.Adapter<libraryAdapter.libraryViewModel> {

    Context context;
    ArrayList<itemModel> items;

    public libraryAdapter(Context context, ArrayList<itemModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public libraryViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.library_recycler_layout,parent,false);
        return new libraryViewModel(view);

    }

    @Override
    public void onBindViewHolder(@NonNull libraryViewModel holder, int position) {

        itemModel data = items.get(position);
        //Put Data here .....
        holder.binding.bookName.setText(data.getName());
        holder.binding.issueText.setText(data.getIssueDate());
        holder.binding.returnText.setText(data.getReturnDate());
        holder.binding.quantityText.setText(data.getQuantity());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class libraryViewModel extends RecyclerView.ViewHolder{

        LibraryRecyclerLayoutBinding binding;
        public libraryViewModel(@NonNull View itemView) {
            super(itemView);

            binding = LibraryRecyclerLayoutBinding.bind(itemView);

        }
    }
}
