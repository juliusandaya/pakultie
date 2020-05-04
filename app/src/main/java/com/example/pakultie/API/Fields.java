package com.example.pakultie.API;

public class Fields {
    String fullname;
    String email;
    String mobile;
    String date;
    int age;
    String gender;

    public Fields(String fullname, String email, String mobile, String date, int age, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.age = age;
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
