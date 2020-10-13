package com.example.elder_sys.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elder_sys.Entity.User;
import com.example.elder_sys.R;
import com.example.elder_sys.ViewModel.UserViewModel;

import java.util.concurrent.ExecutionException;

public class basicInfoFragment extends Fragment {


    UserViewModel userViewModel;
    TextView textViewName,textViewAge;
    Button buttonEdit;
    RadioGroup radioGroup;
    RadioButton radioButtonMale,radioButtonFemale;
    User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        String username = getArguments().getString("username");

        buttonEdit = requireActivity().findViewById(R.id.basicInfoEdit);
        textViewName = requireActivity().findViewById(R.id.basicInfoName);
        textViewAge = requireActivity().findViewById(R.id.basicInfoAge);
        radioGroup=requireActivity().findViewById(R.id.basicRadioGroup);
        radioButtonMale = requireActivity().findViewById(R.id.radioButtonMale);
        radioButtonFemale = requireActivity().findViewById(R.id.radioButtonFemale);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = requireActivity().findViewById(radioButtonId);

            }
        });

        try {
            user = userViewModel.selectByUserName(username);
            if(user.getName() !=null){
                textViewName.setText(user.getName());


            }
            if(user.getAge() != null){
                textViewAge.setText(user.getAge().toString());
            }
            if(user.getSex() !=null){
                if(user.getSex() ==0){
                    radioButtonMale.setChecked(true);
                }
                if(user.getSex() ==1){
                    radioButtonFemale.setChecked(true);
                }
            }
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.setName(textViewName.getText().toString());
                    Integer age = null;
                    if(textViewAge.getText().toString()!=null){
                        age = Integer.valueOf(textViewAge.getText().toString());
                    }
                    user.setAge(age);
                    if(radioButtonMale.isChecked()){
                        user.setSex(0);
                    }
                    if(radioButtonFemale.isChecked()){
                        user.setSex(1);
                    }
                    userViewModel.updateUser(user);
                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                    Toast.makeText(requireContext(), "修改成功", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}