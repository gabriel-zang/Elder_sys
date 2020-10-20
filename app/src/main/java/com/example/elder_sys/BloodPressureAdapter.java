package com.example.elder_sys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elder_sys.Entity.BloodPressure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BloodPressureAdapter extends RecyclerView.Adapter<BloodPressureAdapter.MyViewHolder> {

    List<BloodPressure> getAllBloodPressireHistory = new ArrayList<>();

    public void setGetAllBloodPressireHistory(List<BloodPressure> getAllBloodPressireHistory) {
        this.getAllBloodPressireHistory = getAllBloodPressireHistory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_cell,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BloodPressure bloodPressure = getAllBloodPressireHistory.get(position);
        holder.textViewBloodId.setText(String.valueOf(bloodPressure.getId()));
        holder.textViewhighPressure.setText(bloodPressure.getHighBloodPressure());
        holder.textViewLowPressure.setText(bloodPressure.getLowBloodPressure());
        holder.textViewDate.setText(bloodPressure.getDatePre());
    }

    @Override
    public int getItemCount() {
        return getAllBloodPressireHistory.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewBloodId,textViewhighPressure,textViewLowPressure,textViewDate;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBloodId = itemView.findViewById(R.id.textViewBloodId);
            textViewhighPressure = itemView.findViewById(R.id.textViewHighPressure);
            textViewLowPressure = itemView.findViewById(R.id.textViewLowPressure);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
}
