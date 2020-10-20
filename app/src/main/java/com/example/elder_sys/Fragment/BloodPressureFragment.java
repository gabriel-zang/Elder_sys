package com.example.elder_sys.Fragment;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elder_sys.Entity.BloodPressure;
import com.example.elder_sys.R;
import com.example.elder_sys.ViewModel.BloodPressureViewModel;

import java.util.Date;
import java.util.Random;


public class BloodPressureFragment extends Fragment {

    private BloodPressureViewModel bloodPressureViewModel;
    private TextView highPressure,lowPressure,diagnosisInfo;
    private Button measure,history;
    private BloodPressure bloodPressure;

    String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_pressure, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bloodPressureViewModel = ViewModelProviders.of(this).get(BloodPressureViewModel.class);
        highPressure = requireActivity().findViewById(R.id.acturalHighBloodPressure);
        lowPressure  = requireActivity().findViewById(R.id.acturalLowBloodPressure);
        measure = requireActivity().findViewById(R.id.buttonMeasureBloodPressure);
        history = requireActivity().findViewById(R.id.bloodPressureHistory);
        diagnosisInfo = requireActivity().findViewById(R.id.diagnosisInfo);
        diagnosisInfo.setText("Please measure bloodPressure");
        username= getArguments().getString("username");

        measure.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                diagnosisInfo.setText("Waiting for measure results");
                measure.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        /**
                         * 延时执行的代码
                         */
                        Random r = new Random();
                        int highPre = r.nextInt(50) + 90;

                        int lowPre = highPre - (r.nextInt(30)+20);
                        highPressure.setText(String.valueOf(highPre));
                        lowPressure.setText(String.valueOf(lowPre));
                        if(highPre>120){
                            diagnosisInfo.setText("Your blood pressure is high. Please take care of your health！");
                        }
                        if(highPre<120){
                            diagnosisInfo.setText("Your blood pressure stays within a healthy range！");
                        }

                        bloodPressure = new BloodPressure();
                        bloodPressure.setUserName(username);
                        bloodPressure.setHighBloodPressure(String.valueOf(highPre));
                        bloodPressure.setLowBloodPressure(String.valueOf(lowPre));
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());

                        bloodPressure.setDatePre(formatter.format(date));
                        bloodPressureViewModel.insertBloodPressure(bloodPressure);
                        measure.setEnabled(true);
                    }
                },3000); // 延时1秒
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("username",username);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_bloodPressureFragment_to_bloodHistoryFragment,bundle);
            }
        });
    }
}

