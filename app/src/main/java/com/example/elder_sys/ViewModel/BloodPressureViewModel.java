package com.example.elder_sys.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.elder_sys.Entity.BloodPressure;
import com.example.elder_sys.Entity.User;
import com.example.elder_sys.Repository.BloodPressureRepository;
import com.example.elder_sys.Repository.UserRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BloodPressureViewModel extends AndroidViewModel {
    private BloodPressureRepository bloodPressureRepository;


    public BloodPressureViewModel(@NonNull Application application) {
        super(application);
        bloodPressureRepository = new BloodPressureRepository(application);

    }
    public LiveData<List<BloodPressure>> getAllBloodHistory(String userName) {

        return bloodPressureRepository.getAllBloodHistory(userName);
    }

    public void insertBloodPressure(BloodPressure... bloodPressures) {
        bloodPressureRepository.insertUser(bloodPressures);
    }
//
//    public User selectByUserName(String username) throws ExecutionException, InterruptedException {
//        return userRepository.selectUserByName(username);
//    }
//    public User checkLogin(String username,String password) throws ExecutionException, InterruptedException {
//        return userRepository.checkLogin(username,password);
//    }

}
