package com.example.elder_sys.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.elder_sys.Entity.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    private static UserDataBase INSTANCE;
    public static synchronized UserDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),UserDataBase.class,"user_database")
                    //.fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract UserDao getWordDao();

}
