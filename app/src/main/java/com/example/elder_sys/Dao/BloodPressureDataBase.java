package com.example.elder_sys.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.elder_sys.Entity.BloodPressure;
import com.example.elder_sys.Entity.User;

@Database(entities = {BloodPressure.class},version = 2,exportSchema = false)
public abstract class BloodPressureDataBase extends RoomDatabase {
    public abstract BloodPressureDao getBloodPressureDao();

    private static BloodPressureDataBase INSTANCE;
    public static synchronized BloodPressureDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BloodPressureDataBase.class,"bloodPressure_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }





}
