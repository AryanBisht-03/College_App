package com.example.collegeproject.Adapters;

public class studentDetail {
    String gender,batch,rollNum,image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public studentDetail() {

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public studentDetail(String gender, String batch, String rollNum) {
        this.gender = gender;
        this.batch = batch;
        this.rollNum = rollNum;
    }
}
