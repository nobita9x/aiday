package com.aiday.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserItem {

    @SerializedName("fbid")
    @Expose
    private String fbid;

    @SerializedName("fbName")
    @Expose
    private String fbName;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("fbLocation")
    @Expose
    private String fbLocation;

    @SerializedName("birthDay")
    @Expose
    private String birthDay;

    @SerializedName("relationship")
    @Expose
    private String relationship;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("email")
    @Expose
    private String email;

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

    @Override
    public String toString() {
        return "UserItem{" +
                "fbid='" + fbid + '\'' +
                ", fbName='" + fbName + '\'' +
                ", gender='" + gender + '\'' +
                ", fbLocation='" + fbLocation + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", relationship='" + relationship + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
