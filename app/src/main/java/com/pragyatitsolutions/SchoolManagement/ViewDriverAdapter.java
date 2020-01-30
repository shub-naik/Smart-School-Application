package com.pragyatitsolutions.SchoolManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewDriverAdapter extends RecyclerView.Adapter<ViewDriverAdapter.DriverItemHolder> {

    private List<Driver> list;
    private Context context;

    public ViewDriverAdapter(List<Driver> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewDriverAdapter.DriverItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewdriverrowlayout, parent, false);
        return new DriverItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDriverAdapter.DriverItemHolder holder, int position) {
        final Driver d = list.get(position);
        String name = d.getFullname();
        String password = d.getPassword();
        String license = d.getDriver_license();
        String phone = d.getMobile_number();
        holder.drivername.append(name);
        holder.driverpassword.append(password);
        holder.driverphone.append(phone);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DriverItemHolder extends RecyclerView.ViewHolder {

        TextView drivername, driverphone, driverlicense, driverpassword;

        public DriverItemHolder(@NonNull View itemView) {
            super(itemView);
            drivername = itemView.findViewById(R.id.ViewDriverName);
            driverphone = itemView.findViewById(R.id.ViewDriverPhone);
            driverlicense = itemView.findViewById(R.id.ViewDriverLicense);
            driverpassword = itemView.findViewById(R.id.ViewDriverPassword);

        }
    }
}
