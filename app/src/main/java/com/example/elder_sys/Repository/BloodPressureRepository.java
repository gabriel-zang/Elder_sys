package com.example.elder_sys.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.elder_sys.Dao.BloodPressureDao;
import com.example.elder_sys.Dao.BloodPressureDataBase;
import com.example.elder_sys.Dao.UserDao;
import com.example.elder_sys.Dao.UserDataBase;
import com.example.elder_sys.Entity.BloodPressure;
import com.example.elder_sys.Entity.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BloodPressureRepository {

    private BloodPressureDao bloodPressureDao;


    public LiveData<List<BloodPressure>> getAllBloodHistory(String userName) {

        return bloodPressureDao.getAllBloodHistory(userName);
    }

    public BloodPressureRepository(Context context) {
        BloodPressureDataBase bloodPressureDataBase = BloodPressureDataBase.getDatabase(context.getApplicationContext());
        bloodPressureDao = bloodPressureDataBase.getBloodPressureDao();

    }


    public void insertUser(BloodPressure... bloodPressures) {
        new InsertAsyncTask(bloodPressureDao).execute(bloodPressures);
    }



    static class InsertAsyncTask extends AsyncTask<BloodPressure, Void, Void> {
        private BloodPressureDao bloodPressureDao;

        InsertAsyncTask(BloodPressureDao bloodPressureDao) {
            this.bloodPressureDao = bloodPressureDao;
        }


        @Override
        protected Void doInBackground(BloodPressure...bloodPressures) {
            bloodPressureDao.insertBloodPressure(bloodPressures);
            return null;
        }

    }

//
//
//
//    static class SelectByUsernameAsyncTask extends AsyncTask<String, Void, User> {
//        private UserDao userDao;
//        private User user;
//
//        public User getUser() {
//            return user;
//        }
//
//
//        SelectByUsernameAsyncTask(UserDao userDao) {
//            this.userDao = userDao;
//        }
//
//
//        @Override
//        protected User doInBackground(String... username) {
//            user =  userDao.selectByUserName(username[0]);
//            return user;
//        }
//
//    }
//
//    static class CheckLoginAsyncTask extends AsyncTask<String, Void, User> {
//        private UserDao userDao;
//        private User user;
//
//        public User getUser() {
//            return user;
//        }
//
//
//        CheckLoginAsyncTask(UserDao userDao) {
//            this.userDao = userDao;
//        }
//
//
//        @Override
//        protected User doInBackground(String... userInfo) {
//            user =  userDao.checkLogin(userInfo[0],userInfo[1]);
//            return user;
//        }
//
//    }
//
//    public User selectByUserName(String userName) {
//        return userDao.selectByUserName(userName);
//    }

}
