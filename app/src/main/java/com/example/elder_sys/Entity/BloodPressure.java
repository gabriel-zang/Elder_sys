package com.example.elder_sys.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class BloodPressure {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userName")
    private String userName;

    @ColumnInfo(name = "highBloodPressure")
    private String highBloodPressure;

    @ColumnInfo(name = "lowBloodPressure")
    private String lowBloodPressure;

    @ColumnInfo(name = "date")
    private String datePre;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHighBloodPressure() {
        return highBloodPressure;
    }

    public void setHighBloodPressure(String highBloodPressure) {
        this.highBloodPressure = highBloodPressure;
    }

    public String getLowBloodPressure() {
        return lowBloodPressure;
    }

    public void setLowBloodPressure(String lowBloodPressure) {
        this.lowBloodPressure = lowBloodPressure;
    }

    public String getDatePre() {
        return datePre;
    }

    public void setDatePre(String datePre) {
        this.datePre = datePre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
