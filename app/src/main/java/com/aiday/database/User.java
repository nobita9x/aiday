package com.aiday.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "fbid")
    private String fbid;

    @ColumnInfo(name = "fbName")
    private String fbName;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "fbLocation")
    private String fbLocation;

    @ColumnInfo(name = "birthDay")
    private String birthDay;

    @ColumnInfo(name = "relationship")
    private String relationship;

    @ColumnInfo(name = "mobile")
    private String mobile;

    @ColumnInfo(name = "email")
    private String email;
    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getFbName() {
        return fbName;
    }

    public void setFbName(String fbName) {
        this.fbName = fbName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFbLocation() {
        return fbLocation;
    }

    public void setFbLocation(String fbLocation) {
        this.fbLocation = fbLocation;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}