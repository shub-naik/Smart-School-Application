package com.pragyatitsolutions.SchoolManagement;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDriverFragment extends Fragment {

    RecyclerView viewDriverRecyclerView;
    List<Driver> list;
    DatabaseReference ref;
    Spinner classes, section;
    ViewDriverAdapter adapter;

    public ViewDriverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ref = FirebaseDatabase.getInstance().getReference("DriversData");

        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading Data, Please Wait....");
        progress.show();

        View view = inflater.inflate(R.layout.fragment_view_driver, container, false);
        list = new ArrayList<>();
        final TextView viewDriverTextView = view.findViewById(R.id.ViewDriverTextView);
        viewDriverRecyclerView = view.findViewById(R.id.ViewDriverRecyclerView);
        viewDriverRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d1 : dataSnapshot.getChildren()) {
                        Driver d = d1.getValue(Driver.class);
                        list.add(d);
                    }
                    viewDriverTextView.setVisibility(View.VISIBLE);
                    adapter = new ViewDriverAdapter(list, getContext());
                    viewDriverRecyclerView.setAdapter(adapter);
                    progress.dismiss();
                } else {
                    Toast.makeText(getContext(), "DataBase Empty, So Please Add Data To View", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "DataBase Error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
