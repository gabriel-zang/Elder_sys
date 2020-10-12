package com.example.elder_sys.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.elder_sys.Entity.User;
import com.example.elder_sys.Repository.UserRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel  extends AndroidViewModel {
    private UserRepository userRepository;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);

    }


    public void insertUser(User... users) {
        userRepository.insertUser(users);
    }
    public void updateUser(User... users) {
        userRepository.updateUser(users);
    }

    public User selectByUserName(String username) throws ExecutionException, InterruptedException {
        return userRepository.selectUserByName(username);
    }
    public User checkLogin(String username,String password) throws ExecutionException, InterruptedException {
        return userRepository.checkLogin(username,password);
    }

}
