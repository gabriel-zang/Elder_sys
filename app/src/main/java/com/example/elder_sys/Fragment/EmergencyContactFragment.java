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
import android.widget.TextView;
import android.widget.Toast;

import com.example.elder_sys.Entity.User;
import com.example.elder_sys.R;
import com.example.elder_sys.ViewModel.UserViewModel;

import java.util.concurrent.ExecutionException;

public class EmergencyContactFragment extends Fragment {
    UserViewModel userViewModel;
    TextView textViewAddEmergencyContact;
    Button buttonAddEmergencyContact;
    User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emergency_contact, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        String username = getArguments().getString("username");
        textViewAddEmergencyContact = requireActivity().findViewById(R.id.emergencyNumber);
        buttonAddEmergencyContact = requireActivity().findViewById(R.id.buttonAddEmergencyContact);
        try {
            user = userViewModel.selectByUserName(username);

            if(user.getEmergencyContact() != null){
                textViewAddEmergencyContact.setText(user.getEmergencyContact().toString());
            }

            buttonAddEmergencyContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(textViewAddEmergencyContact.getText().toString() !=null){
                        Integer phoneNumber = null;
                        if(textViewAddEmergencyContact.getText().toString()!=null){
                            phoneNumber = Integer.valueOf(textViewAddEmergencyContact.getText().toString());
                        }
                        user.setEmergencyContact(phoneNumber);
                        userViewModel.updateUser(user);
                        NavController navController = Navigation.findNavController(v);
                        navController.navigateUp();
                        Toast.makeText(requireContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(requireContext(), "请输入紧急联系人", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}