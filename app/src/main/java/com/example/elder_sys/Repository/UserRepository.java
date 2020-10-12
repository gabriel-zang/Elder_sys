package com.example.elder_sys.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.elder_sys.Dao.UserDao;
import com.example.elder_sys.Dao.UserDataBase;
import com.example.elder_sys.Entity.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Context context) {
        UserDataBase wordDatabase = UserDataBase.getDatabase(context.getApplicationContext());
        userDao = wordDatabase.getWordDao();

    }


    public void insertUser(User... users) {
        new InsertAsyncTask(userDao).execute(users);
    }


    public User selectUserByName(String... username) throws ExecutionException, InterruptedException {
        return  new SelectByUsernameAsyncTask(userDao).execute(username).get();
    }
    public User checkLogin(String... userInfo) throws ExecutionException, InterruptedException {
        return  new CheckLoginAsyncTask(userDao).execute(userInfo).get();
    }

    static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {
            userDao.insertWords(users);
            return null;
        }

    }



    static class SelectByUsernameAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;
        private User user;

        public User getUser() {
            return user;
        }


        SelectByUsernameAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected User doInBackground(String... username) {
            user =  userDao.selectByUserName(username[0]);
            return user;
        }

    }

    static class CheckLoginAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;
        private User user;

        public User getUser() {
            return user;
        }


        CheckLoginAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected User doInBackground(String... userInfo) {
            user =  userDao.checkLogin(userInfo[0],userInfo[1]);
            return user;
        }

    }
//
//    public User selectByUserName(String userName) {
//        return userDao.selectByUserName(userName);
//    }

}
