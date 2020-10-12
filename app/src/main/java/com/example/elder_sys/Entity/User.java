package com.example.elder_sys.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userName")
    private String userName;
    @ColumnInfo(name = "passWord")
    private String passWord;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "age")
    private Integer age;


    @ColumnInfo(name = "sex")
    private Integer sex;

    @ColumnInfo(name = "emergencyContact")
    private Integer emergencyContact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(Integer emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}
