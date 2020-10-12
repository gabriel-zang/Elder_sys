package com.example.elder_sys.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
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


public class LoginFragment extends Fragment {

    Button buttonRegister,buttonLogin;
    TextView userName,password;
    UserViewModel userViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonRegister = requireActivity().findViewById(R.id.ButtonRegister);
        buttonLogin = requireActivity().findViewById(R.id.buttonLogin);
        userName = requireActivity().findViewById(R.id.userName);
        password = requireActivity().findViewById(R.id.passwordLogin);
        buttonLogin.setEnabled(false);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameStr = userName.getText().toString();
                String passwordstr = password.getText().toString();
                try {
                    User user = userViewModel.checkLogin(userNameStr,passwordstr);
                    if(user != null){
                        Bundle bundle = new Bundle();
                        bundle.putString("username",user.getUserName());
                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.action_loginFragment_to_mianFragment,bundle);
                    }else {
                        Toast.makeText(requireContext(), "账号密码错误", Toast.LENGTH_SHORT).show();
                    }



                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String usernameStr = userName.getText().toString().trim();
                String passowordStr = password.getText().toString().trim();

                buttonLogin.setEnabled(!usernameStr.isEmpty() && !passowordStr.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        userName.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


}