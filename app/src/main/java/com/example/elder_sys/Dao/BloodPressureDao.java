package com.example.elder_sys.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.elder_sys.Entity.BloodPressure;
import com.example.elder_sys.Entity.User;

import java.util.List;

@Dao
public interface BloodPressureDao {

    @Insert
    void insertBloodPressure(BloodPressure... bloodPressures);




    @Query("SELECT * FROM BloodPressure where userName = :userName ORDER BY ID DESC")
    LiveData<List<BloodPressure>> getAllBloodHistory(String userName);

//    @Query("SELECT * FROM User WHERE userName = :userName ")
//    User selectByUserName(String userName);
//    @Query("SELECT * FROM User WHERE userName = :userName  and passWord = :password")
//    User checkLogin(String userName, String password);
}
