package com.pragyatitsolutions.SchoolManagement;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddBusDriverFragment extends Fragment {

    private TextInputLayout phone, license, password;
    Button register;

    public AddBusDriverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_bus_driver, container, false);

        phone = view.findViewById(R.id.DriverMobileNumber);
        password = view.findViewById(R.id.DriverPassword);
        license = view.findViewById(R.id.DriverLicenseNumber);
        register = view.findViewById(R.id.RegisterDriver);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progress = new ProgressDialog(getActivity());
                progress.setCancelable(false);
                progress.setMessage("Adding Data, Please Wait....");
                final String Phone = phone.getEditText().getText().toString();
                final String Password = password.getEditText().getText().toString();
                final String License = license.getEditText().getText().toString();


                if (!validatePhoneNumber(Phone) | !validatePassword(Password) | !validateLicenseNumber(License)) {
                    return;
                } else {
                    progress.show();
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DriversData");

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long childrens = dataSnapshot.getChildrenCount() + 1;

                            Driver d = new Driver("D-" + Phone + "-" + childrens, Phone, License, Password);
                            ref.child(Phone).setValue(d);
                            progress.dismiss();
                            Toast.makeText(getContext(), "Bus Driver Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), BusManagementActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            progress.dismiss();
                        }
                    });
                }
            }
        });

        return view;
    }


    private boolean validatePhoneNumber(String Phone) {
        if (Phone.isEmpty() || Phone.length() < 10) {
            phone.setError("Invalid Phone Number");
            return false;
        }
        return true;
    }

    private boolean validateLicenseNumber(String License) {
        if (License.isEmpty()) {
            license.setError("License Number is Mandatory");
            return false;
        }
        return true;
    }

    private boolean validatePassword(String Password) {
        if (Password.isEmpty() || Password.length() < 6) {
            password.setError("Password must be atleast 6 characters long");
            return false;
        }
        return true;
    }
}
