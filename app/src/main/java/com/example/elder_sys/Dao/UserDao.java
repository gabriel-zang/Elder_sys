package com.example.elder_sys.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.elder_sys.Entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertWords(User... users);

    @Update
    void updateWords(User... users);


//    @Query("SELECT * FROM User ORDER BY ID DESC")
//        //List<Word> getAllWords();
//    LiveData<List<User>> getAllWordsLive();

    @Query("SELECT * FROM User WHERE userName = :userName ")
    User selectByUserName(String userName);
    @Query("SELECT * FROM User WHERE userName = :userName  and passWord = :password")
    User checkLogin(String userName,String password);
}
