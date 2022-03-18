package com.example.newproj;

import android.widget.EditText;

public class database {
    String name,email,phone,password,dob;

    public database(String namefromdatabase, String emailfromdatabase, String strphone, EditText passw, String name) {
    }                       // to remove firebase error

    public database(String name, String email, String phone, String password,String dob) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dob=dob;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
