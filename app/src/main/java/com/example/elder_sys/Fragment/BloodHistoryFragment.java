package com.example.elder_sys.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.elder_sys.BloodPressureAdapter;
import com.example.elder_sys.Entity.BloodPressure;
import com.example.elder_sys.R;
import com.example.elder_sys.ViewModel.BloodPressureViewModel;

import java.util.List;


public class BloodHistoryFragment extends Fragment {

    private BloodPressureViewModel bloodPressureViewModel;
    String username;
    BloodPressureAdapter bloodPressureAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        username= getArguments().getString("username");
        bloodPressureViewModel = ViewModelProviders.of(this).get(BloodPressureViewModel.class);
        RecyclerView recyclerView = requireActivity().findViewById(R.id.bloodPressureList);
        bloodPressureAdapter = new BloodPressureAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(bloodPressureAdapter);
        bloodPressureViewModel.getAllBloodHistory(username).observe(this, new Observer<List<BloodPressure>>() {
            @Override
            public void onChanged(List<BloodPressure> bloodPressures) {
                if(bloodPressureAdapter.getItemCount() ==0){
                    Toast.makeText(requireContext(), "暂无数据请去测量", Toast.LENGTH_LONG).show();
                }else {
                    bloodPressureAdapter.setGetAllBloodPressireHistory(bloodPressures);
                    bloodPressureAdapter.notifyDataSetChanged();
                }


            }
        });
    }
}