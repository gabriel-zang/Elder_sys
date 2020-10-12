package com.example.elder_sys.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.elder_sys.R;

public class mianFragment extends Fragment {


    String username="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mian, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button buttonBasicInfo = requireActivity().findViewById(R.id.buttonMainBasicInfo);
        Button buttonBloodPressure = requireActivity().findViewById(R.id.buttonMainBloodPressure);
        Button buttonHeartRate = requireActivity().findViewById(R.id.buttonMainHeartRate);
        Button buttonHealthTip = requireActivity().findViewById(R.id.buttonMainHealthTip);
        Button buttonEmergencyContact = requireActivity().findViewById(R.id.buttonMainEmergencyContact);


        buttonBasicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                username = getArguments().get("username").toString();
                bundle.putString("username",username);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_mianFragment_to_basicInfoFragment,bundle);
            }
        });
    }
}