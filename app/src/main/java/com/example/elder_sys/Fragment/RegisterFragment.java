package com.example.elder_sys.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elder_sys.Entity.User;
import com.example.elder_sys.R;
import com.example.elder_sys.ViewModel.UserViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class RegisterFragment extends Fragment {
    private Button buttonSubmit;
    private TextView username, passoword, comfirmPassword;
    UserViewModel userViewModel;


    public RegisterFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        buttonSubmit = requireActivity().findViewById(R.id.buttonSubmit);
        username= requireActivity().findViewById(R.id.userNameRegister);
        passoword = requireActivity().findViewById(R.id.passwordRegister);
        comfirmPassword = requireActivity().findViewById(R.id.comfirmPassword);
        buttonSubmit.setEnabled(false);
        username.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(username,0);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String usernameStr = username.getText().toString().trim();
                String passowordStr = passoword.getText().toString().trim();
                String comfirmPasswordStr = comfirmPassword.getText().toString().trim();
                buttonSubmit.setEnabled(!usernameStr.isEmpty() && !passowordStr.isEmpty() && !comfirmPasswordStr.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        username.addTextChangedListener(textWatcher);
        passoword.addTextChangedListener(textWatcher);
        comfirmPassword.addTextChangedListener(textWatcher);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String passwordstr = passoword.getText().toString();
                String comfirmPasswordstr = passoword.getText().toString();
                User user = null;
                try {
                    user = userViewModel.selectByUserName(username.getText().toString());

                    if(user == null){
                        if(passwordstr.equals(comfirmPasswordstr)){
                            User newUser = new User();
                            newUser.setUserName(username.getText().toString());
                            newUser.setPassWord(passoword.getText().toString());
                            userViewModel.insertUser(newUser);
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                            NavController navController = Navigation.findNavController(v);
                            navController.navigate(R.id.action_registerFragment_to_mianFragment);
                        }else {
                            Toast.makeText(requireContext(), "密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(requireContext(), "此用户以注册请更换账户名", Toast.LENGTH_SHORT).show();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}